package org.sid.ecom.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.ecom.entities.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor @NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private List<Product> productList=new ArrayList<>();
}
