package com.example.demo;

import com.example.demo.Model.MyUser;
import com.example.demo.Model.Order;
import com.example.demo.Model.Product;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Repository.RepositoryUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class RepositoryOrderTest {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    RepositoryUser repositoryUser;
    MyUser myUser;

    Product product;
    Order order1,order2,order3,order4;
    List<Order>orderList;


    @BeforeEach
    void setUp(){
        myUser=new MyUser(null,"Mayar","1213141","CUSTOMER",null);
        order1=new Order(null,5,6000,100,"new",myUser,product);
        order2=new Order(null,10,4500,100,"inProgress",myUser,product);
        order3=new Order(null,19,250,25,"new",myUser,product);
        order4=new Order(null,15,500,25,"completed",myUser,product);
    }

    @Test
    public void findAllByMyUser(){
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderRepository.save(order4);
        List<Order>orderList=orderRepository.findAllByMyUser(myUser);
        Assertions.assertThat(orderList.get(0).getMyUser().getId()).isEqualTo(myUser);
    }
    @Test
    public void findOrderById(){
            orderRepository.save(order1);
             Order orders=orderRepository.findOrderById(order1.getId());
            Assertions.assertThat(orders).isEquualTo(order1);
        }

    public void findOrderById(){
        orderRepository.save(order2);
        Order orders=orderRepository.findOrderById(order2.getId());
        Assertions.assertThat(orders).isEquualTo(order2);
    }
    public void findOrderById() {
        orderRepository.save(order3);
        Order orders = orderRepository.findOrderById(order3.getId());
        Assertions.asserThat(orders).isEquualTo(order3);
    }
    public void findOrderById() {
        orderRepository.save(order4);
        Order orders = orderRepository.findOrderById(order4.getId());
        Assertions.assertThat(orders).isEquualTo(order4);
    }

}
