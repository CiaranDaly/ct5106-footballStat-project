package com.ct5106.carsv2.demo.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Car
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String make, model, colour, registration;

    private int yearRegistered;

    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Owner owner;

    public Car()
    {
        // super();
    }

    public Car(String make, String model, String colour, String registration, int yearRegistered, double price, Owner owner)
    {
        super();
        this.make = make;
        this.model = model;
        this.colour = colour;
        this.registration = registration;
        this.yearRegistered = yearRegistered;
        this.price = price;
        this.owner = owner;
    }

    public Long getId()
    {
        return id;
    }

    // this should only be set in the DB, so commenting this one out
    /*
     * public void setId(Long id) { this.id = id; }
     */

    public String getMake()
    {
        return make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getColour()
    {
        return colour;
    }

    public void setColour(String colour)
    {
        this.colour = colour;
    }

    public String getRegistration()
    {
        return registration;
    }

    public void setRegistration(String registration)
    {
        this.registration = registration;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getYearRegistered()
    {
        return yearRegistered;
    }

    public void setYearRegistered(int yearRegistered)
    {
        this.yearRegistered = yearRegistered;
    }

    // Getter and setter
    public Owner getOwner()
    {
        return owner;
    }

    public void setOwner(Owner owner)
    {
        this.owner = owner;
    }
}
