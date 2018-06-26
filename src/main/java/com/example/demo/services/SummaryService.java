package com.example.demo.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Advertisement;
import com.example.demo.models.NewsSummary;
import com.example.demo.models.Summary;
import com.example.demo.models.User;
import com.example.demo.repositories.AdvertisementRepository;
import com.example.demo.repositories.NewsSummaryRepository;
import com.example.demo.repositories.SummaryRepository;

import static com.example.demo.constants.Constants.FRONT_END_SERVER;

@RestController
@CrossOrigin (origins = FRONT_END_SERVER, maxAge = 3600, allowCredentials="true")
public class SummaryService {

    @Autowired
    SummaryRepository summaryRepository;

    @Autowired
    NewsSummaryRepository newsSummaryRepo;

    @Autowired
    AdvertisementRepository advertisementRepository;

    //CREATE NEW Summary
    @PostMapping("/api/summary")
    public Summary createSummary(
            @RequestBody Summary summary) {
        return summaryRepository.save(summary);
    }

    //FIND Summary BY Summary ID
    @GetMapping("/api/summary/{summaryId}")
    public Summary findSummaryById(
            @PathVariable("summaryId") int summaryId) {

        Optional<Summary> data = summaryRepository.findById(summaryId);
        if (data.isPresent())
            return data.get();

        return null;
    }

    //UPDATE Summary
    @PutMapping("/api/summary/{summaryId}")
    public Summary updateSummary(@PathVariable("summaryId") int summaryId,
                                 @RequestBody Summary newSummary) {

        Optional<Summary> data = summaryRepository.findById(summaryId);
        if (data.isPresent()) {
            return summaryRepository.save(newSummary);
        }
        return null;

    }

    //DELETE Summary
    @DeleteMapping("/api/summary/{summaryId}")
    public void deleteSummary(@PathVariable("summaryId") int summaryId) {
        summaryRepository.deleteById(summaryId);
    }


    @GetMapping("/api/summary/batch/{batchNum}")
    public Iterable<Summary> getSummaryByBatch(
            @RequestParam(name = "category", required = false)
                    String category) {
        List<Summary> summaries = new ArrayList<>();
        List<NewsSummary> newsSummaries = null;
        if (category != null) {
            newsSummaries = (List<NewsSummary>) newsSummaryRepo.findSummaryByCategory(category);
        } else {
            newsSummaries = (List<NewsSummary>) newsSummaryRepo.findAll();
        }
        List<Advertisement> advertisements = new ArrayList<Advertisement>();
        advertisements = (List<Advertisement>) advertisementRepository.findAll();
        int x = newsSummaries.size() / 2;
        for (NewsSummary n : newsSummaries)
            summaries.add(n);
        Random random = new Random();
        for (int i = 0; i < newsSummaries.size() / 2; i++)
            summaries.add(random.nextInt(summaries.size()),advertisements.get(i));
        return summaries;
    }
}
