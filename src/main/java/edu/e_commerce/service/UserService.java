package edu.e_commerce.service;


import edu.e_commerce.converter.UserConverter;
import edu.e_commerce.dtos.request.UserRequest;
import edu.e_commerce.dtos.response.UserResponse;
import edu.e_commerce.model.User;
import edu.e_commerce.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Esta classe de serviço contem toda a regra de negocio do meu usuario
 *
 * @author Leticia Lima
 */

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserConverter userConverter;


    @Autowired
    private UserRepository userRepository;

    /**
     * Este metodo busca todos os usuarios no banco de dados
     *
     * @return usuarios
     */

    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Este metodo registra um usuario
     *
     * @param user
     * @return user
     */

    public UserResponse registerUser(UserRequest user) {
        User newUser = new User();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(user.password());
        newUser.setEmail(user.email());
        newUser.setPassword(encryptedPassword);
        newUser.setRole(user.role());
        User savedUser = userRepository.save(newUser);
        return userConverter.convertEntityToDTO(savedUser);
    }

    /**
     * Este metodo deleta um usuario no banco de dados
     *
     * @param id
     */
    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        }
    }
}