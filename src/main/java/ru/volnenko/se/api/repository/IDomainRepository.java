package ru.volnenko.se.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volnenko.se.entity.Domain;

/**
 * @author Denis Volnenko
 */
public interface IDomainRepository extends JpaRepository<Domain, String> {
    
}
