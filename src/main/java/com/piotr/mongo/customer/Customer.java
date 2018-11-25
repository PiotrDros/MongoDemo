package com.piotr.mongo.customer;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.time.ZonedDateTime;

public class Customer {
    @Id
    public String id;

    public String firstName;
    public String lastName;

    @CreatedDate
    public ZonedDateTime createdDate;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}
