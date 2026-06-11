package com.worldcup.modules.mundial.stadium.repository;

import com.worldcup.modules.mundial.stadium.entity.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StadiumRepository extends JpaRepository<Stadium, Integer>{

	// Find only active stadiums (status = true)
	java.util.List<Stadium> findByStatusTrue();

	// Check if stadium with given name already exists
	@Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM stadium s WHERE s.name = :name")
	boolean existsByName(@Param("name") String name);

}
