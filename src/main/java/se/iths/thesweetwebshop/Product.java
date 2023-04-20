package se.iths.thesweetwebshop;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String itemName;
    private double itemPrice;
    @Enumerated(EnumType.STRING)
    private ProductCategory itemCategory;

    public Product() {
    }

    public Product(String itemName, double itemPrice, ProductCategory itemCategory) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public ProductCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ProductCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Item Category: " + itemCategory + " -- " + "Item Name: " + itemName + " -- " + "Item Price: " + itemPrice;
    }
}

