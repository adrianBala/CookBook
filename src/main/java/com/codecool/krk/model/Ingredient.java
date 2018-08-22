package com.codecool.krk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="INGREDIENTS")
public class Ingredient {
    @Column(name="NAME")
    private String name;
    @Column(name="AMOUNT")
    private double amount;
    @Column(name="UNIT")
    private Unit unit;

    public Ingredient() {}

    public Ingredient(String name, double amount, Unit unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", unit=" + unit +
                '}';
    }

}
