package com.herms.service;

import com.herms.enums.FieldType;
import com.herms.mapper.ResourceRowMapper;
import com.herms.mapper.ResourceRowValueMapper;
import com.herms.model.Resource;
import com.herms.model.ResourceAttribute;
import com.herms.model.ResourceRow;
import com.herms.model.User;
import com.herms.repository.ResourceRepository;
import io.quarkus.security.UnauthorizedException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Map;

@ApplicationScoped
public class GenericService {

    @Inject
    ResourceRepository repository;

    @Inject
    UserService userService;
    @Inject
    ResourceService resourceService;
    @Inject
    ResourceRowService resourceRowService;
    @Inject
    ResourceRowValueService resourceRowValueService;

    public Response getAllPaged(String apiSecret, String resourceName) {
        User user = userService.getByApiSecret(apiSecret);
        if(user == null) {
            throw new UnauthorizedException("API Secret not valid!");
        }
        Resource resource = resourceService.getByUserIdAndResourceName(user.getId(), resourceName);

        return Response
                .ok(resourceRowService.getGenericRowsByResource(resource))
                .build();
    }

    public Response getById(String apiSecret, String resourceName, String resourceId) {
        User user = userService.getByApiSecret(apiSecret);
        if(user == null) {
            throw new UnauthorizedException("API Secret not valid!");
        }
        Resource resource = resourceService.getByUserIdAndResourceName(user.getId(), resourceName);

        return Response
                .ok(resourceRowService.getGenericRowByResourceAndPkAttributeValue(resource, resourceId))
                .build();
    }

    public Response create(String apiSecret, String resourceName, Map<String, Object> rowValues) {
        User user = userService.getByApiSecret(apiSecret);
        if(user == null) {
            throw new UnauthorizedException("API Secret not valid!");
        }
        Resource resource = resourceService.getByUserIdAndResourceName(user.getId(), resourceName);

        ResourceAttribute pkAttribute = resource.getAttributesMap().values()
                                                                    .stream()
                                                                    .filter(attr -> attr.getFieldIsPk())
                                                                    .findFirst()
                                                                    .orElse(null);

        Object pkValue = rowValues.get(pkAttribute.getFieldName());
        if(FieldType.INTEGER.equals(pkAttribute.getFieldType())
                && (pkValue == null || "".equals(String.valueOf(pkValue)))){
            Object nextPkValue = resourceRowValueService.getNextPkValue(resource);
            rowValues.put(pkAttribute.getFieldName(), nextPkValue);
        }

        ResourceRow rowModel = new ResourceRow(null, resource.getId(), ResourceRowValueMapper.toModel(rowValues));
        rowModel = (ResourceRow) resourceRowService.create(rowModel).getEntity();

        rowValues = resourceRowValueService.getRowValuesUsingResourceAttributes(ResourceRowMapper.fromModel(rowModel), resource.getAttributesMap());

        return Response.ok(rowValues).status(Response.Status.CREATED).build();
    }
}
