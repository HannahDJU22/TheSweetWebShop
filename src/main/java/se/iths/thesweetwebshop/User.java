package se.iths.thesweetwebshop;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ShoppingOrder> shoppingOrders= new ArrayList<>();


    public void addOrderToUser(ShoppingOrder shoppingOrder){
        shoppingOrders.add(shoppingOrder);

    }

    public List<ShoppingOrder> getShoppingOrders() {
        return shoppingOrders;
    }

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
