package com.rko.myspring.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Table(
		uniqueConstraints = @UniqueConstraint(name = "unique_email",columnNames = {"email"})
)
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;


    private Integer birthdayDate;

    @Email
	@NotEmpty
    private String email;

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

}
