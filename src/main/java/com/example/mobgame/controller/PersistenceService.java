package com.example.mobgame.controller;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class PersistenceService {

    public void saveToJpa(Character character, CharacterRepository characterRepository, Model model) {
        characterRepository.save(character);
        model.addAttribute("characters", characterRepository.findAll());
    }

}
