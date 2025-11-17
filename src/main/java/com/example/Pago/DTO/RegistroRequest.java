package com.example.Pago.DTO;

import lombok.Data;

@Data
public class RegistroRequest {
    private String email;
    private String password;
    private String role;
}
