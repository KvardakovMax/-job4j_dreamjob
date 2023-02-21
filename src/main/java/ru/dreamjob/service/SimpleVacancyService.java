package ru.dreamjob.service;

import ru.dreamjob.model.Vacancy;
import ru.dreamjob.repository.MemoryVacancyRepository;
import ru.dreamjob.repository.VacancyRepository;

import java.util.Collection;
import java.util.Optional;

public class SimpleVacancyService implements VacancyService{

    private static final SimpleVacancyService INSTANCE = new SimpleVacancyService();

    private final VacancyRepository repository = MemoryVacancyRepository.getInstance();

    public static SimpleVacancyService getInstance() {
        return INSTANCE;
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        return repository.save(vacancy);
    }

    @Override
    public boolean deleteById(int id) {
        return repository.deleteById(id);
    }

    @Override
    public boolean update(Vacancy vacancy) {
        return repository.update(vacancy);
    }

    @Override
    public Optional<Vacancy> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Collection<Vacancy> findAll() {
        return repository.findAll();
    }
}
