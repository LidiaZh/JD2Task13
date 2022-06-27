package com.example.horses.service;

import com.example.horses.repository.entity.Horse;

import java.util.List;

public interface HorseServiceImpl {
    public Horse findById(Integer id);

    public List<Horse> findAll();

    public Horse saveHorse(Horse horse);

    public void delete(Integer id);
}
