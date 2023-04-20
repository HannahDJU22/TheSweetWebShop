package se.iths.thesweetwebshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WebShopServiceTest {

    WebShopService webShopService;

    Product product1;
    Product product2;

    @BeforeEach
    void setUp() {
        product1=new Product("godis", 12.0, ProductCategory.CANDY);
        product2=new Product("glass", 20.0, ProductCategory.ICECREAM);
        this.webShopService=new WebShopService();
    }

    @Test
    void multipleItemsShouldMatchTotalAmount(){
        List<OrderLine> shoppingCart = new ArrayList<>();
        OrderLine oLine=new OrderLine(product1, 3);
        shoppingCart.add(oLine);
        assertEquals(36, webShopService.totalAmount(shoppingCart));
    }

    @Test
    void multipleProductsShouldMatchTotalAmount() {
        List<OrderLine> shoppingCart= new ArrayList<>();
        OrderLine oLine= new OrderLine(product1, 2);
        OrderLine oLine2= new OrderLine(product2, 3);
        shoppingCart.add(oLine);
        shoppingCart.add(oLine2);
        assertEquals(84, webShopService.totalAmount(shoppingCart));
    }


}
