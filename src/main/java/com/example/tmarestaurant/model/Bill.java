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

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "category_id", insertable = false, updatable = false)
//    private Category category;
//    @Column(name = "category_id")
//    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",insertable = false, updatable = false )
    private User user;
    @Column(name = "user_id")
    private Long userId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_details_id", updatable = false, insertable = false)
    private BillDetail billDetails;
    @Column(name = "bill_details_id")
    private Long billDetailsId;

    @Column(name = "totalprice")
    private double totalprice;


    public Bill(User user, BillDetail billDetails, double totalprice) {
        this.user = user;
        this.billDetails = billDetails;
        this.totalprice = totalprice;
    }

    public Bill(Long userId,  Long billDetailsId) {
        this.userId = userId;
        this.billDetailsId = billDetailsId;
    }

}
