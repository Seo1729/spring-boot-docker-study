package com.example.demo;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    // 1. 진짜 DB 리모컨을 가져옵니다.
    private final UserRepository userRepository;

    // 2. 스프링 부트가 켜질 때 이 리모컨을 자동으로 연결(주입)해 줍니다.
    public HelloController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "멘토님, 저 드디어 도커 DB 연동까지 성공했어요!";
    }

    // 3. 전체 유저 조회 API (DB에서 모두 꺼내옵니다)
    @GetMapping("/user")
    public List<User> getUser() {
        return userRepository.findAll(); // findAll()은 JPA가 공짜로 주는 메서드입니다!
    }

    // 4. 나이 필터링 API (우리가 인터페이스에 적어둔 메서드를 호출합니다)
    @GetMapping("/filter")
    public List<User> filterUser(@RequestParam(name = "age") int inputAge) {
        return userRepository.findByAge(inputAge); 
    }

    // 5. 유저 등록 API (진짜 DB에 저장합니다)
    @PostMapping("/user")
    public String createUser(@RequestBody User newUser) {
        userRepository.save(newUser); // save()도 JPA가 공짜로 줍니다. DB에 쏙!
        return newUser.getName() + " 유저가 진짜 데이터베이스에 등록되었습니다!";
    }
}