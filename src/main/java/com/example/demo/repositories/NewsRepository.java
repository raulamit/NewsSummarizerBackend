package com.example.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.News;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends CrudRepository<News, Integer>{
    @Query("SELECT n FROM News n WHERE n.sourceId = :sourceId")
    List<News> findNewsBySourceId(@Param("sourceId") String sourceId);
}
