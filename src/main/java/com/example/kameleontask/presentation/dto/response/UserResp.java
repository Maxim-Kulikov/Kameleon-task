
package com.example.kameleontask.presentation.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserResp {
    private Long id;
    private String email;
    private String username;
}
