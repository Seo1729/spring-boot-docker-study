package com.example.demo;

public class UserRequestDto {
    private String name;
    private int age;

    // 기본 생성자
    public UserRequestDto() {}

    // Getter, Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}