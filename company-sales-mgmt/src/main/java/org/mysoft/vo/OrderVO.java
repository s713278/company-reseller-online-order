package org.mysoft.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="ORDER_INFO")
public class OrderVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5740212406367417182L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="id", updatable = false, nullable = false)
	private Long id;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="ORDER_CART_ITEMS")
	@JoinColumn(name="order_id")
	List<CartItem> cartItems;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private SellerVO seller ;
	private Double subTotal;
	private Double tax;
	private Double disccount;
	private Double total;
	private String notesFromSeller;
	private Boolean allProductsInStock;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private PaymentVO paymentInfo;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	private LocalDateTime modifiedDate;
}
