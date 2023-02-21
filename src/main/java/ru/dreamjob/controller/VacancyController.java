package ru.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dreamjob.model.Vacancy;
import ru.dreamjob.repository.MemoryVacancyRepository;
import ru.dreamjob.repository.VacancyRepository;
import ru.dreamjob.service.SimpleVacancyService;

@Controller
@RequestMapping("/vacancies")
public class VacancyController {

    private final SimpleVacancyService service = SimpleVacancyService.getInstance();

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("vacancies", service.findAll());
        return "vacancies/list";
    }

    @GetMapping("/create")
    public String getCreationPage() {
        return "vacancies/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Vacancy vacancy) {
        service.save(vacancy);
        return "redirect:/vacancies";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var vacancyOptional = service.findById(id);
        if (vacancyOptional.isEmpty()) {
            model.addAttribute("message", "Вакансия с указанным идентификатором не найдена");
            return "errors/404";
        }
        model.addAttribute("vacancy", vacancyOptional.get());
        return "vacancies/one";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Vacancy vacancy, Model model) {
        var updated = service.update(vacancy);
        if (!updated) {
            model.addAttribute("message", "Вакансия с указанным идентификатором не найдена");
            return "error/404";
        }
        return "redirect:/vacancies";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        var deleted = service.deleteById(id);
        if (!deleted) {
            model.addAttribute("message", "Вакансия с указанным идентификатором не найдена");
            return "error/404";
        }
        return "redirect:/vacancies";
    }

}
