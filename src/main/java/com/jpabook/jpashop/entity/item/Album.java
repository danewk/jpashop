package com.jpabook.jpashop.entity.item;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Album extends Item{
  private String artist;
  private String etc;
}
