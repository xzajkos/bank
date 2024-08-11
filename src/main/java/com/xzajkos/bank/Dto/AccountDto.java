package com.xzajkos.bank.Dto;

import java.math.BigDecimal;

public record AccountDto(long id, String accountNumber, BigDecimal balance, BigDecimal withdrawalLimit, UserDto user) {
}
