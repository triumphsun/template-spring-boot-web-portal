package com.suntri.portal.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String email;

    @Column(name="DATE_CREATED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name="DATE_UPDATED")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @Column
    @ManyToMany(
        cascade = CascadeType.PERSIST,
        fetch = FetchType.EAGER
    )
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name = "uid"),
        inverseJoinColumns = @JoinColumn(name = "rid")
    )
    private Set<Role> roles;

    public Long getId(){
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private String username;
        private String password;
        private String email;
        private Set<Role> roles = new HashSet<>();
        private Date createdAt;

        private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        public Builder username(String username){
            this.username = username;
            return this;
        }

        public Builder password(String password){
            this.password = this.passwordEncoder.encode(password);
            return this;
        }

        public Builder role(Role role){
            this.roles.add(role);
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public User build(){
            if(this.username == null) throw new IllegalArgumentException();
            if(this.password == null) throw new IllegalArgumentException();
            User user = new User();
            user.username = this.username;
            user.password = this.password;
            user.email = this.email;
            user.roles = this.roles;
            user.createdAt = new Date(System.currentTimeMillis());
            return user;
        }

    }
}
