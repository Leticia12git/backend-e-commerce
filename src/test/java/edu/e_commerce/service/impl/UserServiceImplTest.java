package edu.e_commerce.service.impl;

import edu.e_commerce.model.User;
import edu.e_commerce.model.dto.UserDtoResponse;
import edu.e_commerce.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final Long ID = 1L;
    private static final String NAME = "test";
    private static final String PASSWORD = "password";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Success find all users")
    void findAll() {
        // given
        final List<UserDtoResponse> userDtoResponseExpected = getUserDtoResponse();

        when(userRepository.findAll()).thenReturn(List.of(getUser()));

        // when
        final var result = userService.findAll();

        // then
        assertAll(
            () -> assertEquals(userDtoResponseExpected, result),
            () -> verify(userRepository).findAll()
        );
    }

    @Test
    @DisplayName("Success create user")
    void registerUser() {
    }

    @Test
    @DisplayName("Success delete user")
    void delete() {
        // given
        when(userRepository.findById(ID)).thenReturn(Optional.of(getUser()));
        doNothing().when(userRepository).deleteById(ID);

        // when
        userService.delete(ID);

        // then
        assertAll(
            () -> verify(userRepository).findById(ID),
            () -> verify(userRepository).deleteById(ID)
        );
    }

    private List<UserDtoResponse> getUserDtoResponse() {
        final UserDtoResponse userDtoResponse = new UserDtoResponse(ID, NAME, PASSWORD);

        return List.of(userDtoResponse);
    }

    private User getUser() {
        return new User(ID, NAME, PASSWORD);
    }
}