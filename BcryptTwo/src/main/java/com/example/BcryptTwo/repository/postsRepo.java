package com.example.BcryptTwo.repository;

import com.example.BcryptTwo.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface postsRepo extends JpaRepository<Posts, Long> {
}
