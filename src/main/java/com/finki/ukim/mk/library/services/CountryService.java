package com.finki.ukim.mk.library.services;

import com.finki.ukim.mk.library.models.Country;

import java.util.List;


public interface CountryService {
    Country findByID(Long ID) throws Exception;
    Country addNewCountry(String name, String continent);
    Country editCountry(Long ID,String name, String continent);
    void deleteCountry(Long ID);
    List<Country> findAll();
    List<Country> findAllByNameContaining(String name);
}
