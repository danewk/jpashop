package com.jpabook.jpashop.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController1 {

  private final HomeService homeService;

  @GetMapping("/decrease")
  public String decreasePrice(@RequestParam(value = "name") String name, @RequestParam(value = "price") int price) {
    String result;
    try {
      homeService.decreasePrice(name, price);
      result = "현재 가격 : " + homeService.currentPrice(name);
    } catch (Exception e) {
      result = e.getMessage();
    }
    log.info(result);
    return result;
  }

}
