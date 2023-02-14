package ru.dreamjob.repository;

import ru.dreamjob.model.Vacancy;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryVacancyRepository implements VacancyRepository {
    private static final MemoryVacancyRepository INSTANCE = new MemoryVacancyRepository();

    private int nextId = 1;

    private final Map<Integer, Vacancy> vacancies = new HashMap<>();

    private MemoryVacancyRepository() {
        save(new Vacancy(0, "Intern Java Developer", "An intern is typically temporary and often receives school credits for their work.", LocalDateTime.now()));
        save(new Vacancy(0, "Junior Java Developer", "This is a beginner specialist who already knows the base", LocalDateTime.now()));
        save(new Vacancy(0, "Junior+ Java Developer", "Strong junior developer", LocalDateTime.now()));
        save(new Vacancy(0, "Middle Java Developer", "Mid-level seniority involves having a managerial position over entry-level employees while also reporting to someone with more seniority", LocalDateTime.now()));
        save(new Vacancy(0, "Middle+ Java Developer", "More advanced mid-level employees are sometimes described as \"mid-senior\" to indicate their seniority over other middle management positions.", LocalDateTime.now()));
        save(new Vacancy(0, "Senior Java Developer", "Senior developers may specialize in a specific area, oversee projects, and manage junior developers.", LocalDateTime.now()));
    }

    public static MemoryVacancyRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        vacancy.setId(nextId++);
        vacancies.put(vacancy.getId(), vacancy);
        return vacancy;
    }

    @Override
    public boolean deleteById(int id) {
        return vacancies.remove(id) != null;
    }

    @Override
    public boolean update(Vacancy vacancy) {
        return vacancies.computeIfPresent(vacancy.getId(), (id, oldVacancy) -> new Vacancy(oldVacancy.getId(), vacancy.getTitle(), vacancy.getDescription(), LocalDateTime.now())) != null;
    }

    @Override
    public Optional<Vacancy> findById(int id) {
        return Optional.ofNullable(vacancies.get(id));
    }

    @Override
    public Collection<Vacancy> findAll() {
        return vacancies.values();
    }
}
