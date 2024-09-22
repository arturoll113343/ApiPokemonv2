package com.pokemon.pokemonApi.controller;

import com.pokemon.pokemonApi.entity.Pokemon;
import com.pokemon.pokemonApi.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    private static final Logger logger = LogManager.getLogger(PokemonController.class);

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable int id) {
        logger.info("Request received for Pokémon with ID: {}", id); // Log del request recibido

        Pokemon pokemon = pokemonService.getPokemonById(id);

        if (pokemon != null) {
            logger.info("Pokémon found: {}", pokemon.getName()); // Log del Pokémon encontrado
            return ResponseEntity.ok(pokemon);
        } else {
            logger.warn("Pokémon not found with ID: {}", id); // Log de Pokémon no encontrado
            return ResponseEntity.notFound().build();
        }
    }
}
