package com.jpabook.jpashop.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

  private String city;
  private String street;
  private String zipcode;

}
