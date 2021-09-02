package com.example.final_project.controller;

import com.example.final_project.entity.DeliveryCost;
import com.example.final_project.repository.DirectionRepository;
import com.example.final_project.repository.TariffRepository;
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
    @Autowired
    private DeliveryRequestService deliveryRequestService;
    @Autowired
    private DirectionServise directionServise;
    @Autowired
    private DirectionRepository directionRepository;
    @Autowired
    private TariffRepository tariffRepository;

    @GetMapping()
    public String mainPage(@RequestParam(name = "sort",
            required = false, defaultValue = "false") Boolean sort,
                           @RequestParam(name = "filter",
                                   required = false, defaultValue = "false") Boolean filter,
                           @RequestParam(value = "city", required = false,defaultValue = "") String city,
                           @RequestParam(value = "form", required = false,defaultValue = "false") Boolean form,
                           @ModelAttribute("deliveryCost") @Valid DeliveryCost deliveryCost, BindingResult bindingResult,
                           Model model) {
        model.addAttribute("filter",filter);
        model.addAttribute("filterDirection",directionServise.findDirectionsLike(city));
        model.addAttribute("locale", LocaleContextHolder.getLocale().getLanguage());
        model.addAttribute("sort", sort);
        model.addAttribute("tariffs", tariffRepository.findAll());
        model.addAttribute("allDirections", directionRepository.findAll());
        model.addAttribute("sortedDirectionsEnLocale", directionServise.sortedDirectionsForEnLocale());
        model.addAttribute("sortedDirectionsUkLocale", directionServise.sortedDirectionsForUkLocale());
        model.addAttribute("deliveryCost", new DeliveryCost());
        model.addAttribute("result", 0);
        if(form){
            if(bindingResult.hasErrors()) return "main";
            model.addAttribute("deliveryCost", deliveryCost);
            model.addAttribute("result", deliveryRequestService.calculateDeliveryCost(deliveryCost.getWeight(),
                    deliveryRequestService.calculateVolume(deliveryCost.getLength(),deliveryCost.getHeight(),deliveryCost.getWidth()),
                    deliveryCost.getCity()));
        }
        return "main";
    }
}
