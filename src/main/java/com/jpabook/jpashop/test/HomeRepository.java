package com.jpabook.jpashop.test;

import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {

  Home findByName(String name);

  @Lock(value = LockModeType.PESSIMISTIC_WRITE) //여기
  @Query("select h from Home h where h.name = :name")
  Home findWithNameForUpdate(@Param("name") String name);
}
