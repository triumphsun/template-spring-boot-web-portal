package com.suntri.portal.entity;

import javax.persistence.*;
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

    public String getName() {
        return this.name;
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
            return role;
        }
    }
}
