package com.poc.urlshortner.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.urlshortner.entity.UrlChecksumEntity;

@Repository
public interface UrlChecksumRepository extends CrudRepository<UrlChecksumEntity, String>{

}
