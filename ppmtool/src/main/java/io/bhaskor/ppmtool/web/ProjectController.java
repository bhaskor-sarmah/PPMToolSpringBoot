package io.bhaskor.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.bhaskor.ppmtool.domain.Project;
import io.bhaskor.ppmtool.services.MapValidationErrorService;
import io.bhaskor.ppmtool.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController{

    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
    	
    	ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
    	
    	if(null != errorMap) {
    		return errorMap;
    	}
    	
    	Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }
}