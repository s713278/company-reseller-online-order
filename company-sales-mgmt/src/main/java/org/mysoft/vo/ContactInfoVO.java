package org.mysoft.vo;

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
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "CONTACT_INFO")
@ApiModel(value = "ContactInfo")
public class ContactInfoVO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;

	@JsonProperty(defaultValue = "DLF 9th FLoor")
	@ApiModelProperty(example = "DLF 9th FLoor")
	private String adress1;

	@JsonProperty(defaultValue = "Gachibowli")
	@ApiModelProperty(example = "Gachibowli")
	private String adress2;

	@JsonProperty(defaultValue = "Telangana")
	@ApiModelProperty(example = "Telangana")
	private String state;

	@JsonProperty(defaultValue = "Hyderabad")
	@ApiModelProperty(example = "Hyderabad")
	private String city;

	@JsonProperty(defaultValue = "India")
	@ApiModelProperty(example = "India")
	private String country;

	@JsonProperty(defaultValue = "500049")
	@ApiModelProperty(example = "500049")
	private String zipCode;

	private String webSite;
	private String email;
	private String faxNo;
	private String mobileNo;
	private String landLine;

	@JsonIgnore
	@CreationTimestamp
	private LocalDateTime createdDate;

	@JsonIgnore
	@UpdateTimestamp
	private LocalDateTime modifiedDate;
}
