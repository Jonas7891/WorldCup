package com.worldcup.worldcup.modules.mundial.team.repository;

import com.worldcup.worldcup.modules.mundial.team.entity.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer>{

	List<Team> findByStatus(Boolean status);

	@Query("SELECT t FROM team t WHERE t.country.id_country = :country_id")
	List<Team> findByCountryId(@Param("country_id") Integer country_id);

	@Query("SELECT t FROM team t WHERE t.group.id_group = :group_id")
	List<Team> findByGroupId(@Param("group_id") Integer group_id);

	@Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM team t WHERE t.name = :name")
	boolean existsByName(@Param("name") String name);

}
