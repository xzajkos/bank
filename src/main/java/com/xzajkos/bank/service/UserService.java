package com.xzajkos.bank.Service;

import com.xzajkos.bank.Dto.UserDto;
import com.xzajkos.bank.Model.AuthenticationResponse;
import com.xzajkos.bank.Model.OperationResponse;
import com.xzajkos.bank.Model.User;

public interface UserService {
    AuthenticationResponse login(String username, String password);
    AuthenticationResponse register(User user);
    UserDto getUser(long id);
    OperationResponse deleteUser(long id);
    OperationResponse changePassword(String username, String oldPassword, String newPassword);
}
