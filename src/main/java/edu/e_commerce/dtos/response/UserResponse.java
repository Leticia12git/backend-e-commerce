package edu.e_commerce.dtos.response;

import edu.e_commerce.enums.UserEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String email;
    private String password ;
    private UserEnum role;


}
