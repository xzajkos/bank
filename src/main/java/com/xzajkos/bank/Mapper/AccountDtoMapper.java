package com.xzajkos.bank.Mapper;

import com.xzajkos.bank.Dto.AccountDto;
import com.xzajkos.bank.Model.Account;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AccountDtoMapper implements Function<Account, AccountDto> {

    private final UserDtoMapper userDtoMapper;

    public AccountDtoMapper(UserDtoMapper userDtoMapper) {
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    public AccountDto apply(Account account) {
        return new AccountDto(
                account.getId(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getWithdrawalLimit(),
                userDtoMapper.apply(account.getUser())
        );
    }
}
