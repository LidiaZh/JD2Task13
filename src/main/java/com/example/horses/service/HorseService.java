package com.example.horses.service;

import com.example.horses.repository.entity.Horse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HorseService {
    Horse findById(Integer id);

    List<Horse> findAll();

    Horse saveHorse(Horse horse);

    void delete(Integer id);

    Page<Horse> horsesByPage(int pageNum, String sortField, String sortDir);

}
