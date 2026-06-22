package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
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
    public void registerUser(UserRequestDto dto) {
        // DTO에서 데이터를 꺼내 진짜 Entity(User) 객체를 새로 만듦
        User user = new User();
        user.setName(dto.getName());
        user.setAge(dto.getAge());

        validateAndAssignGrade(user); // 등급 검증 로직 수행
        userRepository.save(user); // DB에 저장
    }

    // 🔴 [추가] 삭제 로직: ID로 찾아서 삭제함
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("존재하지 않는 ID입니다.");
        }
        userRepository.deleteById(id);
    }

    // 🟡 [추가] 수정 로직: 기존 유저를 꺼내서 새 정보를 덮어씌움
    public User updateUser(Long id, UserRequestDto dto) {
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID입니다."));

        // DTO에 담겨온 안전한 데이터만 기존 유저에게 덮어씌움
        existingUser.setName(dto.getName());
        existingUser.setAge(dto.getAge());

        validateAndAssignGrade(existingUser);
        return userRepository.save(existingUser);
    }

    // 💡 공통 검증 메서드 (가독성을 위해 따로 뺌)
    private void validateAndAssignGrade(User user) {
        if (user.getAge() <= 0 || user.getAge() > 120) {
            throw new IllegalArgumentException("나이가 이상합니다.");
        }
        user.setGrade(user.getAge() < 20 ? "JUNIOR" : "ADULT");
    }
}
