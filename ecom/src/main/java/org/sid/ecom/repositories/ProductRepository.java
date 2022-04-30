package org.sid.ecom.repositories;

import org.sid.ecom.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, String> {

}
