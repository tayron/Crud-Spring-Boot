package br.com.tutorial.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length = 85)
	@NotBlank(message = "The name can't be blank")
	@Size(min = 2, max = 85, message = "The name must between 2 and 85 characters")	
	private String name;
	
	@Column(length = 85)
	@NotBlank(message = "The email can't be blank")
	@Size(min = 2, max = 85, message = "The email must between 2 and 85 characters")	
	private String email;
	
	@Column(length = 1)
	private Boolean active = true;	
	
	@Column(length = 85)
	@NotBlank(message = "The name can't be blank")
	@Size(min = 6, max = 85, message = "The name must between 6 and 85 characters")	
	private String password;
	
	@Transient
	private String passwordEditable;
	
	@Transient
	private String passwordConfirm;	
	
    @ManyToOne    
	private Role role;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordEditable() {
		return passwordEditable;
	}

	public void setPasswordEditable(String passwordEditable) {
		this.passwordEditable = passwordEditable;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
