package com.secondgame.rps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secondgame.rps.entity.GameResult;

public interface  GameResultRepository extends JpaRepository<GameResult, Long> {
	

}
