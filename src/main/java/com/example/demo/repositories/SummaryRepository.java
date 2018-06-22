package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Summary;


public interface SummaryRepository extends CrudRepository<Summary, Integer>{

}
