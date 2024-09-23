package com.pokemon.pokemonApi.controller;

import com.pokemon.pokemonApi.entity.Pokemon;
import com.pokemon.pokemonApi.dto.EvolutionChainDTO;
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
        logger.info("Request received for Pokémon with ID: {}", id);

        Pokemon pokemon = pokemonService.getPokemonById(id);

        if (pokemon != null) {
            logger.info("Pokémon found: {}", pokemon.getName());
            return ResponseEntity.ok(pokemon);
        } else {
            logger.warn("Pokémon not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    // Nuevo endpoint para obtener la cadena de evolución
    @GetMapping("/{id}/evolution-chain")
    public ResponseEntity<EvolutionChainDTO> getEvolutionChain(@PathVariable int id) {
        logger.info("Request received for evolution chain of Pokémon with ID: {}", id);

        EvolutionChainDTO evolutionChain = pokemonService.getEvolutionChain(id);

        if (evolutionChain != null) {
            logger.info("Evolution chain found for Pokémon ID: {}", id);
            return ResponseEntity.ok(evolutionChain);
        } else {
            logger.warn("Evolution chain not found for Pokémon ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
