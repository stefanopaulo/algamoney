package com.stefano.api.awm.backend.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "person")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	@Column(name = "is_active")
	private Boolean active;
	
	@Embedded
	private Address address;

	public Person() {
	}

	public Person(Long id, @NotNull String name, @NotNull Boolean active, Address address) {
		this.id = id;
		this.name = name;
		this.active = active;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Boolean getActive() {
		return active;
	}

	public Address getAddress() {
		return address;
	}
	
	@JsonIgnore
	@Transient
	public boolean isInativo() {
		return !this.active;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", active=" + active + ", address=" + address + "]";
	}
	
}
