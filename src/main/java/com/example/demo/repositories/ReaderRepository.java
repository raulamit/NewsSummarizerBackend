package com.example.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Editor;
import com.example.demo.models.Reader;

public interface ReaderRepository extends CrudRepository<Reader, Integer>{
	

}
