package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.entity.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository2 {

  @PersistenceContext
  private EntityManager em;

  public Long save(Member member) {
    em.persist(member);
    return member.getId();
  }

  //Command랑 Query를 분리하자.
  public Member find(Long id) {
    return em.find(Member.class, id);
  }

  public Member findOne(Long id) {
    return em.find(Member.class, id);
  }

  public List<Member> findAll() {
    return em.createQuery("select m from Member m", Member.class)
        .getResultList();
  }

  public List<Member> findByName(String name) {
    return em.createQuery("select m from Member m where m.name = :name",
            Member.class)
        .setParameter("name", name)
        .getResultList();
  }
}

