package io.bhaskor.ppmtool.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.bhaskor.ppmtool.domain.Backlog;
import io.bhaskor.ppmtool.domain.Project;
import io.bhaskor.ppmtool.domain.ProjectTask;
import io.bhaskor.ppmtool.exceptions.CommonException;
import io.bhaskor.ppmtool.repository.BacklogRepository;
import io.bhaskor.ppmtool.repository.ProjectRepository;
import io.bhaskor.ppmtool.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;

	@Autowired
	private ProjectTaskRepository projectTaskRepository;

	@Autowired
	private ProjectRepository projectRepository;

	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

		try {

			// All PTs to added to a specific project, project != null, BL exists

			Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

			// Exception project not found
			if (null == backlog) {
				throw new CommonException("Project not found Exception");
			} else {
				// set the BL to the projectTask
				projectTask.setBacklog(backlog);
				// Project Sequence = [ProjectIdentifier]-[ID] of the task in the project
				Integer backLogSequence = backlog.getPTSequence();

				// Update the backlog sequence
				backLogSequence++;
				backlog.setPTSequence(backLogSequence);

				// Add sequence to projectTask
				projectTask.setProjectSequemnce(projectIdentifier + "-" + backLogSequence);
				projectTask.setProjectIdentifier(projectIdentifier);

				// Initial priority when priority null
				if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
					projectTask.setPriority(3);
				}

				// Initial status when status is null
				if (projectTask.getStatus() == null || projectTask.getStatus() == "") {
					projectTask.setStatus("TO_DO");
				}

				return projectTaskRepository.save(projectTask);
			}
		} catch (Exception e) {
			if (e instanceof CommonException) {
				throw new CommonException("Project not Found");
			} else {
				throw new CommonException("Some Error has ocurred");
			}
		}
	}

	public Iterable<ProjectTask> findBacklogById(String id) {
		Project project = projectRepository.findByProjectIdentifier(id);

		if (project == null) {
			throw new CommonException("Project with Id : " + id + " doesn't exist");
		}

		return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
	}

	public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id) {
		
		// make sure we are searching on the right backlog
		Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
		if(backlog==null) {
			throw new CommonException("Project ID: '"+backlog_id+"' not found");
		}
		
		// make sure that our task exists
		ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
		if(projectTask==null) {
			throw new CommonException("Project Task '"+pt_id+"' not found");
		}
		
		// make sure that the backlog/project is in the path corresponds to the right project
		if(!projectTask.getProjectIdentifier().equals(backlog_id)) {
			throw new CommonException("Project Task '"+pt_id+"' does not exist in project: '"+backlog_id+"'");
		}
		
		return projectTask;
	}
	
	public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id) {

		//Update project task//find existing project task
		
		//replace it with updated task
		
		//save update
		
		
		ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);
		
		if(!projectTask.getProjectSequemnce().equals(updatedTask.getProjectSequemnce())) {
			throw new CommonException("Project Sequence '"+pt_id+"' not matching");
		}
		
		projectTask = updatedTask;
		
		return projectTaskRepository.save(projectTask);
		
	}
	
	public void deleteProjectTask(String backlog_id, String pt_id) {
		
		ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);
		
		Backlog backlog = projectTask.getBacklog();
		List<ProjectTask> pts = backlog.getProjectTask();
		pts.remove(projectTask);
		backlogRepository.save(backlog);
		
		projectTaskRepository.delete(projectTask);
	}
}
