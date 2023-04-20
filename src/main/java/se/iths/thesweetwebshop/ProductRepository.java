package se.iths.thesweetwebshop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
List<Product> findByItemCategory(ProductCategory productCategory);

List<Product> findByItemName(String itemName);


}
