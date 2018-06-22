package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.News;

public interface NewsRepository extends CrudRepository<News, Integer>{

}
