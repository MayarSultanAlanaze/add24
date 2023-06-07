package com.example.demo.Controller;

import com.example.demo.Model.MyUser;
import com.example.demo.Model.Order;
import com.example.demo.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Repository
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class ControllerOrder {


    private final OrderService orderService;
    @GetMapping("/get")
    public ResponseEntity getOrders(@AuthenticationPrincipal MyUser myUser){
        List<Order> orders=orderService.getOrders(myUser .getId());
        return ResponseEntity.status(200).body(orders);
    }
    @PostMapping("/add")
    public ResponseEntity addOrder(@AuthenticationPrincipal MyUser myUser, @RequestBody Order order){
        orderService.addOrder(myUser.getId(),order);
        return ResponseEntity.status(200).body("Order Generated successfully!");
    }


    @PutMapping("/update/{ordered}")
    public ResponseEntity updateToDo(@AuthenticationPrincipal MyUser myUser, @RequestBody Order order,@PathVariable Integer ordered){

        orderService.updateOrder(myUser.getId(), order,ordered);
        return ResponseEntity.status(200).body("order Updated");
    }

    @DeleteMapping("/delete/{ordered}")
    public ResponseEntity deleteToDo(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer ordered){
        orderService.deleteOrder(myUser.getId(),ordered);
        return ResponseEntity.status(200).body("order Deleted");
    }


    @GetMapping("/get-all")
    public ResponseEntity getAllBlogs(){
        List<Order> orders=orderService.getAllOrders();
        return ResponseEntity.status(200).body(orders);
    }


    @GetMapping("/get-id/{id}")
    public ResponseEntity findById(@PathVariable Integer id){
        Order order= orderService.findOrderById(id);
        return ResponseEntity.status(200).body(order);
    }

    @PutMapping("/update-status/{ordered}/{status}")
    public ResponseEntity updateStatus(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer ordered,@PathVariable String status){
        orderService.updateStatus(myUser.getId() ,ordered,status);
        return ResponseEntity.status(200).body("Status Updated");
    }


}
