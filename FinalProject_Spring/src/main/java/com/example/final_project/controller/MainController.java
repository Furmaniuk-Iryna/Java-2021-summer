package com.example.final_project.controller;

import com.example.final_project.entity.DeliveryCost;
import com.example.final_project.entity.Direction;
import com.example.final_project.repository.DirectionRepository;
import com.example.final_project.repository.TariffRepository;
import com.example.final_project.service.DeliveryCostService;
import com.example.final_project.service.DeliveryRequestService;
import com.example.final_project.service.DirectionServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/")
public class MainController {
    private boolean sorted;
    private boolean filter=false;
    private Direction filterDirection=new Direction();

    @Autowired
    private DeliveryCostService deliveryCostService;
    @Autowired
    private DirectionServise directionServise;
    @Autowired
    private DirectionRepository directionRepository;
    @Autowired
    private TariffRepository tariffRepository;
    @Autowired
    private DeliveryRequestService deliveryRequestService;

//TODO refactoring
    @GetMapping()
    public String mainPage(@RequestParam(name = "sort",
            required = false, defaultValue = "false") Boolean sort,
                           Model model) {
        model.addAttribute("filter",filter);
        model.addAttribute("filterDirection", filterDirection);
        model.addAttribute("neededDirection", new Direction());
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
    public String deliveryCost(@ModelAttribute("deliveryCost") @Valid DeliveryCost deliveryCost, BindingResult bindingResult,
                               @ModelAttribute("directionValue") Direction direction,
                               @RequestParam(name = "sort", required = false,
                                       defaultValue = "false") Boolean sort,
                               Model model) {
        sort = sorted;
        model.addAttribute("filter",filter);
        model.addAttribute("filterDirection", filterDirection);
        model.addAttribute("neededDirection", new Direction());
        model.addAttribute("locale", LocaleContextHolder.getLocale().getLanguage());
        model.addAttribute("sort", sort);
        model.addAttribute("tariffs", tariffRepository.findAll());
        model.addAttribute("allDirections", directionRepository.findAll());
        model.addAttribute("sortedDirectionsEnLocale", directionServise.sortedDirectionsForEnLocale());
        model.addAttribute("sortedDirectionsUkLocale", directionServise.sortedDirectionsForUkLocale());
        if(bindingResult.hasErrors()) return "main";
        model.addAttribute("result", deliveryCostService.calculateDeliveryCost(deliveryCost.getWeight(), deliveryCostService.calculateVolumeForDeliveryCosts(deliveryCost),deliveryCost.getCity()));
        return "main";
    }

    @PostMapping("filter")
    public String createDirectionReport(@ModelAttribute("neededDirection") Direction neededDirection, Model model) {
        model.addAttribute("filterDirection",filterDirection=directionServise.getNeededDirection(neededDirection.getCity_en()));
        model.addAttribute("filter", filter=true);
        return "redirect:/";
    }
    @GetMapping("filter")
    public String getDirectionWithoutFilter(@ModelAttribute("neededDirection") Direction neededDirection, Model model) {
        model.addAttribute("filterDirection",filterDirection);
        model.addAttribute("filter", filter=false);
        return "redirect:/";
    }


}
