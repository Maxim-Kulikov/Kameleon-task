package com.example.kameleontask.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaveVoteReq {

    @NotNull(message = "idQuote should be not null")
    @Positive(message = "idQuote should be positive")
    @Schema(description = "Quote id", required = true, type = "long")
    private Long idQuote;

    @NotNull(message = "idUser should be not null")
    @Positive(message = "idUSer should be positive")
    @Schema(description = "User id", required = true, type = "long")
    private Long idUser;

    @NotNull(message = "isPositive should be not null")
    @Min(value = 0, message = "isPositive min value is 0 - it means false")
    @Max(value = 1, message = "isPositive max value is 1 - it means true")
    @Schema(description = "Is vote positive or negative (1 or 0)", required = true, type = "short")
    private Short isPositive;
}
