package com.rko.myspring.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Table(

		uniqueConstraints = @UniqueConstraint(name = "unique_email",columnNames = {"email"})
)
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_id")
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private Integer birthdayDate;

    @Email
	@NotBlank
    private String email;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")

    private String password;

	@Column(name = "active")
	private int active;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@ManyToMany
    private List<User> amis = new ArrayList<User>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(Integer birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFullName() {
		return getLastName() + " " + getFirstName();
	}

	public List<User> getAmis() {
		return amis;
	}

	public void setAmis(List<User> amis) {
		this.amis = amis;
	}

	public void addAmis(User user){
		this.amis.add(user);
	}

	public User getAmis(long id){
		return this.amis.get((int) id);
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


}
