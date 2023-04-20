package se.iths.thesweetwebshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebShopController {
    @Autowired
    private WebShopService webShopService;

    @PostMapping("/login")
    public String loginReg(@RequestParam("usersname") String username, Model m) {
        webShopService.login(username);
        m.addAttribute("itemCategory", ProductCategory.values());
        return "categorypage";
    }

    @PostMapping("/admin")
    public String loginAdmin(@RequestParam("usersname") String username, Model m) {
        webShopService.login(username);
        return "adminpage";
    }

    @GetMapping("/admin")
    public String adminWork() {
        return "adminpage";
    }

    @GetMapping("/adminlogin")
    public String adminLoginForm() {

        return "adminlogin";
    }

    @PostMapping("/showitemspercategory")
    public String showProducts(@RequestParam("category") ProductCategory itemCategory, Model m) {
        m.addAttribute("productlist", webShopService.getItemsByCategory(itemCategory));
        return "productpage";
    }

    @PostMapping("/searchitem")
    public String searchedItems(@RequestParam("itemName") String product, @RequestParam("getitem") String getitem, Model m) {
        m.addAttribute("itemName", webShopService.showItem(product));

        return "itempage";
    }

    @GetMapping("/shoppingcart")
    public String showCart(Model m) {
        m.addAttribute("shoppingcart", webShopService.showCart());
        m.addAttribute("totalAmount", webShopService.totalAmount(webShopService.showCart()));
        return "shoppingcartpage";
    }

    @PostMapping("/shoppingcart")
    public String handleShoppingCart(Model m, @RequestParam("event") String event, @RequestParam("lineindex") int index) {
        OrderLine orderLine = webShopService.showCart().get(index);
        switch (event) {
            case "Remove one item":
                webShopService.removeOneFromCart(orderLine);
                break;
            case "Add one item":
                webShopService.addOneToCart(orderLine);
                break;
            case "Delete item/items":
                webShopService.deleteFromCart(orderLine);
                break;
        }
        m.addAttribute("shoppingcart", webShopService.showCart());
        m.addAttribute("totalAmount", webShopService.totalAmount(webShopService.showCart()));
        return "shoppingcartpage";
    }

    @PostMapping("/checkout")
    public String registerOrder(Model m) {
        webShopService.saveShoppingOrder();
        m.addAttribute("shoppingorder", webShopService.getUsersOrder());
        return "/ordersuccesspage";
    }

    @GetMapping("/login")
    public String showCategories(Model m) {
        m.addAttribute("itemCategory", ProductCategory.values());
        return "categorypage";
    }

    @PostMapping("/product")
    public String addItemToCart(Model m, @RequestParam Long id, @RequestParam int NoOfItems) {
        m.addAttribute("itemCategory", ProductCategory.values());
        webShopService.addItemToShoppingCart(id, NoOfItems);
        return "categorypage";
    }

    @PostMapping("/adminwork")
    String adminChoiceOfWork(String choice, Model m) {
        switch (choice) {
            case "Add new item":
                m.addAttribute("catValues", ProductCategory.values());
                m.addAttribute("product", new Product());
                return "newitempage";

            case "Delivered orders":
                m.addAttribute("listWithOrders", webShopService.showAllDeliveredOrders());
                return "deliveredpage";

            case "Non delivered orders":
                m.addAttribute("listWithOrders", webShopService.showNonDeliveredOrders());
                return "undeliveredpage";

        }
        return "adminpage";
    }

    @PostMapping("/undelivered")
    String setAsDelivered(Model m, @RequestParam String delivery, @RequestParam Long id) {

        if ("Set as delivered".equals(delivery)) {
            webShopService.updateOrderToDelivered(id);
        }
        m.addAttribute("listWithOrders", webShopService.showNonDeliveredOrders());
        return "undeliveredpage";
    }

    @PostMapping("/newitem")
    String addItem(Product product, Model m) {
        m.addAttribute("catValues", ProductCategory.values());
        m.addAttribute("product", new Product());
        webShopService.addNewItem(product);
        return "newitempage";
    }


}
