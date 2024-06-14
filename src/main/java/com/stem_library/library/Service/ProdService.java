package com.stem_library.library.Service;

import com.stem_library.library.Entity.CartItem;
import com.stem_library.library.Entity.Product;
import com.stem_library.library.Repository.CartItemRepo;
import com.stem_library.library.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProdService {

    @Autowired
    private CartItemRepo cartItemRepo;
    @Autowired
    ProductRepository productRepository;




    public void saveProduct(MultipartFile image, Product product) throws IOException {

        byte[] imageData = image.getBytes();
        product.setImage(imageData);
        product.setImageType(image.getContentType());
        productRepository.save(product);

    }

    public Product getProdByid(Long id){
        return productRepository.findById(id).orElse(null);

    }
    public List<Product> findAllProd(){

        return productRepository.findAll();
    }

    public  Product getByPodId(String prodId){

        return productRepository.findByProdId(prodId);

    }








}








