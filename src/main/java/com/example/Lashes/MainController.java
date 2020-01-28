package com.example.Lashes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {

    private UserRepository userRepo;
    public MainController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    @GetMapping("/home")
    public String home(ModelMap model){
        model.put("usersList", userRepo.findAll());

        return "home";
    }

    @PostMapping("/add")
    public String add(ModelMap model , @Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.put("user", userRepo.findAll());
            return "home";
        }
        userRepo.save(user);
        return "redirect:/home";
    }



}
