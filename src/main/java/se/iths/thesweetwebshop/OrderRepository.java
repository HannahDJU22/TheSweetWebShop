package se.iths.thesweetwebshop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<ShoppingOrder, Long> {
}
