package io.bhaskor.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.bhaskor.ppmtool.domain.Backlog;
import io.bhaskor.ppmtool.domain.Project;
import io.bhaskor.ppmtool.exceptions.CommonException;
import io.bhaskor.ppmtool.repository.BacklogRepository;
import io.bhaskor.ppmtool.repository.ProjectRepository;

@Service
public class ProjectService{

    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private BacklogRepository backlogRepository;

    public Project saveOrUpdateProject(Project project){
        try{
        	String upperCaseIdentifier = project.getProjectIdentifier().toUpperCase();
        	project.setProjectIdentifier(upperCaseIdentifier);
        	
        	if(null == project.getId()) {
        		Backlog backlog = new Backlog();
        		project.setBacklog(backlog);
        		backlog.setProject(project);
        		backlog.setProjectIdentifier(upperCaseIdentifier);
        	}
        	
        	if(null != project.getId()) {
        		project.setBacklog(backlogRepository.findByProjectIdentifier(upperCaseIdentifier));
        	}
        	
        	return projectRepository.save(project);
        }catch(Exception e) {
        	throw new CommonException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }
    }
    
    public Project findProjectByIdentifier(String projectIdentifier) {
    	
    	Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
    	
    	if(project == null) {
    		throw new CommonException("Project ID '"+projectIdentifier+"' dose not exists");
    	}
    	
    	return project;
    }
    
    public Iterable<Project> findAllProjects(){
    	return projectRepository.findAll();
    }
    
    public void deleteProjectByIdentifier(String projectIdentifier) {
    	Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
    	
    	if(project == null) {
    		throw new CommonException("Cannot delete projec with ID '"+projectIdentifier+"'. This project doesn't exist");
    	}
    	
    	projectRepository.delete(project);
    }
}