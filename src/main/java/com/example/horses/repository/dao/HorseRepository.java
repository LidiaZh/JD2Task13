package com.example.horses.repository.dao;

import com.example.horses.repository.entity.Horse;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HorseRepository extends JpaRepository<Horse, Integer> {

}
