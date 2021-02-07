package com.herms.repository;

import com.herms.entity.ResourceRowEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ResourceRowRepository implements PanacheRepository<ResourceRowEntity> {


}
