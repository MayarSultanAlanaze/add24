package com.example.demo.Service;

import com.example.demo.ApiException.ApiException;
import com.example.demo.Model.Product;
import com.example.demo.Repository.RepositoryProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final RepositoryProduct repositoryProduct;

    public List<Product> getAllProducts(){
        return repositoryProduct.findAll();
    }


    public void addProduct(Product product){
        repositoryProduct.save(product);
    }
    public void updateProduct(Integer id, Product product){
        Product oldProduct=repositoryProduct.findProductById(id);
        if(oldProduct==null){
            throw new ApiException("not found ");
        }

        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setOrderSet(product.getOrderSet());
        repositoryProduct.save(oldProduct);
    }
    public void deleteBlog(Integer id){

        Product oldProduct=repositoryProduct.findProductById(id);
        if(oldProduct==null){
            throw new ApiException("not found ");
        }
        repositoryProduct.deleteById(id);
    }

    public Product findProductById(Integer id){
       Product product=repositoryProduct.findProductById(id);
        if(product==null){
            throw new ApiException("not found");
        }
        return product;
    }

}
