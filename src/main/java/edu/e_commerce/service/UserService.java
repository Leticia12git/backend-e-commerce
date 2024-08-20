package edu.e_commerce.service;

import edu.e_commerce.enums.UserEnum;
import edu.e_commerce.model.User;
import edu.e_commerce.repository.UserRepository;
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
     * Este metodo cria um usuario no banco de dados
     *
     * @param user
     * @return
     */
    public User registerUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setRole(UserEnum.ADMIN);
        User saved = userRepository.save(user);
        return saved;
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