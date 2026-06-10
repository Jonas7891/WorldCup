package com.worldcup.worldcup.modules.simulations.match.repository;

import com.worldcup.worldcup.modules.simulations.match.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatchRepository extends JpaRepository<Match, Integer>{

	java.util.List<Match> findByStatusTrue();

	@Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM team t WHERE t.name = :name")
	boolean existsByName(@Param("name") String name);

}
