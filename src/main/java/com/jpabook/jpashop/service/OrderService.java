package com.jpabook.jpashop.service;

import com.jpabook.jpashop.entity.Delivery;
import com.jpabook.jpashop.entity.DeliveryStatus;
import com.jpabook.jpashop.entity.Member;
import com.jpabook.jpashop.entity.Order;
import com.jpabook.jpashop.entity.OrderItem;
import com.jpabook.jpashop.entity.OrderSearch;
import com.jpabook.jpashop.entity.item.Item;
import com.jpabook.jpashop.repository.ItemRepository;
import com.jpabook.jpashop.repository.MemberRepository2;
import com.jpabook.jpashop.repository.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

  private final MemberRepository2 memberRepository;
  private final OrderRepository orderRepository;
  private final ItemRepository itemRepository;

  @Transactional
  public Long order(Long memberId, Long itemId, int count) {

    //엔티티 조회
    Member member = memberRepository.findOne(memberId);
    Item item = itemRepository.findOne(itemId);

    //배송정보 생성
    Delivery delivery = new Delivery();
    delivery.setAddress(member.getAddress());
    delivery.setStatus(DeliveryStatus.READY);

    //주문상품 생성
    OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(),
        count);
    //주문 생성
    Order order = Order.createOrder(member, delivery, orderItem);

    //주문 저장
    orderRepository.save(order);
    return order.getId();

  }

  /**
   * 주문 취소
   */
  @Transactional
  public void cancelOrder(Long orderId) {
    //주문 엔티티 조회
    Order order = orderRepository.findOne(orderId);
    //주문 취소
    order.cancel();
  }

  //검색
  public List<javax.persistence.criteria.Order> findOrders(OrderSearch orderSearch) {
    return orderRepository.findAllByString(orderSearch);
  }

}
