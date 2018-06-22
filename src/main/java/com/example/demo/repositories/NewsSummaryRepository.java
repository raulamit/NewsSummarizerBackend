package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.NewsSummary;

public interface NewsSummaryRepository extends CrudRepository<NewsSummary, Integer>{

}
