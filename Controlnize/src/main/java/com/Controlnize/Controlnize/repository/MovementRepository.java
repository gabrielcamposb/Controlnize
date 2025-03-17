package com.Controlnize.Controlnize.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Controlnize.Controlnize.model.Movement;

public interface MovementRepository extends JpaRepository<Movement, Long> {}
