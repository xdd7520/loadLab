package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        logger.info("尝试创建用户: {}", user.getName());
        
        if (userRepository.existsByName(user.getName())) {
            logger.warn("用户名已存在: {}", user.getName());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("用户名已存在");
        }
        
        if (userRepository.existsByEmail(user.getEmail())) {
            logger.warn("邮箱已存在: {}", user.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("邮箱已存在");
        }
        
        User savedUser = userRepository.save(user);
        logger.info("用户创建成功: {}", savedUser.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping
    public List<User> getAllUsers() {
        logger.info("获取所有用户");
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        logger.info("获取用户: {}", id);
        return userRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        logger.info("更新用户: {}", id);
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        logger.info("删除用户: {}", id);
        userRepository.deleteById(id);
    }
}
