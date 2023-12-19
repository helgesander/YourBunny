package ru.yourbunny.yourbunny.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.yourbunny.yourbunny.models.Advice;
import java.util.Optional;

public interface AdviceRepository extends JpaRepository <Advice, Long> {
    @Transactional
    Optional<Advice> findById(long id);

}
