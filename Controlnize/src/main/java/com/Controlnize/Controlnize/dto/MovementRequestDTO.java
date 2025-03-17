package com.Controlnize.Controlnize.dto;

import com.Controlnize.Controlnize.model.MovementType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MovementRequestDTO {

    @NotNull(message = "The productId field cannot be null.")
    private Long productId;

    @NotNull(message = "The amount field cannot be null.")
    @Positive(message = "The amount must be positive.")
    private Integer amount;

    @NotNull(message = "The movementType field cannot be null.")
    private MovementType movementType;

    @NotNull(message = "The reason field cannot be null.")
    private String reason;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}