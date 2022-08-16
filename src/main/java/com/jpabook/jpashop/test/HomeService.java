package com.jpabook.jpashop.test;

import javax.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HomeService {

  private final HomeRepository homeRepository;

  @Transactional
  public int currentPrice(String name) {
    Home home = homeRepository.findByName(name);
    return home.getPrice();
  }

  @Transactional
  @Lock(value = LockModeType.PESSIMISTIC_WRITE) //여기
  public int decreasePrice(String name, int price) {
    Home home = homeRepository.findWithNameForUpdate(name);
    home.decreasePrice(price);
    return home.getPrice();
  }



}
