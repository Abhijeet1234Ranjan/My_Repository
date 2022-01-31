package com.card.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private int id;
    @Column(name="CARD_NUMBER",unique = true)
    private String number;
    @Column(name="CUST_NAME",unique = false)
    private String name;
    @Column(name="CARD_LIMIT",unique = false)
    private long limit;
    @Column(name="card_BALANCE")
    private long balance;

    public CreditCard() {
    }

    public CreditCard(int id,String number, String name,Long limit,Long balance) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.limit = limit;
        this.balance =balance;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof CreditCard))
            return false;
        CreditCard creditCard = (CreditCard) o;
        return Objects.equals(this.id, creditCard.id) && Objects.equals(this.name, creditCard.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString() {
        return "CreditCard{" + "id=" + this.id + ", name=" + this.name + "}";
    }

}
