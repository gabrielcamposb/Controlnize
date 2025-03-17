package com.Controlnize.Controlnize.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Controlnize.Controlnize.dto.MovementRequestDTO;
import com.Controlnize.Controlnize.service.MovementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private MovementService movementService;

    @PostMapping("/movement")
    public ResponseEntity<String> registerMovement(@Valid @RequestBody MovementRequestDTO request) {
        try {
            movementService.registerMovement(request.getProductId(), request.getAmount(), request.getMovementType(), request.getReason());
            return ResponseEntity.ok("Movement registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}