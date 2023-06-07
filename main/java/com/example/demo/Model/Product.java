package com.example.demo.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "username shouldn't be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String name;


    @NotNull(message = "Price Can't Be Null")
    @Column(columnDefinition = "varchar(5) not null")
    private Integer price;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private Set<Order> orderSet;
}


