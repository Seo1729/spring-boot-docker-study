package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<대상클래스, ID의타입>을 상속받으면 모든 DB 조작 메서드가 공짜로 생깁니다!
public interface UserRepository extends JpaRepository<User, Long> {
    
    // 💡 나이로 유저를 검색하는 메서드를 커스텀으로 추가합니다. 
    // 이름 규칙(findByAge)만 맞춰주면 스프링이 알아서 SQL 필터링 문을 만들어 줍니다!
    List<User> findByAge(int age);
}