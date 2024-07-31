package com.xzajkos.bank.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Account {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String accountNumber;
    private BigDecimal balance;
    private BigDecimal withdrawalLimit;
    @ManyToOne
    @JoinTable(name = "userId")
    private User user;
}
