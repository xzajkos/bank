package com.xzajkos.bank.Controller;

import com.xzajkos.bank.Dto.UserDto;
import com.xzajkos.bank.Model.AuthenticationResponse;
import com.xzajkos.bank.Model.OperationResponse;
import com.xzajkos.bank.Model.User;
import com.xzajkos.bank.Repository.UserRepository;
import com.xzajkos.bank.Service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserServiceImpl userService;
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(String username, String password) {
        return ResponseEntity.ok(userService.login(username, password));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OperationResponse> deleteUser(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PatchMapping("/{username}/changepassword")
    public ResponseEntity<OperationResponse> changePassword(@PathVariable("username")String username,
                                                            @RequestParam String oldPassword,
                                                            @RequestParam String newPassword) {
        return ResponseEntity.ok(userService.changePassword(username, oldPassword, newPassword));
    }

}
