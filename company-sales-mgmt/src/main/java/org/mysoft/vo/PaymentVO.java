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

import lombok.Data;

@Entity
@Data
@Table(name="PAYMENT_INFO")
public class PaymentVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1673641455227209496L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="id", updatable = false, nullable = false)
	private Long id;
	
	private Double amout;
	
	private String paymentType;
	
	private String paymentStatus;
	
	@CreationTimestamp
	private LocalDateTime dueDate;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	private LocalDateTime modifiedDate;
}
