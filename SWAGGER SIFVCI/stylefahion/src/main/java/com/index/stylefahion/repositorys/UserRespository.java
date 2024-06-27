package com.index.stylefahion.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.index.stylefahion.models.UserEntity;

public interface UserRespository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findUserEntityByUsername(String username);

}
