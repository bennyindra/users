package com.aegis.users.controller;

import com.aegis.users.dto.UserRequestDTO;
import com.aegis.users.dto.UserResponseDTO;
import com.aegis.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class UserController {

    private final UserService userService;

    private static final List<String> ALLOWED_SORT_FIELDS = List.of("email", "name", "created_at");

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@RequestBody @Valid UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(userService.save(userRequestDTO));
    }

    @GetMapping
    public ResponseEntity<Object> findAllUsers(@RequestParam(required = false) String search,
                                                  @RequestParam(required = false, defaultValue = "1") int page,
                                                  @RequestParam(required = false, defaultValue = "created_at") String sortBy) {
        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            return ResponseEntity.badRequest().body(Map.of("error", List.of("Invalid sort field")));
        } else {
            return ResponseEntity.ok(userService.getUsers(search, PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, sortBy))));
        }
    }


}
