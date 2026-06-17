package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // 🔥 1. 이 클래스는 이제 DB 테이블과 1:1로 매핑된다는 뜻입니다.
@Table(name = "users") // 🔥 2. DB에 'users'라는 이름의 테이블로 만들어집니다.
public class User {

    @Id // 🔥 3. 데이터의 고유 번호(주민등록번호 역할)를 지정합니다. 백엔드에선 필수입니다!
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 4. 번호를 1, 2, 3... 자동으로 올려줍니다.
    private Long id; 

    private String name;
    private int age;
    private String role;

    // JPA를 쓸 때는 기본 생성자(아무것도 없는 빈 생성자)가 필수로 하나 있어야 오류가 안 납니다!
    protected User() {}

    // 기존에 있던 생성자 (id는 자동으로 들어가므로 그대로 두시면 됩니다)
    public User(String name, int age, String role) {
        this.name = name;
        this.age = age;
        this.role = role;
    }

    // 기존의 Getter들 아래에 id 전용 Getter 하나만 추가해 주세요.
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getRole() { return role; }
}