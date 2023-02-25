package ru.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.dreamjob.model.City;
import ru.dreamjob.repository.CityRepository;
import ru.dreamjob.repository.MemoryCityRepository;

import java.util.Collection;

@Service
public class SimpleCityService implements CityService{

    private final CityRepository repository;

    public SimpleCityService(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<City> findAll() {
        return repository.findAll();
    }

}
