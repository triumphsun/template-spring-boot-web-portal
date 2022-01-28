package com.suntri.portal.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
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

    @Column
    @ManyToMany(
        cascade = CascadeType.PERSIST
    )
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name = "uid"),
        inverseJoinColumns = @JoinColumn(name = "rid")
    )
    private Set<Role> roles;

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private String username;
        private String password;
        private String email;
        private Set<Role> roles = new HashSet<>();

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
            return user;
        }

    }
}
