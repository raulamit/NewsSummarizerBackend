package com.example.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.NewsSummary;
import com.example.demo.models.User;

public interface NewsSummaryRepository extends CrudRepository<NewsSummary, Integer>{
	
	@Query("SELECT s FROM NewsSummary s WHERE s.category=:category")
	Iterable<NewsSummary> findSummaryByCategory(@Param("category") String c);

}
