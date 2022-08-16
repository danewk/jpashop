package com.jpabook.jpashop.service;

import static org.junit.Assert.assertEquals;

import com.jpabook.jpashop.entity.Member;
import com.jpabook.jpashop.repository.MemberRepository2;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

  @Autowired
  MemberService memberService;
  @Autowired
  MemberRepository2 memberRepository;

  @Test
  public void join() throws Exception {
    //Given
    Member member = new Member();
    member.setName("kim");
    //When
    Long saveId = memberService.join(member);
    //Then
    assertEquals(member, memberRepository.findOne(saveId));
  }


  @Test
  public void 중복_회원_예외() throws Exception {
//Given
    Member member1 = new Member();
    member1.setName("kim");
    Member member2 = new Member();
    member2.setName("kim");
//When
    memberService.join(member1);
    memberService.join(member2); //예외가 발생해야 한다.


  }
}