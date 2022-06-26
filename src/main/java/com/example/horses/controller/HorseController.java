package com.example.horses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.horses.repository.entity.Horse;
import com.example.horses.service.HorseService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HorseController {
    private final HorseService horseService;

    @Autowired
    public HorseController(HorseService horseService) {
        this.horseService = horseService;
    }

    @GetMapping("/horses")
    public String findAll(Model model) {
        List<Horse> horses = horseService.findAll();
        model.addAttribute("horses", horses);
        return "horse-list";
    }

    @GetMapping("/horse-create")
    public String createHorseForm(Horse horse) {
        return "horse-create";
    }

    @PostMapping("/horse-create")
    public String createHorse(Horse horse) {
        horseService.saveHorse(horse);
        return "redirect:/horses";
    }

    @GetMapping("/horse-delete/{id}")
    public String deleteHorse(@PathVariable("id") Integer id) {
        horseService.delete(id);
        return "redirect:/horses";
    }

    @GetMapping("/horse-update/{id}")
    public String updateHorseForm(@PathVariable("id") Integer id, Model model) {
        Horse horse = horseService.findById(id);
        model.addAttribute("horse",horse);
        return "/horse-update";
    }

    @PostMapping("/horse-update")
    public String updateHorse(Horse horse) {
        horseService.saveHorse(horse);
        return "redirect:/horses";
    }
}
