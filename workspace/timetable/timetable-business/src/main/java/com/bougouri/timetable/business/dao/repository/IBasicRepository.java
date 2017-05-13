package com.bougouri.timetable.business.dao.repository;

import java.io.Serializable;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.bougouri.timetable.business.model.AbstractEntity;

@NoRepositoryBean
public interface IBasicRepository<T extends AbstractEntity, ID extends Serializable> extends JpaRepository<T, ID> {

	@Query("select entity from #{#entityName} entity")
	Stream<T> getAllAsStream();
}
