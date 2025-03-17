package com.Controlnize.Controlnize.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Controlnize.Controlnize.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{}
