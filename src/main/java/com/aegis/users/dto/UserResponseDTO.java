package com.aegis.users.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO implements Serializable {
    private Long id;
    private String email;
    private String name;
    private LocalDateTime createdDate;

    private Integer ordersCount;
    public UserResponseDTO(Long id, String name, String email, LocalDateTime createdDate, Integer ordersCount){
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setCreatedDate(createdDate);
        this.setCreatedDate(LocalDateTime.now());
        this.setOrdersCount(ordersCount);
    }
}
