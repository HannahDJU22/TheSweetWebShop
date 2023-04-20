package se.iths.thesweetwebshop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<ShoppingOrder, Long> {

    List<ShoppingOrder> findByDelivered(boolean delivered);
}
