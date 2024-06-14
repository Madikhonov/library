package com.stem_library.library.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prod_id", unique = true)
    private String prodId;

    @Column(name = "prod_name", unique = true)
    private String name;

    @Column(name = "prod_price")
    private double price;

    @Column(name = "prod_quant")
    private int quantity;

    @Lob
    @Column(name = "prod_image",  columnDefinition = "LONGBLOB")
    private byte[]  image;

    @Column(name = "prod_image_type")
    private String imageType;


}


