package com.example.asmtrangsuc2.models;

import java.io.Serializable;


import com.example.asmtrangsuc2.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartModel implements Serializable{
	private Product product;
	private int quantity;
}
