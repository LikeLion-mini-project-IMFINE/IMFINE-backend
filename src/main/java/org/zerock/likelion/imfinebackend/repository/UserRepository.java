package org.zerock.likelion.imfinebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.likelion.imfinebackend.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
