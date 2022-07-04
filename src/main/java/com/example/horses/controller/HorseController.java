package com.example.horses.controller;

import com.example.horses.repository.entity.Horse;
import com.example.horses.service.HorseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.horses.controller.Const.*;

@Controller
@RequestMapping("/horses")
public class HorseController {
    private final HorseServiceImpl horseService;

    @Autowired
    public HorseController(HorseServiceImpl horseService) {
        this.horseService = horseService;
    }

    @GetMapping
    public String viewHorsePage(Model model) {
        return viewPage(model, 1, HORSE_ID, ASC_SORT);
    }

    @GetMapping("/page/{pageNum}")
    public String viewPage(Model model,
                           @PathVariable(PAGE_NUM) int pageNum,
                           @RequestParam(SORT_FIELD) String sortField,
                           @RequestParam(SORT_DIR) String sortDir) {
        Page<Horse> page = horseService.horsesByPage(pageNum, sortField, sortDir);
        List<Horse> horseList = page.getContent();
        model.addAttribute(CURRENT_PAGE, pageNum);
        model.addAttribute(TOTAL_PAGES, page.getTotalPages());
        model.addAttribute(TOTAL_ITEMS, page.getTotalElements());
        model.addAttribute(HORSE_LIST, horseList);
        model.addAttribute(SORT_FIELD, sortField);
        model.addAttribute(SORT_DIR, sortDir);
        model.addAttribute(REVERSE_SORT_DIR, sortDir.equals(ASC_SORT) ? DESC_SORT : ASC_SORT);
        return HORSE_LIST_HTML;
    }

    @GetMapping("/horse-create")
    public String createHorseForm(Horse horse) {
        return HORSE_CREATE_HTML;
    }

    @PostMapping("/horse-create")
    public String createHorse(Horse horse) {
        horseService.saveHorse(horse);
        return REDIRECT_HORSES;
    }

    @GetMapping("/horse-delete/{id}/{pageNum}/{sortField}/{sortDir}")
    public String deleteHorse(@PathVariable(HORSE_ID) Integer id,
                              @PathVariable(PAGE_NUM) int pageNum,
                              @PathVariable(SORT_FIELD) String sortField,
                              @PathVariable(SORT_DIR) String sortDir,
                              Model model) {
        horseService.delete(id);
        return viewPage(model, pageNum, sortField, sortDir);
    }

    @GetMapping("/horse-update/{id}/{pageNum}/{sortField}/{sortDir}")
    public String updateHorseForm(@PathVariable(HORSE_ID) Integer id,
                                  @PathVariable(PAGE_NUM) int pageNum,
                                  @PathVariable(SORT_FIELD) String sortField,
                                  @PathVariable(SORT_DIR) String sortDir,
                                  Model model) {
        Horse horse = horseService.findById(id);
        model.addAttribute(HORSE, horse);
        model.addAttribute(PAGE_NUM, pageNum);
        model.addAttribute(SORT_FIELD, sortField);
        model.addAttribute(SORT_DIR, sortDir);
        return HORSE_UPDATE_HTML;
    }

    @PostMapping("/horse-update")
    public String updateHorse(Horse horse, int pageNum, String sortField, String sortDir, Model model) {
        horseService.saveHorse(horse);
        return viewPage(model, pageNum, sortField, sortDir);
    }
}
