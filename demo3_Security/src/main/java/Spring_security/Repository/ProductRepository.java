package Spring_security.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Spring_security.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	

}
