package com.example.tmarestaurant.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @SequenceGenerator(
            name = "bill_sequence",
            sequenceName = "bill_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bill_sequence"
    )
    private Long id ;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "category_id", insertable = false, updatable = false)
//    private Category category;
//    @Column(name = "category_id")
//    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id" )
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user ;
//    @Column(name = "user_id")
//    private Long userId ;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_details_id")
    private BillDetail billDetails = new BillDetail();
//    @Column(name = "bill_details_id")
//    private Long billDetailsId;

    @Column(name = "totalprice")
    private double totalprice;

    @Column(name = "ordered_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime orderedTime = LocalDateTime.now();

    public Bill() {
        this.user = new User();
    }

    public Bill(User user, BillDetail billDetails, double totalprice) {
        this.user = user;
        this.billDetails = billDetails;
        this.totalprice = totalprice;
    }

    public Bill(Long userId,  Long billDetailsId) {
        this.getUser().setId(userId);
        this.getBillDetails().setId(billDetailsId);
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(LocalDateTime orderedTime) {
        this.orderedTime = orderedTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BillDetail getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(BillDetail billDetails) {
        this.billDetails = billDetails;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }
}
