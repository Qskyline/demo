package com.skyline.dao;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.skyline.entity._Person;

public interface _PersonDao extends PagingAndSortingRepository<_Person, String> {
    public List<_Person> findByName(String name);
    public List<_Person> findByAge(int age);
}