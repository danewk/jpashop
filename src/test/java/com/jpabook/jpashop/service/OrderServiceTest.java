package com.jpabook.jpashop.service;

import static org.junit.Assert.assertEquals;

import com.jpabook.jpashop.entity.Address;
import com.jpabook.jpashop.entity.Member;
import com.jpabook.jpashop.entity.Order;
import com.jpabook.jpashop.entity.OrderStatus;
import com.jpabook.jpashop.entity.item.Book;
import com.jpabook.jpashop.entity.item.Item;
import com.jpabook.jpashop.repository.OrderRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

  @PersistenceContext
  EntityManager em;

  @Autowired
  OrderService orderService;

  @Autowired
  OrderRepository orderRepository;

  @Test
  public void 상품주문() throws Exception {
    //given
    Member member = createMember();
    Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고
    int orderCount = 2;

    //when
    Long order = orderService.order(member.getId(), item.getId(), orderCount);

    //then
    Order getOrder = orderRepository.findOne(order);

    assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER,
        getOrder.getStatus());
    assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1,
        getOrder.getOrderItems()
            .size());
    assertEquals("주문 가격은 가격 * 수량이다.", 10000 * 2,
        getOrder.getTotalPrice());
    assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, item.getStockQuantity());

  }

  @Test
  public void 주문취소() throws Exception {
    //given

    //when

    //then
  }

  @Test
  public void 재고수량초과() throws Exception {
    //given

    //when

    //then
  }

  private Member createMember() {
    Member member = new Member();
    member.setName("회원1");
    member.setAddress(new Address("서울", "강가", "123-123")); em.persist(member);
    return member;
  }

  private Book createBook(String name, int price, int stockQuantity) {
    Book book = new Book();
    book.setName(name);
    book.setStockQuantity(stockQuantity);
    book.setPrice(price);
    em.persist(book);
    return book;
  }
}