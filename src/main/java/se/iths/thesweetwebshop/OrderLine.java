package se.iths.thesweetwebshop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderLine {

    @GeneratedValue()
    @Id
    private Long id;

    @ManyToOne
    Product item;
    int quantity;
    double lineAmount;

    public OrderLine(Product item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public OrderLine() {
    }

    public Long getId() {
        return id;
    }

    public Product getItem() {
        return item;
    }

    public void setItem(Product item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getLineAmount() {
        return lineAmount;
    }

    public void setLineAmount(double lineAmount) {
        this.lineAmount = lineAmount;
    }
}
