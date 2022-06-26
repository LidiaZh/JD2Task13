package com.example.horses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.horses.repository.dao.HorseRepository;
import com.example.horses.repository.entity.Horse;

import java.util.List;

@Service
public class HorseService {
    private final HorseRepository horseRepository;

    @Autowired
    public HorseService(HorseRepository horseRepository) {
        this.horseRepository = horseRepository;
    }

    public Horse findById(Integer id) {
        return horseRepository.getReferenceById(id);
    }

    public List<Horse> findAll() {
        return horseRepository.findAll();
    }

    public Horse saveHorse(Horse horse) {
        return horseRepository.save(horse);
    }

    public void delete(Integer id) {
        horseRepository.deleteById(id);
    }
}
