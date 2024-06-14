package com.stem_library.library.Repository;

import com.stem_library.library.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long>{
 CartItem findByProductId(Long productId); 
}
