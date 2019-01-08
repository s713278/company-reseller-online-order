package org.mysoft.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name="PRODUCT_INFO")
public class ProductVO implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ID", updatable = false, nullable = false)
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PRICE")
	private Double price;
	
	@Column(name="STOCK_LEVEL")
	private Long stockLevel;
	
	
	@Column(name="STOCK_THRESHOLD_LEVEL")
	private Integer stockThresholdLevel;
	
	@CreationTimestamp
	@Column(name="CREATED_DATE")
	@JsonIgnore
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	@Column(name="MODIFIED_DATE")
	@JsonIgnore
	private LocalDateTime modifiedDate;
	
}
