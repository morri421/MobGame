package com.example.mobgame.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class CharacterController {

    CharacterRepository characterRepository;

    public CharacterController(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @GetMapping("/signup")
    public String showSignUpForm(Character character) {
        return "add-character";
    }

    @PostMapping("/addcharacter")
    public String addUser(@Valid Character character, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-character";
        }

        characterRepository.save(character);
        log.info(characterRepository.findAll().toString());
        model.addAttribute("characters", characterRepository.findAll());
        return "index";
        //return "redirect:/index";
    }

}
