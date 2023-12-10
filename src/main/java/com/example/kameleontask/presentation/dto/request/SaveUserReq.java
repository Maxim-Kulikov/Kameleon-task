package com.example.kameleontask.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaveUserReq {

    @Email(message = "email")
    @Schema(description = "User email", required = true, type = "string")
    private String email;

    @Pattern(regexp = "^[A-Za-z0-9-]*$", message = "password should contain only digits and letters")
    @Size(min = 10, max = 40, message = "password size should be more than 10 symbols")
    @Schema(description = "User password", required = true, type = "string")
    private String password;

    @Pattern(regexp = "^[A-Za-z0-9-]*$", message = "username should contain only digits and letters")
    @Size(min = 1, max = 40, message = "username size should be more than 1 symbol")
    @Schema(description = "Username", required = true, type = "string")
    private String username;
}
