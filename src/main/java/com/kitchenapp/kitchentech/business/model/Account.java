package com.kitchenapp.kitchentech.business.model;

import com.kitchenapp.kitchentech.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//La cuenta tiene que contener, la orden, el cliente y la mesa
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = true)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = true)
    private Order order;

    @Column(name = "restaurant_id", nullable = false)
    private long restaurantId;

    @Column(name="state",nullable = false)
    private String state;

    @Column(name = "total_account",nullable = false)
    private Float totalAccount;

    @Column(name = "total_guests",nullable = false)
    private int totalGuests;

    @Column(name = "date_created",nullable = false)
    private LocalDate dateCreated;

    @Column(name = "date_log",nullable = false)
    private LocalDate dateLog;

    //falta mesas

}
