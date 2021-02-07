package com.herms.service;

import com.herms.entity.AttributeValidationEntity;
import com.herms.enums.Condition;
import com.herms.exception.AttributeValidationException;
import com.herms.exception.EvaluateExpressionException;
import com.herms.mapper.AttributeValidationMapper;
import com.herms.mapper.ResourceRowMapper;
import com.herms.model.AttributeValidation;
import com.herms.model.Resource;
import com.herms.model.ResourceRow;
import com.herms.model.ResourceRowValue;
import com.herms.pages.PageRequest;
import com.herms.repository.AttributeValidationRepository;
import io.quarkus.panache.common.Page;
import io.smallrye.config.common.utils.StringUtil;
import org.dom4j.util.StringUtils;

import javax.el.Expression;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class AttributeValidationService {
    @Inject
    private AttributeValidationRepository repository;
    @Inject
    private ResourceService resourceService;
    @Inject
    private ResourceRowValueService resourceRowValueService;

    public Response getAllPaged(PageRequest pageRequest){
        List<AttributeValidationEntity> entityList = repository.findAll().page(Page.of(pageRequest.getPageNumber(), pageRequest.getPageSize())).list();
        return Response
                .ok(entityList.stream().map(AttributeValidationMapper::toModel).collect(Collectors.toList()))
                .build();
    }

    public Response getById(Long id) {
        AttributeValidationEntity entity = repository.findById(id);
        if(entity == null){
            throw new EntityNotFoundException("Attribute Validation not found! ID: " + id);
        }

        return Response
                .ok(AttributeValidationMapper.toModel(repository.findById(id)))
                .build();
    }

    public Response create(AttributeValidation model) {
        AttributeValidationEntity entity = AttributeValidationMapper.fromModel(model);
        repository.persist(entity);

        return Response.ok(AttributeValidationMapper.toModel(entity)).status(Response.Status.CREATED).build();
    }

    public Response update(Long id, AttributeValidation model) {

        AttributeValidationEntity entity = repository.findById(id);
        if (entity == null)
            throw new WebApplicationException("Attribute Validation not found!", Response.Status.NOT_FOUND);

        AttributeValidationEntity modifications = AttributeValidationMapper.fromModel(model);
        entity.setCondition(modifications.getCondition());
        entity.setValueToCompare(modifications.getValueToCompare());
        entity.setResponseOnError(modifications.getResponseOnError());
        entity.setStatusOnError(modifications.getStatusOnError());
        return Response.ok(AttributeValidationMapper.toModel(entity)).build();
    }

    public Response delete(Long id) {
        if (repository.findById(id) == null)
            throw new WebApplicationException("Attribute Validation not found!", Response.Status.NOT_FOUND);

        repository.deleteById(id);
        return Response.noContent().build();
    }

    public Map<String, List<AttributeValidation>> getAttributeValidationMapByResourceId(Long resourceId) {
        List<AttributeValidationEntity> validationEntityList = repository.find(
                                                                "resourceAttribute.resource.id = ?1",
                                                                       resourceId).list();

        Map<String, List<AttributeValidation>> validationsMap = new HashMap<>();
        for(AttributeValidationEntity entity : validationEntityList) {
            String fieldName = entity.getResourceAttribute().getFieldName();
            if(validationsMap.containsKey(entity.getResourceAttribute().getFieldName())){
                List<AttributeValidation> attributeValidationList = validationsMap.get(fieldName);
                attributeValidationList.add(AttributeValidationMapper.toModel(entity));
            } else {
                List<AttributeValidation> validationList = new ArrayList<>();
                validationList.add(AttributeValidationMapper.toModel(entity));
                validationsMap.put(fieldName, validationList);
            }
        }

        return validationsMap;
    }

    public void validateRow(ResourceRow row) {
        Resource resource = (Resource) resourceService.getById(row.getResourceId()).getEntity();
        Map<String, Object> typedRowValues = resourceRowValueService.getRowValuesUsingResourceAttributes(
                                                                            ResourceRowMapper.fromModel(row),
                                                                            resource.getAttributesMap());

        Map<String, List<AttributeValidation>> validationsMap = getAttributeValidationMapByResourceId(row.getResourceId());
        for(Map.Entry<String, Object> value : typedRowValues.entrySet()){
            if(validationsMap.containsKey(value.getKey())) {
                for(AttributeValidation validation : validationsMap.get(value.getKey())) {
                    Condition c = Condition.toEnum(validation.getCondition());
                    String valueExp = (value.getValue() instanceof String)
                            ? "'" + value.getValue() + "'"
                            : String.valueOf(value.getValue());

                    String expression = Objects.requireNonNullElse(valueExp, "") +
                            (c != null ? c.getOperator() : "") +
                            Objects.requireNonNullElse(validation.getValueToCompare(), "");

                    if(!evaluate(expression)) {
                        throw new AttributeValidationException(
                                validation.getResponseOnError(),
                                Response.Status.fromStatusCode(validation.getStatusOnError()));
                    }
                }
            }
        }
    }

    private boolean evaluate(String expression) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {
            return (Boolean)engine.eval(expression);
        } catch (ScriptException e) {
            throw new EvaluateExpressionException("Bad expression format. Could not execute '" + expression + "'. " + e.getMessage());
        }
    }
}
