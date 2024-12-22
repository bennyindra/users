package com.aegis.users.service;

import com.aegis.users.dto.SimplePageDTO;
import com.aegis.users.dto.UserRequestDTO;
import com.aegis.users.dto.UserResponseDTO;
import com.aegis.users.entity.UserEntity;
import com.aegis.users.event.SendEmailEvent;
import com.aegis.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserResponseDTO save(UserRequestDTO userRequest){
        UserEntity userEntity = userRepository.save(new UserEntity(userRequest.getEmail(), userRequest.getPassword(), userRequest.getName()));
        applicationEventPublisher.publishEvent(new SendEmailEvent(userEntity));
        return modelMapper.map(userRepository.save(userEntity), UserResponseDTO.class);
    }

    @Transactional(readOnly = true)
    public SimplePageDTO<List<UserResponseDTO>> getUsers(String search, Pageable pageable){
        Page<UserEntity> result = userRepository.findByNameOrEmail(search, search, pageable);
        List<UserResponseDTO> userResponseDTOList =
                result.getContent().stream().map(userEntity -> new UserResponseDTO(userEntity.getId(), userEntity.getName(), userEntity.getEmail(), userEntity.getCreatedAt(), userEntity.getOrders().size())).toList();
        return new SimplePageDTO<>(pageable.getPageNumber() + 1, result.getTotalPages(), userResponseDTOList);
    }
}
