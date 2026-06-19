package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // 🔥 이제 Repository가 아니라 Service(주방장)를 바라봅니다!
    private final UserService userService;

    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "2일 차 레이어드 아키텍처 분리도 성공!";
    }

    // 전체 조회: 매니저는 주방장에게 요청만 전달합니다.
    @GetMapping("/user")
    public List<User> getUser() {
        return userService.getAllUsers();
    }

    // 필터링 조회
    @GetMapping("/filter")
    public List<User> filterUser(@RequestParam(name = "age") int inputAge) {
        return userService.getUsersByAge(inputAge);
    }

    // 유저 등록
    @PostMapping("/user")
    public String createUser(@RequestBody User newUser) {
        userService.registerUser(newUser);
        return newUser.getName() + " 유저가 서비스를 거쳐 진짜 DB에 등록되었습니다!";
    }
}