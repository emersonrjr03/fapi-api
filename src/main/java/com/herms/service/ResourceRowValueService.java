package com.herms.service;

import com.herms.entity.ResourceAttributeEntity;
import com.herms.entity.ResourceRowEntity;
import com.herms.entity.ResourceRowValueEntity;
import com.herms.enums.FieldType;
import com.herms.mapper.ResourceRowMapper;
import com.herms.mapper.ResourceRowValueMapper;
import com.herms.model.Resource;
import com.herms.model.ResourceAttribute;
import com.herms.model.ResourceRowValue;
import com.herms.repository.ResourceRowValueRepository;
import com.herms.utils.ConvertUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ResourceRowValueService {
    @Inject
    private ResourceRowValueRepository repository;

    public ResourceRowValue create(ResourceRowValue model) {
        ResourceRowValueEntity entity = ResourceRowValueMapper.fromModel(model);
        repository.persist(entity);
        return ResourceRowValueMapper.toModel(entity);
    }

    public Map<String, Object> getRowValuesUsingResourceAttributes(ResourceRowEntity row,
                                                                    Map<String, ResourceAttribute> resourceAttributeMap) {
        Map<String, Object> rowValues = new HashMap<>();
        for(ResourceRowValueEntity value : row.getValueList()) {
            rowValues.put(value.getField(), convertValueToType(value, resourceAttributeMap.get(value.getField())));
        }
        return rowValues;
    }

    private Object convertValueToType(ResourceRowValueEntity value, ResourceAttribute attribute) {
        if (attribute.getFieldType() == FieldType.BOOLEAN) {
            return Boolean.valueOf(value.getValue());
        } else if (attribute.getFieldType() == FieldType.DATE) {
            Date date = ConvertUtils.stringToDate(value.getValue(), attribute.getFieldFormat());
            return ConvertUtils.dateToString(date, attribute.getFieldFormat());
        } else if (attribute.getFieldType() == FieldType.INTEGER) {
            return Integer.valueOf(value.getValue());
        } else if (attribute.getFieldType() == FieldType.DOUBLE) {
            return Double.valueOf(value.getValue());
        } else {
            return value.getValue();
        }
    }

    public void updateFieldValuesIfNeeded(ResourceAttributeEntity entity, ResourceAttributeEntity modifiedEntity) {
        boolean changingFieldName = !(entity.getFieldName() + "").equals(modifiedEntity.getFieldName());
        boolean changingFieldFormat = !(entity.getFieldFormat() + "").equals(modifiedEntity.getFieldFormat());

        if(changingFieldFormat || changingFieldName) {
            List<ResourceRowValueEntity> valueEntityList = repository.list(
                    "row.resource.id = ?1 and field = ?2",
                    entity.getResource().getId(),
                    entity.getFieldName());


            for(ResourceRowValueEntity value : valueEntityList) {

                if(changingFieldName) {
                    value.setField(modifiedEntity.getFieldName());
                } else if(changingFieldFormat) {
                    String newValue = value.getValue();
                    if (entity.getFieldType() == FieldType.DATE) {
                        Date date = ConvertUtils.stringToDate(value.getValue(), entity.getFieldFormat());
                        newValue = ConvertUtils.dateToString(date, modifiedEntity.getFieldFormat());
                    }
                    //TODO If needed, create the same logic for other fieldTypes. For now I don't think it's necessary.
                    value.setValue(newValue);
                }
            }
        }


    }

    public Object getNextPkValue(Resource resource) {
        ResourceAttribute pkAttribute = resource.getAttributesMap().values()
                .stream()
                .filter(attr -> attr.getFieldIsPk())
                .findFirst()
                .orElse(null);
        TypedQuery<Integer> query = repository.getEntityManager().createQuery(
                "select max(cast(v.value as java.lang.Integer)) from ResourceRowValueEntity v " +
                "where row.resource.id = :resourceId and field = :fieldName", Integer.class);
        query.setParameter("resourceId", resource.getId());
        query.setParameter("fieldName", pkAttribute.getFieldName());
        Integer maxValue = query.getSingleResult();

        Integer nextPkValue = null;
        if(maxValue != null) {
            nextPkValue = maxValue + 1;
        }
        return nextPkValue;
    }
}
