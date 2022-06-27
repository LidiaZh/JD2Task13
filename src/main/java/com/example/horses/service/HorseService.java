package com.example.horses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.horses.repository.dao.HorseRepository;
import com.example.horses.repository.entity.Horse;

import java.util.List;

@Service
public class HorseService implements HorseServiceImpl {
    private final HorseRepository horseRepository;

    @Autowired
    public HorseService(HorseRepository horseRepository) {
        this.horseRepository = horseRepository;
    }

    @Override
    public Horse findById(Integer id) {
        return horseRepository.getReferenceById(id);
    }

    @Override
    public List<Horse> findAll() {
        return horseRepository.findAll();
    }

    @Override
    public Horse saveHorse(Horse horse) {
        return horseRepository.save(horse);
    }

    @Override
    public void delete(Integer id) {
        horseRepository.deleteById(id);
    }
}
