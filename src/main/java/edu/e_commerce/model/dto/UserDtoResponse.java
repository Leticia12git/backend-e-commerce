package edu.e_commerce.model.dto;

import edu.e_commerce.model.User;

public record UserDtoResponse(Long id, String name, String password) {
  public static UserDtoResponse map(User user) {
    if (user == null) {
      return null;
    }

    return new UserDtoResponse(user.getId(), user.getName(), user.getPassword());
  }
}
