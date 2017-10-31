package org.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String username;

    @JsonIgnore
    @Column(length = 50, nullable=false)
    private String password;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(length = 100, unique = true)
    private String email;

    @JsonIgnore
    @ManyToMany(targetEntity = Role.class,fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> userAuthotities = new HashSet<>();
        for(Role role : this.roles){
            for(Authority authority : role.getAuthorities()){
                userAuthotities.add(new SimpleGrantedAuthority(authority.getValue()));
            }
        }

        return userAuthotities;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
