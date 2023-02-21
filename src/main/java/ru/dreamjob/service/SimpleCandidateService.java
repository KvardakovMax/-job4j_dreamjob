package ru.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.dreamjob.model.Candidate;
import ru.dreamjob.repository.CandidateRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleCandidateService implements CandidateService {

    private final CandidateRepository repository;

    public SimpleCandidateService(CandidateRepository repository) {
        this.repository = repository;
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
