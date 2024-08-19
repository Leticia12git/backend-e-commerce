package edu.e_commerce.model.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;

public record UserDto(@Nonnull String name, @NotBlank String password) {
}
