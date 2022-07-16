package com.poc.urlshortner.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.urlshortner.entity.UrlShortnerEntity;
/**
 * Url shortner repository
 * @author Bala
 *
 */
@Repository
public interface UrlShortnerRepository extends CrudRepository<UrlShortnerEntity, Long> {

}
