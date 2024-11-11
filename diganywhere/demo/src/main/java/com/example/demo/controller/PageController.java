package com.example.demo.controller;

import com.example.demo.controller.service.Assistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {


    @Autowired
    private Assistant assistant;


    @GetMapping("/home")
    public String chat() {
        // Ensure this matches the form's input name attribute
        int userId = 123; // This can be customized
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Points to `login.html` in the templates folder
    }

    @PostMapping("/search")
    String search(@RequestParam("question") String question, Model model) {
        int userId = 123;
        String response = assistant.chat(userId, question);

        model.addAttribute("result", response);
        return "searchResult";
    }
}