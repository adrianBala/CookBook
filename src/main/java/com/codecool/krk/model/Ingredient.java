package com.codecool.krk.model;

import javax.persistence.*;

@Entity
@Table(name="ingredients")
public class Ingredient {
    @Id
    @GeneratedValue
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="amount")
    private double amount;
    @Column(name="unit")
    private Unit unit;

    public Ingredient() {}

    public Ingredient(String name, double amount, Unit unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    public Ingredient(long id, String name, double amount, Unit unit) {
        this(name, amount, unit);
        this.id = id;
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
