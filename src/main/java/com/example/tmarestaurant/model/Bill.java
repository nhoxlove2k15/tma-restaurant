package com.example.tmarestaurant.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "bills")
//@Table(name = "bills")
@Table
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id ;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_details_id")
    private BillDetail billDetails;
    @Column(name = "totalprice")
    private double totalprice;
}
