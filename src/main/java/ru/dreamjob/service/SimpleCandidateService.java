package ru.dreamjob.service;

import ru.dreamjob.model.Candidate;
import ru.dreamjob.repository.CandidateRepository;
import ru.dreamjob.repository.MemoryCandidateRepository;

import java.util.Collection;
import java.util.Optional;

public class SimpleCandidateService implements CandidateService{

    private static final SimpleCandidateService INSTANCE = new SimpleCandidateService();

    private final CandidateRepository repository = MemoryCandidateRepository.getInstance();

    public static SimpleCandidateService getInstance() {
        return INSTANCE;
    }

    @Override
    public Candidate save(Candidate candidate) {
        return repository.save(candidate);
    }

    @Override
    public boolean update(Candidate candidate) {
        return repository.update(candidate);
    }

    @Override
    public boolean deleteById(int id) {
        return repository.deleteById(id);
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Collection<Candidate> findAll() {
        return repository.findAll();
    }
}
