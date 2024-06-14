package com.stem_library.library.Repository;

import com.stem_library.library.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
 public Product findByProdId(String prodId);


}
