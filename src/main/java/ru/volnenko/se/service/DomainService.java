package ru.volnenko.se.service;

import java.util.List;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volnenko.se.api.repository.IDomainRepository;
import ru.volnenko.se.api.repository.IProjectRepository;
import ru.volnenko.se.api.repository.ITaskRepository;
import ru.volnenko.se.api.service.IDomainService;
import ru.volnenko.se.entity.Domain;

/**
 * @author Denis Volnenko
 */
@Service
@Transactional
public final class DomainService implements IDomainService {
    
    @Autowired
    private IProjectRepository projectRepository;
    
    @Autowired
    private ITaskRepository taskRepository;
    
    @Autowired
    private IDomainRepository domainRepository;

    @Override
    @Transactional
    public void merge(Domain domain) {
        if (domain == null) {
            throw new IllegalArgumentException("The domain to merge is null!");
        }
        domainRepository.save(domain);
    }

    @Override
    public Domain createDomain(String name) {
        if(StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Invalid name of domain to create!");
        }
        Domain domain = new Domain();
        domain.setName(name);
        return domainRepository.save(domain);
    }

    @Override
    public void removeByName(String name) {
        if(StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Invalid name of domain to remove!");
        }
        domainRepository.deleteById(name);
    }

    @Override
    public List<Domain> findAll() {
        return domainRepository.findAll();
    }

    @Override
    public Domain findByName(String name) {
        if(StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Invalid name of domain to find!");
        }
        return domainRepository.findById(name).orElse(null);
    }

}
