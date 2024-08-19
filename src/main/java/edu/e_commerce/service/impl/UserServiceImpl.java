package edu.e_commerce.service.impl;

import edu.e_commerce.model.User;
import edu.e_commerce.model.dto.UserDto;
import edu.e_commerce.model.dto.UserDtoResponse;
import edu.e_commerce.repository.UserRepository;

import edu.e_commerce.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Esta classe de serviço contem toda a regra de negocio do meu usuario
 *
 * @author Leticia Lima
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Este metodo busca todos os usuarios no banco de dados
     *
     * @return usuarios
     */

    @Override
    public List<UserDtoResponse> findAll() {
        return userRepository.findAll()
            .stream()
            .map(UserDtoResponse::map)
            .toList();
    }

    /**
     * Este metodo cria um usuario no banco de dados
     *
     * @param userDto
     * @return UserDtoResponse
     */
    @Override
    public UserDtoResponse registerUser(final UserDto userDto) {
        var user = User.builder()
            .name(userDto.name())
            .password(userDto.password())
            .build();

        var userSaved = userRepository.save(user);

        return UserDtoResponse.map(userSaved);
    }

    /**
     * Este metodo deleta um usuario no banco de dados
     *
     * @param id
     */
    @Override
    public void delete(final Long id) {
        userRepository.findById(id)
            .map(User::getId)
            .ifPresent(userRepository::deleteById);
    }
}