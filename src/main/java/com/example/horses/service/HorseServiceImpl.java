package com.example.horses.service;

import com.example.horses.repository.dao.HorseRepository;
import com.example.horses.repository.entity.Horse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorseServiceImpl implements HorseService {

    private final HorseRepository horseRepository;

    @Autowired
    public HorseServiceImpl(HorseRepository horseRepository) {
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

    @Override
    public Page<Horse> horsesByPage(int pageNum, String sortField, String sortDir) {
        int pageSize = 3;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return horseRepository.findAll(pageable);
    }
}