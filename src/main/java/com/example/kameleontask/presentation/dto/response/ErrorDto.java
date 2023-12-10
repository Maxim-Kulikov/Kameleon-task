package com.example.kameleontask.presentation.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorDto {

    private String code;

    private String message;
}
