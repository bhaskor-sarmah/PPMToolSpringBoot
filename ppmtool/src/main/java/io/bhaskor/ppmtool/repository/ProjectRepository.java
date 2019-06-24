package io.bhaskor.ppmtool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.bhaskor.ppmtool.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    Iterable<Project> findAllById(Iterable<Long> iterable);
}