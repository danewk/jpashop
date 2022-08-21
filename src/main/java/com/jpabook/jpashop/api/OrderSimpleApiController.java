package com.jpabook.jpashop.api;

import com.jpabook.jpashop.entity.Address;
import com.jpabook.jpashop.entity.Order;
import com.jpabook.jpashop.entity.OrderStatus;
import com.jpabook.jpashop.repository.OrderRepository;
import com.jpabook.jpashop.repository.OrderSimpleQueryDto;
import com.jpabook.jpashop.repository.OrderSimpleQueryRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** *
 * xToOne(ManyToOne, OneToOne) 관계 최적화 * Order
 * Order -> Member
 * Order -> Delivery
 *
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

  private final OrderRepository orderRepository;
  private final OrderSimpleQueryRepository orderSimpleQueryRepository; //의존관계

  /**
   * V2. 엔티티를 조회해서 DTO로 변환(fetch join 사용X) * - 단점: 지연로딩으로 쿼리 N번 호출
   */
  @GetMapping("/api/v2/simple-orders")
  public List<SimpleOrderDto> ordersV2(){
    List<Order> orders = orderRepository.findAll();
    List<SimpleOrderDto> collect = orders.stream()
        .map(o -> new SimpleOrderDto(o))
        .collect(Collectors.toUnmodifiableList());

    return collect;
  }

  /**
   * V3. 엔티티를 조회해서 DTO로 변환(fetch join 사용O)
   * - fetch join으로 쿼리 1번 호출
   * 참고: fetch join에 대한 자세한 내용은 JPA 기본편 참고(정말 중요함)
   * */
  @GetMapping("/api/v3/simple-orders")
  public List<SimpleOrderDto> orderV3(){
    List<Order> allWithMemberDelivery = orderRepository.findAllWithMemberDelivery();
    return allWithMemberDelivery.stream()
        .map(SimpleOrderDto::new)
        .toList();
  }

  /**
   * V4. JPA에서 DTO로 바로 조회
   *-쿼리1번 호출
   * - select 절에서 원하는 데이터만 선택해서 조회 */
  @GetMapping("/api/v4/simple-orders")
  public List<OrderSimpleQueryDto> orderV4(){
    return orderSimpleQueryRepository.findOrderDtos();
  }

  @Data
  static class SimpleOrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate; //주문시간

    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDto(Order order) {
      orderId = order.getId();
      name = order.getMember().getName();
      orderDate = order.getOrderDate();
      orderStatus = order.getStatus();
      address = order.getDelivery().getAddress();
    } }
}
