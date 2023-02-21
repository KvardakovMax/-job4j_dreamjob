package ru.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dreamjob.model.Candidate;
import ru.dreamjob.repository.CandidateRepository;
import ru.dreamjob.repository.MemoryCandidateRepository;
import ru.dreamjob.service.SimpleCandidateService;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    private final SimpleCandidateService service = SimpleCandidateService.getInstance();

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("candidates", service.findAll());
        return "candidates/list";
    }

    @GetMapping("/create")
    public String getCreationPage() {
        return "candidates/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Candidate candidate) {
        service.save(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var candidateOptional = service.findById(id);
        if (candidateOptional.isEmpty()) {
            model.addAttribute("message", "Кандидат не найден");
            return "error/404";
        }
        model.addAttribute("candidate", candidateOptional.get());
        return "candidates/one";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Candidate candidate, Model model) {
        var updated = service.update(candidate);
        if (!updated) {
            model.addAttribute("message", "Кандидат не найден");
            return "error/404";
        }
        return "redirect:/candidates";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        var deleted = service.deleteById(id);
        if (!deleted) {
            model.addAttribute("message", "Кандидат не найден");
            return "error/404";
        }
        return "redirect:/candidates";
    }

}
