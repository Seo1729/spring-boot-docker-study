package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final UserService userService;

    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    public String createUser(@RequestBody UserRequestDto requestDto) {
        try {
            userService.registerUser(requestDto);
            return requestDto.getName() + " 등록 성공!";
        }   catch (Exception e) { return "실패: " + e.getMessage(); }
    }

    // 🟡 [추가] 수정 API (예: PUT http://localhost:8080/user/1)
    @PutMapping("/user/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        try {
            userService.updateUser(id, requestDto);
            return id + "번 유저 수정 완료!";
        } catch (Exception e) { return "수정 실패: " + e.getMessage(); }
    }

    // 🔴 [추가] 삭제 API (예: DELETE http://localhost:8080/user/1)
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return id + "번 유저 삭제 완료!";
        } catch (Exception e) { return "삭제 실패: " + e.getMessage(); }
    }
}