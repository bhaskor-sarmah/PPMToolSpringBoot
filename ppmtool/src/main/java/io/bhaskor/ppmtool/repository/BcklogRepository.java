package io.bhaskor.ppmtool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.bhaskor.ppmtool.domain.Backlog;

@Repository
public interface BcklogRepository extends CrudRepository<Backlog, Long>{

}
