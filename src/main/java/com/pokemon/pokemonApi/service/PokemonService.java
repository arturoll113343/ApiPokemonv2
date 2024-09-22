package com.pokemon.pokemonApi.service;

import com.pokemon.pokemonApi.dto.PokemonDTO;
import com.pokemon.pokemonApi.dto.EvolutionChainDTO; // Asegúrate de importar tu nuevo DTO
import com.pokemon.pokemonApi.entity.ApiAccess;
import com.pokemon.pokemonApi.entity.Pokemon;
import com.pokemon.pokemonApi.repository.PokemonRepository;
import com.pokemon.pokemonApi.repository.ApiAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

@Service
public class PokemonService {

    private static final Logger logger = LogManager.getLogger(PokemonService.class);
    private static final Logger auditLogger = LogManager.getLogger("AuditLogger");

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private ApiAccessRepository apiAccessRepository;

    private final String POKE_API_URL = "https://pokeapi.co/api/v2/pokemon/";
    private final String EVOLUTION_CHAIN_URL = "https://pokeapi.co/api/v2/evolution-chain/";

    public Pokemon getPokemonById(int id) {
        logger.info("Fetching Pokémon data from PokéAPI for ID: {}", id);

        // Obtener datos del Pokémon
        String url = POKE_API_URL + id;
        PokemonDTO pokemonDTO = restTemplate.getForObject(url, PokemonDTO.class);

        if (pokemonDTO != null) {
            Pokemon pokemon = mapToEntity(pokemonDTO);

            // Obtener la cadena de evolución
            EvolutionChainDTO evolutionChainDTO = getEvolutionChain(pokemon.getId());
            if (evolutionChainDTO != null) {
                pokemon.setEvolutionChain(evolutionChainDTO); // Asegúrate de que Pokemon tenga este método
            }

            // Guardar Pokémon en la base de datos
            pokemonRepository.save(pokemon);
            logger.info("Pokémon {} saved to the database", pokemon.getName());

            // Registrar acceso a la API
            ApiAccess apiAccess = new ApiAccess();
            apiAccess.setAccessTime(LocalDateTime.now());
            apiAccess.setPokemon(pokemon);
            apiAccessRepository.save(apiAccess);

            auditLogger.info("API access logged for Pokémon ID: {}", id);

            return pokemon;
        } else {
            logger.warn("Pokémon not found on PokéAPI for ID: {}", id);
        }

        return null;
    }

    private Pokemon mapToEntity(PokemonDTO pokemonDTO) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId((long) pokemonDTO.getId());
        pokemon.setName(pokemonDTO.getName());
        pokemon.setHeight(pokemonDTO.getHeight());
        pokemon.setWeight(pokemonDTO.getWeight());

        return pokemon;
    }

    // Método para obtener la cadena de evolución
    public EvolutionChainDTO getEvolutionChain(int id) {
        String url = EVOLUTION_CHAIN_URL + id;
        EvolutionChainDTO evolutionChainDTO = restTemplate.getForObject(url, EvolutionChainDTO.class);

        if (evolutionChainDTO != null) {
            logger.info("Fetched evolution chain for Pokémon ID: {}", id);
            return evolutionChainDTO;
        } else {
            logger.warn("Evolution chain not found for Pokémon ID: {}", id);
            return null;
        }
    }
}