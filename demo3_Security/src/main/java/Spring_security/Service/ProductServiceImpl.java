package Spring_security.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Spring_security.Entity.Product;
import Spring_security.Repository.ProductRepository;

public class ProductServiceImpl implements ProductServices{

	@Autowired
	private ProductRepository repo;
	
	public ProductServiceImpl(ProductRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public void delete(Long id) {
		repo.deleteById(id);
		
	}

	@Override
	public Product get(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public Product save(Product product) {
		return repo.save(product);
	}

	@Override
	public List<Product> listAll() {
		return repo.findAll();
	}

}
