package com.example.demo.repository;

import com.example.demo.model.LikeTable;
import com.example.demo.model.LikeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<LikeTable, LikeKey> {

    List<LikeTable> findByUserId(Long userId);
}
