package com.worldcup.modules.mundial.repository;

import com.worldcup.modules.mundial.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountryRepository extends JpaRepository<Country, Integer>{

	// Find only active countries (status = true)
	java.util.List<Country> findByStatusTrue();

	// Checks if a country with the given name already exists
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM country c WHERE c.name_country = :name_country")
	boolean existsByName_country(@Param("name_country") String name_country);

}
