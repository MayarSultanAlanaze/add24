package com.example.demo;

import com.example.demo.Model.MyUser;
import com.example.demo.Model.Order;
import com.example.demo.Model.Product;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Repository.RepositoryUser;
import com.example.demo.Service.OrderService;
import com.example.demo.Service.ServiceUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceOrderTest {
    @InjectMocks
    OrderService orderService;
    @Mock
    RepositoryUser repositoryUser;
    @Mock
    OrderRepository orderRepository;


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

      orderList=new ArrayList<>();
      orderList.add(order1);
       orderList.add(order2);
       orderList.add(order3);
      orderList.add(order4);
    }
    @Test
    public void getOrders() {
     when(orderRepository.findAll()).thenReturn(orderList);
     List<Order>orders=orderService.getOrders(myUser.getId());
       Assertions.assertEquals(orders,orderList);
      Assertions.assertEquals(6,orderList.size());
     verify(orderRepository,times(1)).findAll();

    }
        @Test
        public void addOrderTest(){
            when(repositoryUser.findMyUserBy(myUser.getId())).thenReturn(myUser);
    orderService.addOrder(myUser.getId(),order4);
    verify(repositoryUser,times(1)).findMyUserBy(myUser.getId());
     verify(orderRepository,times(1)).save(order4);
    }
    @Test
    public void updateOrder(){
   when(orderRepository.findOrderById(order3.getId())).thenReturn(order3);
     when(repositoryUser.findMyUserBy(myUser.getId())).thenReturn(myUser);
     orderService.updateOrder(order3.getId(),order3,myUser.getId());
       verify(orderRepository,times(1)).findOrderById(order3.getId());
     verify(repositoryUser,times(1)).findMyUserBy(myUser.getId());
        verify(orderRepository,times(1)).save(order3);

    }
    @Test
    public void deleteOrder(){
        when(orderRepository.findOrderById(order1.getId())).thenReturn(order1);
    orderService.deleteOrder(order1.getId(),myUser.getId());
    verify(orderRepository,times(1)).findOrderById(order1.getId());
      verify(orderRepository,times(1)).delete(order1);

    }

}
