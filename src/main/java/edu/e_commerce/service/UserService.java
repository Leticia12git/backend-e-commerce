package edu.e_commerce.service;

import edu.e_commerce.model.dto.UserDto;
import edu.e_commerce.model.dto.UserDtoResponse;

import java.util.List;

public interface UserService {
  List<UserDtoResponse> findAll();
  UserDtoResponse registerUser(final UserDto userDto);
  void delete(final Long id);
}
