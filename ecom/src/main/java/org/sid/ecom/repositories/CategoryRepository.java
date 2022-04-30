package org.sid.ecom.repositories;

import org.sid.ecom.entities.Category;
import org.sid.ecom.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
