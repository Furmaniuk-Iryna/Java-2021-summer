package com.example.final_project.controller;

import com.example.final_project.entity.DeliveryCost;
import com.example.final_project.repository.DirectionRepository;
import com.example.final_project.service.DeliveryCostService;
import com.example.final_project.service.DirectionServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    private boolean sorted;

    @Autowired
    private DeliveryCostService deliveryCostService;
    @Autowired
    private DirectionServise directionServise;
    @Autowired
    private DirectionRepository directionRepository;

    @RequestMapping("/")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/")
    public String newCalculateDeliveryCost(@RequestParam(name = "sort",
            required = false, defaultValue = "false") Boolean sort, Model model) {
        model.addAttribute("sort", sort);
        sorted = sort;
        model.addAttribute("directions1", directionRepository.findAll());
        model.addAttribute("directions2", directionServise.sortedDirections());
        model.addAttribute("deliveryCost", new DeliveryCost());
        model.addAttribute("result", 0);
        model.addAttribute("volume", 0);
        return "main";
    }

    @PostMapping("/")
    public String readDeliveryCost(@ModelAttribute("deliveryCost") DeliveryCost deliveryCost,
                                   @RequestParam(name = "sort", required = false,
                                           defaultValue = "false") Boolean sort, Model model) {
        System.out.println(deliveryCost);
        sort = sorted;
        model.addAttribute("sort", sort);
        model.addAttribute("directions1", directionRepository.findAll());
        model.addAttribute("directions2", directionServise.sortedDirections());
        model.addAttribute("result", deliveryCostService.calculateDeliveryCost(deliveryCost));
        model.addAttribute("volume", deliveryCostService.calculateVolumeForDeliveryCosts(deliveryCost));
        return "main";
    }


}
