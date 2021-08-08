package com.example.final_project.controller;

import com.example.final_project.entity.DeliveryCost;
import com.example.final_project.repository.DirectionRepository;
import com.example.final_project.repository.TariffRepository;
import com.example.final_project.service.DeliveryCostService;
import com.example.final_project.service.DirectionServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class MainController {
    private boolean sorted;


    @Autowired
    private DeliveryCostService deliveryCostService;
    @Autowired
    private DirectionServise directionServise;
    @Autowired
    private DirectionRepository directionRepository;
    @Autowired
    private TariffRepository tariffRepository;


    @GetMapping()
    public String mainPage(@RequestParam(name = "sort",
            required = false, defaultValue = "false") Boolean sort,
                           Model model) {
        model.addAttribute("locale", LocaleContextHolder.getLocale().getLanguage());
        model.addAttribute("sort", sort);
        sorted = sort;
        model.addAttribute("tariffs", tariffRepository.findAll());
        model.addAttribute("allDirections", directionRepository.findAll());
        model.addAttribute("sortedDirectionsEnLocale", directionServise.sortedDirectionsForEnLocale());
        model.addAttribute("sortedDirectionsUkLocale", directionServise.sortedDirectionsForUkLocale());
        model.addAttribute("deliveryCost", new DeliveryCost());
        model.addAttribute("result", 0);
        return "main";
    }

    @PostMapping()
    public String deliveryCost(@ModelAttribute("deliveryCost") DeliveryCost deliveryCost,
                               @RequestParam(name = "sort", required = false,
                                       defaultValue = "false") Boolean sort,
                               Model model) {
        sort = sorted;
        model.addAttribute("locale", LocaleContextHolder.getLocale().getLanguage());
        model.addAttribute("sort", sort);
        model.addAttribute("tariffs", tariffRepository.findAll());
        model.addAttribute("allDirections", directionRepository.findAll());
        model.addAttribute("sortedDirectionsEnLocale", directionServise.sortedDirectionsForEnLocale());
        model.addAttribute("sortedDirectionsUkLocale", directionServise.sortedDirectionsForUkLocale());
        model.addAttribute("result", deliveryCostService.calculateDeliveryCost(deliveryCost));
        return "main";
    }


}