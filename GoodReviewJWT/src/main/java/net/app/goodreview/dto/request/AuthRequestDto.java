package net.app.goodreview.dto.request;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String email;
    private String password;
}
