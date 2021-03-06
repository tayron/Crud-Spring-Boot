package br.com.tutorial.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;	

@Entity
@Table(name="\"user\"")
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
	private String password;
	
	@Transient
	private String passwordEditable;
	
	@Transient
	private String passwordConfirm;	
	
    @ManyToOne    
	private Role role;

    @Column
    @CreationTimestamp
    private LocalDateTime created;	
    
    @Column
    @UpdateTimestamp
    private LocalDateTime updated;

    
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

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}


}
