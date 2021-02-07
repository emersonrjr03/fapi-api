package com.herms.service;

import com.herms.entity.UserEntity;
import com.herms.model.Resource;
import com.herms.model.ResourceRow;
import com.herms.model.User;
import com.herms.repository.ResourceRepository;
import io.quarkus.security.UnauthorizedException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;
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

    public Response getAllPaged(String apiSecret, String resourceName) {
        User user = userService.getByApiSecret(apiSecret);
        if(user == null) {
            throw new UnauthorizedException("API Secret not valid!");
        }
        Resource resource = resourceService.getByUserIdAndResourceName(user.getId(), resourceName);

        return Response
                .ok(resourceRowService.getRowsByResource(resource))
                .build();
    }
}
