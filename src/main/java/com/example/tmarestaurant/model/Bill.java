package com.example.tmarestaurant.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @OneToOne
    private BillDetail billDetails;
    @Column(name = "totalprice")
    private double totalprice;
}
