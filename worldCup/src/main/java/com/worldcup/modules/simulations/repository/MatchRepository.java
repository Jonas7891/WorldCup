package com.worldcup.modules.simulations.repository;

import com.worldcup.modules.simulations.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Integer>{

	java.util.List<Match> findByStatusTrue();

}
