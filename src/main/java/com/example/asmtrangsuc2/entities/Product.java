package com.example.asmtrangsuc2.entities;

import java.sql.Date;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "image")
	private String image;

	@Column(name = "price")
	private double price;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "available")
	private Integer available;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category categoryById;
	
	@OneToMany(mappedBy = "productById")
	private List<OrderDetail> orderDetails;
	
	@Column(name = "status")
	private Integer status;

	@Column(name = "quantity")
	private Integer quantity;
}
