package com.xzajkos.bank.Model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CheckingAccount extends Account{
    private BigDecimal overdraftLimit;
}
