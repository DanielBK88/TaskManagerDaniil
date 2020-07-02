package ru.volnenko.se.api.service;

import java.util.List;
import ru.volnenko.se.entity.Domain;

/**
 * @author Denis Volnenko
 */
public interface IDomainService {
    
    Domain createDomain(String name);
    
    void removeByName(String name);
    
    void merge(Domain domain);
    
    List<Domain> findAll();
    
    Domain findByName(String name);

}
