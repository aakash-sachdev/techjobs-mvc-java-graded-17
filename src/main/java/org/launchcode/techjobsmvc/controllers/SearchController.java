package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) { //two parameters with correct annotation
        //Creating an Arraylist
        ArrayList <Job> jobs;

        //Conditional - If the user enters “all” or leave the box empty
        if (searchTerm.equals("all") || searchTerm.isBlank()){
            jobs = JobData.findAll();  // calling the findAll() from JobData
        } else{
                jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        //Passing jobs into the search.html view via the model parameter
        model.addAttribute("jobs",jobs);

        //Passing ListController.columnChoices
        model.addAttribute("columns",ListController.columnChoices);


//        model.addAttribute("searchType",searchType);
//        model.addAttribute("searchTerm",searchTerm);
        return "search";
    }


}

