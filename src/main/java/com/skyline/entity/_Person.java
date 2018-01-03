package com.skyline.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class _Person {
	private String id;
	private String name;
	private int age;
	
	public _Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Id
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
}