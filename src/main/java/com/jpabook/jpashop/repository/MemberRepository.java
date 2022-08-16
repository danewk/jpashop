package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.entity.Member;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  @PersistenceContext
  private EntityManager em;

}
