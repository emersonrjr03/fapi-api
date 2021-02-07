package com.herms.repository;

import com.herms.entity.ResourceRowEntity;
import com.herms.entity.ResourceRowValueEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ResourceRowValueRepository implements PanacheRepository<ResourceRowValueEntity> {


}
