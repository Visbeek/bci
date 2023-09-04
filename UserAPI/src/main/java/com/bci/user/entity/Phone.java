package com.bci.user.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Phone {

    @Id
    @GeneratedValue
    private Long id;
    private Long number;
    private Integer cityCode;
    private Integer countryCode;

}
