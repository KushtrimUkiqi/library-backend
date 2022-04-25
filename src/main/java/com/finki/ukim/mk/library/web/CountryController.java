package com.finki.ukim.mk.library.web;

import com.finki.ukim.mk.library.models.Country;
import com.finki.ukim.mk.library.services.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CountryController {
    private final CountryService countryService;

    @GetMapping("countries")
    public ResponseEntity<List<Country>> getAll()
    {
        return ResponseEntity.ok(this.countryService.findAll());
    }

    @GetMapping("countries/{ID}")
    public ResponseEntity<Country> getCountry(@PathVariable Long ID)
    {
        Country country = null;
        try {
            country = this.countryService.findByID(ID);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(country);
    }

    @PostMapping("countries/add")
    public ResponseEntity<Country> addCountry(@RequestParam String name,@RequestParam String continent)
    {
        Country country = null;
        try {
            country = this.countryService.addNewCountry(name,continent);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(country);
    }

    @PutMapping("countries/edit/{ID}")
    public ResponseEntity<Country> addCountry(@PathVariable Long ID, @RequestParam String name,@RequestParam String continent)
    {
        Country country = null;
        try {
            country = this.countryService.editCountry(ID,name,continent);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(country);
    }

    @DeleteMapping("countries/remove/{ID}")
    public ResponseEntity<String> removeCountry(@PathVariable Long ID)
    {
        try {
            this.countryService.deleteCountry(ID);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
