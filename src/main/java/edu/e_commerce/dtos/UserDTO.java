package edu.e_commerce.dtos;

import edu.e_commerce.enums.UserEnum;

public record UserDTO(String email, String password , UserEnum role) {
}
