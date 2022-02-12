package com.suntri.portal.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Column(name="DATE_CREATED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name="DATE_UPDATED")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
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
        private String name;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Role build(){
            if(this.name == null) throw new IllegalArgumentException();
            Role role = new Role();
            role.name = this.name;
            role.createdAt = new Date(System.currentTimeMillis());
            return role;
        }
    }
}
