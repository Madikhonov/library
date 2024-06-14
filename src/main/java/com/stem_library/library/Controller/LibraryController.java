package com.stem_library.library.Controller;
import com.stem_library.library.Entity.CartItem;
import com.stem_library.library.Entity.Product;
import com.stem_library.library.Service.CartService;
import com.stem_library.library.Service.ProdService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class LibraryController {
    @Autowired
    ProdService prodService;
    @Autowired
    CartService cartService;

    @GetMapping("/invoice-list")
    public String invoiceList(Model model) {

        return "invoice-list";
    }

    @GetMapping("/invoice-summary")
    public String invoiceSummary(Model model) {

        return "invoice-summary";
    }

    @GetMapping("/invoice-template")
    public String invoiceTemplate(Model model) {

        return "invoice-template";
    }

    @GetMapping("/ecommerce-order")
    public String orderRender() {

        return "ecommerce-order";
    }

    @GetMapping("/ecommerce-product-shop")
    public String library(Model model){

    List<Product> prod = prodService.findAllProd();
        model.addAttribute("products",prod);

        return"ecommerce-product-shop";

    }



    @GetMapping("product/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id){
        Product pr = prodService.getProdByid(id);

        byte[] image = pr.getImage();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/*")
                .body(image);

    }
    @GetMapping("prod/upload")
    public String uploadProd(){

        return "image";
    }

    @PostMapping("/prod/add")
    public String addProduct(@RequestParam("prodId") String prodId, @RequestParam("name") String name,
                             @RequestParam("price") double price, @RequestParam("quantity") int quantity,
                             @RequestParam("image")MultipartFile image) throws IOException {
        Product product = new Product();
        product.setProdId(prodId);
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setImageType(image.getContentType());
        prodService.saveProduct(image, product);


        return "redirect:/ecommerce-product-shop";
    }


    @GetMapping("/ecommerce-shopping-cart")
    public String cart(Model model, HttpSession session) {
        List<CartItem> cartItems = cartService.getCartItems(session);
        model.addAttribute("cartItems", cartItems);
        return "ecommerce-shopping-cart";
    }


    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("productId") Long productId,
                            @RequestParam("quantity") int quantity,
                            HttpSession session) {
        Product product = prodService.getProdByid(productId);
        cartService.addToCart(product, quantity, session);
        return "redirect:/ecommerce-shopping-cart";
    }


    public void newFunc(){

    }


}
