package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MyUser")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = " Can't Be Null")
    @Column(columnDefinition = "varchar(5) not null")
    private Integer quantity;


    @Column(columnDefinition = "varchar(5) not null")
    private Integer totalPrice;

    @NotNull(message = "Date Can't Be Null")
    @Column(columnDefinition = "varchar(5) not null")
    private Integer dateReceived;

    @NotEmpty(message = "Status can't be empty")
    @Column(columnDefinition = "varchar(25) not null check ( status='new' or status='inProgress' or status='completed')")
    private String status;

    @ManyToOne(cascade = CascadeType.ALL.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private MyUser myUser;

    @ManyToOne(cascade = CascadeType.ALL.PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnore
    private Product product;


}

