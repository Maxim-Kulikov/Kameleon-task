package com.example.kameleontask.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SortQuotesReq {

    @NotNull(message = "quantity should be not null")
    @Positive(message = "quantity should be positive")
    @Schema(description = "First required quotes quantity", required = true, type = "integer")
    private Integer quantity;

    @NotNull(message = "isPositive should be not null")
    @Min(value = 0, message = "isPositive min value is 0 - it means false")
    @Max(value = 1, message = "isPositive max value is 1 - it means true")
    @Schema(description = "Is vote positive or negative (1 or 0)", required = true, type = "short")
    private Short isPositive;
}
