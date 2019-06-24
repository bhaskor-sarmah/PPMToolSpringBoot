package io.bhaskor.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.bhaskor.ppmtool.domain.Project;
import io.bhaskor.ppmtool.repository.ProjectRepository;

@Service
public class ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        // Logic to check ownership etc..

        return projectRepository.save(project);
    }
}