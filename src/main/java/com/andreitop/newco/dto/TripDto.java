package com.andreitop.newco.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;

public class TripDto implements Serializable {

    private static final long serialVersionUID = 5914366185889783660L;

    @Length(min = 0, max = 20)
    private Long id;
    @NotNull
    @Pattern(regexp = "/^[A-Z]{3}$/", message = "Input data format is incorrect")
    @Size(min = 3, max = 3)
    private String origin;
    @NotNull
    @Pattern(regexp = "/^[A-Z]{3}$/", message = "Input data format is incorrect")
    @Size(min = 3, max = 3)
    private String destination;
    @Min(0)
    @Max(666_666)
    private Integer price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
