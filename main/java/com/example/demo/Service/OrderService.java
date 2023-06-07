package com.example.demo.Service;

import com.example.demo.ApiException.ApiException;
import com.example.demo.Model.MyUser;
import com.example.demo.Model.Order;
import com.example.demo.Model.Product;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Repository.RepositoryProduct;
import com.example.demo.Repository.RepositoryUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderService {

        private final RepositoryUser repositoryUser;
        private final RepositoryProduct repositoryProduct;
        private final OrderRepository orderRepository ;

        public List<Order> getOrders(Integer MyuserId) {
            return orderRepository.findOrderByMyUserId(MyuserId);
        }

        public void addOrder(Integer myUserId, Order order){
            Product product=repositoryProduct.findProductById(order.getProduct().getId());
            User myUser=repositoryUser.findMyUserBy(myUserId);
            order.setMyUser(myUser);
            order.setStatus("new");
            order.setTotalPrice(order.getQuantity()* product.getPrice());
            orderRepository.save(order);
        }
        public void updateOrder(Integer userId, Order order, Integer ordered){

            Order oldOrder=orderRepository.findOrderById(ordered);
            if(oldOrder==null) {
                throw new ApiException("not found");
            }

            if(oldOrder.getMyUser().getId()!=userId) {
                throw new ApiException(" Order doesn't belong to you!");
            }

            oldOrder.setQuantity(order.getQuantity());
             oldOrder.setStatus(order.getStatus());
            oldOrder.setDateReceived(order.getDateReceived());
            oldOrder.setProduct(order.getProduct());
            orderRepository.save(oldOrder);
        }
        public void deleteOrder(Integer userId, Integer ordered){

            Order oldOrder=orderRepository.findOrderById(ordered);
            if(oldOrder==null) {
                throw new ApiException("order delete");
            }

            if(oldOrder.getMyUser().getId()!=userId) {
                throw new ApiException("Order doesn't belong to you");
            }

            if(oldOrder.getStatus().equals("inProgress")||oldOrder.getStatus().equals("completed")) {
                throw new ApiException(" Order it's Even Completed or already in progress");
            }
            orderRepository.delete(oldOrder);
        }

        public List<Order> getAllOrders(){
            return orderRepository.findAll();
        }

        public Order findOrderById(Integer id){
            Order order=orderRepository.findOrderById(id);
            if(order==null){
                throw new ApiException("not found");
            }
            return order;
        }

        public void updateStatus(Integer userId, Integer ordered,String status){

            Order oldOrder=orderRepository.findOrderById(ordered);
            if(oldOrder==null) {
                throw new ApiException("not found");
            }

            if(oldOrder.getMyUser().getId()!=userId) {
                throw new ApiException(" Order doesn't belong to you");
            }

            oldOrder.setStatus(status);
            orderRepository.save(oldOrder);
        }

    public List<Order> getOrders() {
            return null;
    }
}

