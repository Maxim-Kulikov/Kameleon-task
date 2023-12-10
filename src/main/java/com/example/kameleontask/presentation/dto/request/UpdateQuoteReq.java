package com.example.kameleontask.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateQuoteReq {
    @NotNull(message = "content should be not null")
    @Size(min = 1, max = 40, message = "content size should be more than 1 symbol")
    @Schema(description = "Content", required = true, type = "string")
    private String content;
}
