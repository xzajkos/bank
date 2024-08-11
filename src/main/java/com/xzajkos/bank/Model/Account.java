package com.xzajkos.bank.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String accountNumber;
    private BigDecimal balance;
    private BigDecimal withdrawalLimit;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
