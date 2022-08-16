package com.jpabook.jpashop.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberRepository2Test {

  @Autowired
  MemberRepository2 memberRepository2;


  @Test
  @Transactional //같은 영속성 컨텍스트 안에 있다. id값이 같으면 같은 메모리를 사용한다.
  @Rollback(value = false)
  public void testMember() throws Exception{
//
//    Member member = new Member();
//    member.setUsername("memberA");
//
//    Long savedId = memberRepository2.save(member);
//
//    Member findMember = memberRepository2.find(savedId);
//
//    Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//    Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//    Assertions.assertThat(findMember).isEqualTo(member); //영속성 컨텍스트 내의 1차 캐시에 같은 값을 사용한다는 의미.

  }

  @Test
  void save() {

  }

  @Test
  void find() {
  }
}