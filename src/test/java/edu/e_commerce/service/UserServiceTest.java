package edu.e_commerce.service;

import edu.e_commerce.converter.UserConverter;
import edu.e_commerce.dtos.request.UserRequest;
import edu.e_commerce.dtos.response.UserResponse;
import edu.e_commerce.enums.UserEnum;
import edu.e_commerce.model.User;
import edu.e_commerce.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private User user;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserConverter userConverter;


    @Mock
    private UserEnum userEnum;

    @InjectMocks
    private UserService userService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Test
    @DisplayName("Sucess find all users")
    void findAll() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> response = userService.findAll();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(0).getClass());

        assertEquals(user.getId(), response.get(0).getId());
        assertEquals(user.getEmail(), response.get(0).getEmail());
        assertEquals(user.getPassword(), response.get(0).getPassword());
    }

    @Test
    @DisplayName("Not Sucess find all users")
    void notFindAll() {
        userRepository.findAll();
    }

    @Test
    @DisplayName("Sucess create user")
    public void testRegisterUser() {
        // Dados de entrada
        UserRequest userRequest = new UserRequest("santos@gmail.com", "123456789", UserEnum.ADMIN);

        // Mockando comportamento do passwordEncoder
        String encodedPassword = passwordEncoder.encode(userRequest.password());

        // Mockando comportamento do userRepository
        User user = new User();
        user.setEmail(userRequest.email());
        user.setPassword(encodedPassword);
        user.setRole(userRequest.role());
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Mockando comportamento do userConverter
        UserResponse userResponse = new UserResponse("santos@gmail.com", "123456789", UserEnum.ADMIN);
        when(userConverter.convertEntityToDTO(any(User.class))).thenReturn(userResponse);

        // Executando o método a ser testado
        UserResponse result = userService.registerUser(userRequest);

        // Verificações
        assertEquals(userResponse, result); // Verifica se a resposta esperada é igual ao resultado
        verify(userRepository).save(any(User.class)); // Verifica se o método save foi chamado
        verify(userConverter).convertEntityToDTO(any(User.class)); // Verifica se o método convertEntityToDTO foi chamado
        assertEquals(true, passwordEncoder.matches(userRequest.password(), encodedPassword));

    }

    @Test
    @DisplayName("Sucess delete user")
    void delete() {
    }

}