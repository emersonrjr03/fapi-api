package com.herms.repository;

import com.herms.entity.AttributeValidationEntity;
import com.herms.entity.ResourceEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class AttributeValidationRepository implements PanacheRepository<AttributeValidationEntity> {


}
