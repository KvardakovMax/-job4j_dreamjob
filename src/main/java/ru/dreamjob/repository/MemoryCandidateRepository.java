package ru.dreamjob.repository;

import org.springframework.stereotype.Repository;
import ru.dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryCandidateRepository implements CandidateRepository {

    private int nextId = 1;

    private final Map<Integer, Candidate> candidates = new HashMap<>();

    private MemoryCandidateRepository() {
        save(new Candidate(0, "Andrey", "Intern developer", LocalDateTime.now(), 0, 0));
        save(new Candidate(0, "Igor", "Junior developer", LocalDateTime.now(), 0, 0));
        save(new Candidate(0, "Max", "Middle developer", LocalDateTime.now(), 0, 0));
        save(new Candidate(0, "Anton", "Senior developer", LocalDateTime.now(), 0, 0));
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId++);
        candidates.put(candidate.getId(), candidate);
        return candidate;
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(), (id, oldCandidate) -> new Candidate(
                oldCandidate.getId(),
                candidate.getName(),
                candidate.getDescription(),
                LocalDateTime.now(),
                candidate.getCityId(),
                candidate.getFileId()
        )) != null;
    }

    @Override
    public boolean deleteById(int id) {
        return candidates.remove(id) != null;
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return Optional.ofNullable(candidates.get(id));
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
