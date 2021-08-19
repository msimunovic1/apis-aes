package com.apisaes.task.controllers;

import com.apisaes.task.services.DataDetailsService;
import hr.aaa.test.v0.datadetails.DataDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/datadetails")
public class DataDetailsController {

    private final DataDetailsService dataDetailsService;

    @ModelAttribute("now")
    public LocalDate currentTime(){
        LocalDate now = LocalDate.now();
        return now;
    }

    public DataDetailsController(DataDetailsService dataDetailsService) {
        this.dataDetailsService = dataDetailsService;
    }

    @GetMapping({"", "/", "list"})
    public String listDataDetails(Model model){
        model.addAttribute("listDataDetails", dataDetailsService.findAllDataDetails());
        return "dataDetailsList";
    }

    @GetMapping({"/{id}"})
    public String findDataDetailsById(Model model, @PathVariable String id){
        model.addAttribute("listDataDetails", dataDetailsService.findById(id));
        return "dataDetailsList";
    }

    @GetMapping("/new")
    public String getForm(Model model){
        model.addAttribute(new DataDetails());
        return "newDataDetails";
    }

    @PostMapping("/new")
    public String createDataDetails(@ModelAttribute DataDetails dataDetails, Model model){
        dataDetailsService.saveDataDetails(dataDetails);
        return "redirect:/datadetails";
    }
}
