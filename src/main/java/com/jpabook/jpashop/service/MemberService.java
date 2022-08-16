package com.jpabook.jpashop.service;

import com.jpabook.jpashop.entity.Member;
import com.jpabook.jpashop.repository.MemberRepository2;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

  private final MemberRepository2 memberRepository;

  /**
   * 회원가입
   */
  @Transactional
  public Long join(Member member){
    validateDuplicateMember(member);
    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
    List<Member> members = memberRepository.findByName(member.getName());
    if(!members.isEmpty()){
      throw new IllegalStateException("이미 존재하는 회원입니다.");
    }
    //Exception발생
  }

  public List<Member> findMembers(){
    return memberRepository.findAll();
  }

  public Member findOne(Long memberId){
    return memberRepository.findOne(memberId);
  }

}
