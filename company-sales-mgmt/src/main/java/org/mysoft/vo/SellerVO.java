package org.mysoft.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name="SELLER_INFO")
public class SellerVO implements Serializable{
	
	public SellerVO() {
		PORestriction = false;
		POLimit = 10000d;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="id", updatable = false, nullable = false)
	@JsonProperty(required = true,defaultValue="123")
	private Long id;
	
	@JsonProperty(required = true,defaultValue="Mayuri Health Pharmacy")
	private String name;
	//@JsonIgnore
	//private Clob desc;
	private Boolean PORestriction;
	private Double POLimit;
	
	@JsonIgnore
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@JsonIgnore
	@UpdateTimestamp
	private LocalDateTime modifiedDate;
	
	@JsonProperty(value="contactInfo",required=false)
	@OneToOne(fetch=FetchType.LAZY)
	private ContactInfoVO contactInfoVO;
}
