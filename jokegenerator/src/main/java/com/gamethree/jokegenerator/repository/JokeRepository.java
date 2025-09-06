package com.gamethree.jokegenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamethree.jokegenerator.entity.Joke;

public interface JokeRepository extends JpaRepository<Joke, Long>{
	

}
