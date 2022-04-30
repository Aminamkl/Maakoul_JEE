package org.sid.ecom.mappers;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.sid.ecom.dtos.CategoryDTO;
import org.sid.ecom.dtos.ProductDTO;
import org.sid.ecom.entities.Category;
import org.sid.ecom.entities.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CalatogMappers {
    public ProductDTO fromproduct(Product product){
        ProductDTO productDTO=new ProductDTO();
        BeanUtils.copyProperties(product,productDTO);
        productDTO.setCategoryDTO(fromCategory(product.getCategory()));
        return productDTO;
    }

    public Product fromProductDTO(ProductDTO productDTO){
        Product product=new Product();
        BeanUtils.copyProperties(productDTO,product);
        product.setCategory(fromCategoryDTO(productDTO.getCategoryDTO()));
        return product;
    }

    public CategoryDTO fromCategory(Category category){
        CategoryDTO categoryDTO=new CategoryDTO();
        BeanUtils.copyProperties(category,categoryDTO);
        return  categoryDTO;
    }
    public Category fromCategoryDTO(CategoryDTO categoryDTO){
        Category category=new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        return  category;
    }
}
