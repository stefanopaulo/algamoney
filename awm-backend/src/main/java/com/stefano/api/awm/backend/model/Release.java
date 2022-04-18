package com.stefano.api.awm.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "release_tb")
public class Release {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "description_rls")
	private String description;
	
	@NotNull
	@Column(name = "due_date")
	private LocalDate dueDate;
	
	@Column(name = "payment_date")
	private LocalDate paymentDate;
	
	@NotNull
	@Column(name = "value_rls")
	private BigDecimal value;
	
	private String note;
	
	@NotNull
	@Column(name = "type_rls")
	@Enumerated(EnumType.STRING)
	private TypeRelease type;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_category")
	private Category category;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_person")
	private Person person;

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public BigDecimal getValue() {
		return value;
	}

	public String getNote() {
		return note;
	}

	public TypeRelease getType() {
		return type;
	}

	public Category getCategory() {
		return category;
	}

	public Person getPerson() {
		return person;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setType(TypeRelease type) {
		this.type = type;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setPerson(Person person) {
		this.person = person;
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
		Release other = (Release) obj;
		return Objects.equals(id, other.id);
	}

}
