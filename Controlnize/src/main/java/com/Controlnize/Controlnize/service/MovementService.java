package com.Controlnize.Controlnize.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Controlnize.Controlnize.model.Movement;
import com.Controlnize.Controlnize.model.MovementType;
import com.Controlnize.Controlnize.model.Product;
import com.Controlnize.Controlnize.repository.MovementRepository;
import com.Controlnize.Controlnize.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class MovementService {

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void registerMovement(Long productId, Integer amount, MovementType movementType, String reason) {
        if (amount == null) {
            throw new IllegalArgumentException("The amount field cannot be null.");
        }

        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        if (movementType == MovementType.Exit && product.getAmount() < amount) {
            throw new RuntimeException("Not enough stock");
        }

        if (movementType == MovementType.Entry) {
            product.setAmount(product.getAmount() + amount);
        } else if (movementType == MovementType.Exit) {
            product.setAmount(product.getAmount() - amount);
        }

        Movement movement = new Movement();
        movement.setProduct(product);
        movement.setAmount(amount);
        movement.setMovementDate(LocalDateTime.now());
        movement.setMovementType(movementType);
        movement.setReason(reason);

        movementRepository.save(movement);
        productRepository.save(product);
    }
}