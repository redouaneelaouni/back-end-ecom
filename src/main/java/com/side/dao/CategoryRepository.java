package com.side.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.side.entites.Category;
@RepositoryRestResource
@CrossOrigin(origins="*")
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
