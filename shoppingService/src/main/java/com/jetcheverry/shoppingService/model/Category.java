package com.jetcheverry.shoppingService.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Category {

    private Long id;

    private String name;
}
