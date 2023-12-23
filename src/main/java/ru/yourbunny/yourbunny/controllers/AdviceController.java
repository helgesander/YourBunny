package ru.yourbunny.yourbunny.controllers;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yourbunny.yourbunny.repositories.AdviceRepository;
import ru.yourbunny.yourbunny.models.Advice;
import java.util.List;

@RestController
@RequestMapping("/advice")
@Tag(name = "Советы", description = "Сервис по подбору советов при знакомстве")
public class AdviceController {

    private final AdviceRepository adviceRepository;
    @Autowired
    public AdviceController(AdviceRepository adviceRepository) {
        this.adviceRepository = adviceRepository;
    }
    @GetMapping("")
    public List<Advice> getAllAdvice() {
        return adviceRepository.findAll();
    }
    @GetMapping("/{id}")
    public Advice getAdviceById(@PathVariable Long id) {
        return adviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Совет с ID " + id + " не найден"));
    }
}
