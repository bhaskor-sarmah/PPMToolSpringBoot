package io.bhaskor.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.bhaskor.ppmtool.domain.Backlog;
import io.bhaskor.ppmtool.domain.ProjectTask;
import io.bhaskor.ppmtool.exceptions.CommonException;
import io.bhaskor.ppmtool.repository.BacklogRepository;
import io.bhaskor.ppmtool.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;

	@Autowired
	private ProjectTaskRepository projectTaskRepository;

	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		// Exception project not found
		try {
			// All PTs to added to a specific project, project != null, BL exists

			Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

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

//			if (projectTask.getPriority() == 0 || projectTask.getPriority() == null) {
//				projectTask.setPriority(3);
//			}

			// Initial status when status is null
			if (projectTask.getStatus() == null || projectTask.getStatus() == "") {
				projectTask.setStatus("TO_DO");
			}

			return projectTaskRepository.save(projectTask);
		} catch (Exception e) {
			throw new CommonException("Some Exception Ocurred");
		}
	}
}
