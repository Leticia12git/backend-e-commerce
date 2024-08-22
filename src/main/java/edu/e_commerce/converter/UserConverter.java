package edu.e_commerce.converter;

import edu.e_commerce.dtos.response.UserResponse;
import edu.e_commerce.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;


    public UserResponse convertEntityToDTO(User user) {
        return modelMapper.map(user, UserResponse.class);
    }
}
