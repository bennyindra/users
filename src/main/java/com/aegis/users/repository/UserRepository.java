package com.aegis.users.repository;

import com.aegis.users.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "SELECT * FROM USERS c WHERE (:name is null or c.name = :name) or (:email is null"
            + " or c.email = :email) and c.active = true", nativeQuery = true)
    Page<UserEntity> findByNameOrEmail(@Param("name") String name, @Param("email") String email, Pageable pageable);
}
