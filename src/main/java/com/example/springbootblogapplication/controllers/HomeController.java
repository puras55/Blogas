package com.example.springbootblogapplication.controllers;

import com.example.springbootblogapplication.models.Post;
import com.example.springbootblogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private PostService postService;

    @GetMapping("/") //etodas home turi būti iškviestas, kai gaunama HTTP GET užklausa į pagrindinį puslapį ("/").
    public String home(Model model) {
        List<Post> posts = postService.getAll(); // gauna visus įrašus  is h2
        model.addAttribute("posts", posts); //prideda gautus įrašus prie modelio su pavadinimu "posts".
        return "home";
    }

//Model model leidžia pridėti duomenis, kurie bus naudojami šablone (view) puslapio atvaizdavimui.

}
