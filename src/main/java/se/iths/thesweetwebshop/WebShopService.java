package se.iths.thesweetwebshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class WebShopService {
    User user;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;

    boolean isLoggedin;
    ArrayList<OrderLine> shoppingcart = new ArrayList<>();
    List<ShoppingOrder> shoppingOrders = new ArrayList<>();


    public void addItemToShoppingCart(Long id, int quantity) {
        Product item = productRepository.findById(id).get();
        OrderLine orderLine = new OrderLine(item, quantity);
        shoppingcart.add(orderLine);
    }

    public void login(String username) {
        if (isLoggedin) return;
        List<User> userList = userRepository.findByName(username);
        if (userList.size() > 0) {
            user = userList.get(0);
        } else {
            user = new User(username);
            user = userRepository.save(user);
        }
        isLoggedin = true;
    }

    public List<Product> getItemsByCategory(ProductCategory itemCategory) {
        List<Product> productList;
        switch (itemCategory) {
            case CANDY:
                productList = productRepository.findByItemCategory(ProductCategory.CANDY);
                break;
            case ICECREAM:
                productList = productRepository.findByItemCategory(ProductCategory.ICECREAM);
                break;
            case SNACKS:
                productList = productRepository.findByItemCategory(ProductCategory.SNACKS);
                break;
            default:
                productList = new ArrayList<>();
        }
        return productList;
    }

    public void addNewItem(Product product) {
        productRepository.save(product);
    }

    public List<Product> showItem(String product) {
        List<Product> itemList = productRepository.findByItemName(product);
        return itemList;
    }

    public ArrayList<OrderLine> showCart() {
        return shoppingcart;
    }

    public double totalAmount(List<OrderLine> orderLines) {
        double amount = 0;
        for (OrderLine line : orderLines) {
            double lineTotal = line.getQuantity() * line.getItem().getItemPrice();
            line.setLineAmount(lineTotal);
            amount += lineTotal;
        }
        return amount;
    }

    public void removeOneFromCart(OrderLine orderLine) {
        orderLine.quantity--;
    }

    public void addOneToCart(OrderLine orderLine) {
        orderLine.quantity++;
    }

    public void deleteFromCart(OrderLine orderLine) {
        shoppingcart.remove(orderLine);
    }

    public void saveShoppingOrder() {
        //ShoppingOrder shoppingOrder = new ShoppingOrder(user, false, shoppingcart);
        //shoppingOrder=orderRepository.save(shoppingOrder);
        user.addOrderToUser(new ShoppingOrder(user, false, shoppingcart));
        user=userRepository.save(user);
        //System.out.println("hallå listan" + shoppingcart);
        shoppingcart.clear();
    }
    public ShoppingOrder getUsersOrder(){
        return user.getShoppingOrders().get(user.getShoppingOrders().size()-1);

    }
    public List<ShoppingOrder> showNonDeliveredOrders(){
        shoppingOrders = orderRepository.findByDelivered(false);
        return shoppingOrders;
    }
    public void updateOrderToDelivered(Long id){
        ShoppingOrder shoppingOrder=orderRepository.findById(id).get();
        shoppingOrder.setDelivered(true);
        orderRepository.save(shoppingOrder);
    }

    public List<ShoppingOrder> showAllDeliveredOrders() {
        shoppingOrders=orderRepository.findByDelivered(true);
        return shoppingOrders;
    }



/*    public List<Product> findAllProducts(){
        List<Product> allProducts = productRepository.findAll();
        return allProducts;
    }*/
}
