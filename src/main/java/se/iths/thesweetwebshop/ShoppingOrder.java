package se.iths.thesweetwebshop;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingOrder {
    @Id
    @GeneratedValue
    private Long id;

    boolean delivered;

    @ManyToOne
    User user;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<OrderLine> orderLines = new ArrayList<>();

    public ShoppingOrder() {
    }

    public ShoppingOrder(User user, boolean delivered, List<OrderLine> orderLines) {
        this.user = user;
        this.delivered = delivered;
        this.orderLines = orderLines;
    }

    public User getUser() {
        return user;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLineList) {
        this.orderLines = orderLineList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    @Override
    public String toString() {
        return "ShoppingOrder{" +
                "id=" + id +
                ", delivered=" + delivered +
                ", user=" + user +
                ", orderLines=" + orderLines +
                '}';
    }
}
