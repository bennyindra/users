package com.aegis.users.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Table(name = "users")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class UserEntity extends BaseEntity{

    public UserEntity(String email, String password, String name){
        this.setEmail(email);
        this.setPassword(password);
        this.setName(name);
    }

    @Size
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "active")
    private boolean active = Boolean.TRUE;

    @OneToMany(mappedBy = "user")
    @Fetch(FetchMode.SELECT)
    private Set<OrderEntity> orders = new HashSet<>();
}
