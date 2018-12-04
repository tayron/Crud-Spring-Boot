package br.com.tutorial.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Institution {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length = 30)
	@NotBlank(message = "The name can't be blank")
	@Size(min = 2, max = 30, message = "The name must between 2 and 30 characters")
	private String name;	
	
	@Column(length = 100)
	@NotBlank(message = "The address can't be blank")
	@Size(min = 2, max = 100, message = "The address must between 2 and 100 characters")	
	private String address;
	
	@OneToMany(mappedBy = "institution", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Student> student;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}
}
