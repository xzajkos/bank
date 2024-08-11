package com.xzajkos.bank.Mapper;

import com.xzajkos.bank.Dto.UserDto;
import com.xzajkos.bank.Model.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDtoMapper implements Function<User, UserDto> {

    @Override
    public UserDto apply(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername()
        );
    }
}
