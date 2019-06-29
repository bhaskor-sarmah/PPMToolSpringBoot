package io.bhaskor.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.bhaskor.ppmtool.domain.Project;
import io.bhaskor.ppmtool.exceptions.ProjectIdException;
import io.bhaskor.ppmtool.repository.ProjectRepository;

@Service
public class ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try{
        	project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch(Exception e) {
        	throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }
    }
    
    public Project findProjectByIdentifier(String projectIdentifier) {
    	
    	Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
    	
    	if(project == null) {
    		throw new ProjectIdException("Project ID '"+projectIdentifier+"' dose not exists");
    	}
    	
    	return project;
    }
    
    public Iterable<Project> findAllProjects(){
    	return projectRepository.findAll();
    }
}