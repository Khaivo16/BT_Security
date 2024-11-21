package Spring_security.Service;

import java.util.List;

import Spring_security.Entity.Product;

public interface ProductServices {
	
	void delete(Long id);
	
	Product get(Long id);
	
	Product save(Product product);
	
	List<Product> listAll();

}
