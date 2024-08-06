package com.xzajkos.bank.Service;

import com.xzajkos.bank.Dto.UserDto;
import com.xzajkos.bank.Model.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface UserService {
    ResponseEntity <AuthenticationResponse> login(String username, String password);
    ResponseEntity<AuthenticationResponse> register(UserDto userDto);
    ResponseEntity<UserDto> getUser(String username);
    ResponseEntity<UserDto> updateUser(String username, UserDto userDto);
    ResponseEntity<UserDto> deleteUser(String username);
    ResponseEntity<UserDto> changePassword(String username, String oldPassword, String newPassword);
}
