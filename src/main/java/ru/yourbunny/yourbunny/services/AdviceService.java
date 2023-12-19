package ru.yourbunny.yourbunny.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  ru.yourbunny.yourbunny.repositories.AdviceRepository;
import  ru.yourbunny.yourbunny.models.Advice;

import java.util.List;
import java.util.Optional;

@Service
public class AdviceService {

    private final AdviceRepository adviceRepository;

    @Autowired
    public AdviceService(AdviceRepository adviceRepository) {
        this.adviceRepository = adviceRepository;
    }

    public List<Advice> getAllAdvice() {
        return adviceRepository.findAll();
    }

    public Advice getAdviceById(Long id) {
        Optional<Advice> adviceOptional = adviceRepository.findById(id);
        return adviceOptional.orElseThrow(() -> new IllegalArgumentException("Совет с ID " + id + " не найден"));
    }

    public Advice addAdvice(Advice advice) {
        return adviceRepository.save(advice);
    }

    public void deleteAdvice(Long id) {
        adviceRepository.deleteById(id);
    }
}
