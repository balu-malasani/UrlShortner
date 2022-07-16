package com.poc.urlshortner.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.urlshortner.entity.UrlChecksumEntity;
/**
 * Url checksum repository
 * 
 * @author Bala
 *
 */
@Repository
public interface UrlChecksumRepository extends CrudRepository<UrlChecksumEntity, String> {

}
