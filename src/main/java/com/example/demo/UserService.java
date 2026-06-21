package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service // 🔥 스프링에게 "이 클래스는 비즈니스 로직을 담당하는 주방장이야!"라고 알려줍니다.
public class UserService {

    // 주방장도 재료를 꺼내려면 창고 관리인(Repository)이 필요합니다.
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 1. 모든 유저를 가져오는 비즈니스 로직
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 2. 나이로 필터링해서 가져오는 비즈니스 로직
    public List<User> getUsersByAge(int age) {
        return userRepository.findByAge(age);
    }

    // 3. 유저를 등록하는 비즈니스 로직 (여기에 나중에 "검증 규칙" 같은 게 들어갑니다!)
    public void registerUser(User user) {
        // 1. 나이 검증 (Validation)
        if (user.getAge() <= 0 || user.getAge() > 120) {
            throw new IllegalArgumentException("유효하지 않은 나이입니다: " + user.getAge());
        }
        // 2. 나이에 따른 등급 부여 (Business Logic)
        if (user.getAge() < 20) { user.setGrade("JUNIOR"); }
        else { user.setGrade("ADULT"); }
        userRepository.save(user);
    }
}
