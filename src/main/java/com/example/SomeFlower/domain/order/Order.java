package com.example.SomeFlower.domain.order;

import com.example.SomeFlower.domain.userGroup.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity @Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //주로 acces하는 order를 연관관계 주인으로 설정

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderPlant> orderItems;

    private int totalPrice;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}
