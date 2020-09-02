package com.side;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.side.dao.CategoryRepository;
import com.side.dao.ProductRepository;
import com.side.entites.Category;
import com.side.entites.Product;

import net.bytebuddy.utility.RandomString;

@SpringBootApplication
public class EcomProjectApplication implements CommandLineRunner {
	
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration; 

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcomProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		repositoryRestConfiguration.exposeIdsFor(Product.class,Category.class);
		
		categoryRepository.save(new Category(null, "computers", null,null, null));
		categoryRepository.save(new Category(null, "printers", null,null, null));
		categoryRepository.save(new Category(null, "smartphone", null,null, null));
		Random rnd = new Random();

		categoryRepository.findAll().forEach(c -> {

			for (int i = 0; i < 10; i++) {
				Product product = new Product();
				product.setName(RandomString.make(18));
				product.setCurrentPrice(rnd.nextInt(10000) + 100);
				product.setAvailable(rnd.nextBoolean());
				product.setPromotion(rnd.nextBoolean());
				product.setSelected(rnd.nextBoolean());
				product.setPhotoName("unknown.png");
				product.setCategory(c);
				productRepository.save(product);
			}

		});
	}

}
