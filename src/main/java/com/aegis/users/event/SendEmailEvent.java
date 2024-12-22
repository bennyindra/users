package com.aegis.users.event;

import com.aegis.users.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SendEmailEvent {

    private UserEntity user;

}
