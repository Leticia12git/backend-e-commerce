package edu.e_commerce.dtos.request;

import edu.e_commerce.enums.UserEnum;

public record UserRequest(String email, String password , UserEnum role) {
}
