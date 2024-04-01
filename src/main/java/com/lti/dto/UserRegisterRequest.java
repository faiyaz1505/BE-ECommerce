package com.lti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {
//    @NotBlank(message = "email cannot be blank")
//    @Pattern(regexp = "^(.+)@(\\S+) $")
    private String userEmail;
//    @NotBlank(message = "first name cannot be blank")
    private String userFirstName;
    private String userLastName;
//    @NotBlank(message = "password cannot be blank")
    private String userPassword;
//    @NotBlank(message = "mobile number cannot be blank")
//    @Size(min = 10,max = 10)
    private String userMobileNo;
    private String userCity;
}
