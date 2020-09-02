package com.side.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.side.dao.ProductRepository;
import com.side.entites.Product;
@CrossOrigin(origins = "*")
@RestController
public class CatalogueRestController {

	@Autowired
	ProductRepository productRepository;

	@GetMapping(path = "photoProduct/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
		Product product = productRepository.findById(id).get();

		return Files
				.readAllBytes(Paths.get(System.getProperty("user.home") + "/ecom/products/" + product.getPhotoName()));
	}

	@PostMapping(path = "/uploadPhoto/{id}")
	public void uploadPhoto(MultipartFile file, @PathVariable Long id) throws IOException {
		Product p = productRepository.findById(id).get();
		p.setPhotoName(id + ".jpg");
		Files.write(Paths.get(
		System.getProperty("user.home") + "/ecom/products/" + p.getPhotoName()), file.getBytes());
		productRepository.save(p);

	}

}
