package ru.volnenko.se.service;

import java.time.LocalDate;
import java.util.Arrays;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volnenko.se.api.repository.IDomainRepository;
import ru.volnenko.se.api.repository.IProjectRepository;
import ru.volnenko.se.api.service.IProjectService;
import ru.volnenko.se.entity.Domain;
import ru.volnenko.se.entity.Project;
import java.util.List;

/**
 * @author Denis Volnenko
 */
@Service
@Transactional
public final class ProjectService implements IProjectService {

    @Autowired
    private IProjectRepository projectRepository;
    
    @Autowired
    private IDomainRepository domainRepository;

    @Override
    public Project createProject(final String name, final String domainName) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Invalid name of project to create: " + name);
        } else if (StringUtils.isEmpty(domainName)) {
            throw new IllegalArgumentException("Invalid name of domain for the new project: " + domainName);
        }
        Domain domain = domainRepository.findById(domainName).orElse(null);
        if (domain == null) {
            throw new IllegalArgumentException("No domain found with the given name: " + domainName);
        }
        Project project = new Project();
        project.setName(name);
        project.setCreated(LocalDate.now());
        project.setDomain(domain);
        return projectRepository.save(project);
    }

    @Override
    public Project merge(final Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Cannot merge null project!");
        }
        return projectRepository.save(project);
    }

    @Override
    public Project getProjectById(final String id) {
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("Invalid project id!");
        }
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public void removeProjectById(final String id) {
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("Invalid project id!");
        }
        Project project = projectRepository.findById(id).orElse(null);
        if (project == null) {
            return;
        }
        project.getDomain().getProjects().remove(project);
        projectRepository.delete(project);
    }

    @Override
    public List<Project> getListProject() {
        return projectRepository.findAll();
    }

    @Override
    public void clear() {
        projectRepository.deleteAll();
    }

    @Override
    public void merge(Project... projects) {
        if (projects.length == 0) {
            return;
        }
        Arrays.stream(projects).forEach(this::merge);
    }

}
