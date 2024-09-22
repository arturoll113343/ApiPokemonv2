package com.pokemon.pokemonApi.service;

import com.pokemon.pokemonApi.dto.PokemonDTO;
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
    private static final Logger auditLogger = LogManager.getLogger("AuditLogger"); // Logger de auditoría

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private ApiAccessRepository apiAccessRepository;

    private final String POKE_API_URL = "https://pokeapi.co/api/v2/pokemon/";

    public Pokemon getPokemonById(int id) {
        logger.info("Fetching Pokémon data from PokéAPI for ID: {}", id); // Log de obtención de datos

        // Llamada a la PokéAPI para obtener los datos del Pokémon usando el DTO
        String url = POKE_API_URL + id;
        PokemonDTO pokemonDTO = restTemplate.getForObject(url, PokemonDTO.class);

        if (pokemonDTO != null) {
            // Convierte el DTO a tu entidad Pokemon
            Pokemon pokemon = mapToEntity(pokemonDTO);

            // Guardar o actualizar el Pokémon en la base de datos
            pokemonRepository.save(pokemon);
            logger.info("Pokémon {} saved to the database", pokemon.getName()); // Log de guardado en la BD

            // Registrar el acceso a la API
            ApiAccess apiAccess = new ApiAccess();
            apiAccess.setAccessTime(LocalDateTime.now());
            apiAccess.setPokemon(pokemon);
            apiAccessRepository.save(apiAccess);

            auditLogger.info("API access logged for Pokémon ID: {}", id); // Log de auditoría

            return pokemon;
        } else {
            logger.warn("Pokémon not found on PokéAPI for ID: {}", id); // Log de Pokémon no encontrado en PokéAPI
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
}
