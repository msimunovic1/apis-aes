package com.apisaes.task.mvc.controllers;
import javax.validation.Valid;

import com.apisaes.task.mvc.commands.DataDetailsCommand;
import com.apisaes.task.mvc.converters.DataDetailsConverter;
import com.apisaes.task.mvc.services.DataDetailsService;
import hr.aaa.test.v0.datadetails.DataDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/datadetails")
public class DataDetailsController {

    private final DataDetailsService dataDetailsService;
    private DataDetailsConverter dataDetailsConverter;

    public DataDetailsController(DataDetailsService dataDetailsService) {
        this.dataDetailsService = dataDetailsService;
        this.dataDetailsConverter = new DataDetailsConverter();
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
        model.addAttribute(new DataDetailsCommand());
        return "newDataDetails";
    }

    @PostMapping("/new")
    public String createDataDetails(@Valid DataDetailsCommand dataDetailsCommand, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute(dataDetailsCommand);
            return "newDataDetails";
        }
        DataDetails dataDetails = dataDetailsConverter.convert(dataDetailsCommand);
        dataDetailsService.saveDataDetails(dataDetails);
        return "redirect:/datadetails";
    }
}
