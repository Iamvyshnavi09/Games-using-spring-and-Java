package com.gamefour.memorygame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamefour.memorygame.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

}
