package com.lti.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Integer productId;
	    private String productName;
	    @Column(length = 2000)
	    private String productDescription;
	    private Double productDiscountedPrice;
	    private Double productActualPrice;
	    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	    @JoinTable(name = "product_images",
	            joinColumns = {
	                    @JoinColumn(name = "product_id")
	            },
	            inverseJoinColumns = {
	                    @JoinColumn(name = "image_id")
	            }
	    )
	    private Set<ImageModel> productImages;

}
