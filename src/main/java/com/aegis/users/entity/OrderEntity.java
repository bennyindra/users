package com.aegis.users.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ORDERS")
@Data
public class OrderEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
