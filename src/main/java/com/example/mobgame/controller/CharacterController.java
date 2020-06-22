package com.example.mobgame.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class CharacterController {

    CharacterRepository characterRepository;
    PersistenceService persistenceService;

    public CharacterController(CharacterRepository characterRepository, PersistenceService persistenceService) {
        this.characterRepository = characterRepository;
        this.persistenceService = persistenceService;
    }

    @GetMapping("/main")
    public String showIndex(Model model) {
        model.addAttribute("characters", characterRepository.findAll());
        return "index";
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

        persistenceService.saveToJpa(character, characterRepository, model);

        log.info(characterRepository.findAll().toString());

        //return "index";
        return "redirect:/main";
    }

}
