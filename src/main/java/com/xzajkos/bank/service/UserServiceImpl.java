package com.xzajkos.bank.Service;

import com.xzajkos.bank.Dto.UserDto;
import com.xzajkos.bank.Mapper.UserDtoMapper;
import com.xzajkos.bank.Model.AuthenticationResponse;
import com.xzajkos.bank.Model.OperationResponse;
import com.xzajkos.bank.Model.Role;
import com.xzajkos.bank.Model.User;
import com.xzajkos.bank.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDtoMapper userDtoMapper;


    public UserServiceImpl(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserDtoMapper userDtoMapper) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    public AuthenticationResponse login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User user = userRepository.findByUsername(username);
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    @Override
    public AuthenticationResponse register(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return new AuthenticationResponse(null);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getUsername().contains("bank")){
            user.setRole(Role.ADMIN);
        }
        else{
            user.setRole(Role.USER);
        }
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    @Override
    public UserDto getUser(String username) {
        return userDtoMapper.apply(userRepository.findByUsername(username));
    }

    @Override
    public OperationResponse deleteUser(String username) {
        if (userRepository.findByUsername(username) == null) {
            return new OperationResponse("DELETE_USER", "FAILED", new Date());
        }
        else{
            userRepository.delete(userRepository.findByUsername(username));
            return new OperationResponse("DELETE_USER", "SUCCESS", new Date());
        }
    }

    @Override
    public OperationResponse changePassword(String username, String oldPassword, String newPassword) {
        if (userRepository.findByUsername(username) == null) {
            return new OperationResponse("CHANGE_PASSWORD", "FAILED", new Date());
        }
        else{
            User user = userRepository.findByUsername(username);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return new OperationResponse("CHANGE_PASSWORD", "SUCCESS", new Date());
        }
    }
}
