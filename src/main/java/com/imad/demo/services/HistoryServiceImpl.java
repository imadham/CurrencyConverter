package com.imad.demo.services;

import com.imad.demo.model.HistoryModel;
import com.imad.demo.repositories.HistoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Service for saving/loading data to DB
 * using Service is better when there is need for some operations before insert
 */
@Service
public class HistoryServiceImpl implements HistoryService{

    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public Set<HistoryModel> findAll() {
        Set<HistoryModel> result = new HashSet<>();
         historyRepository.findAll().forEach(result::add);
         return result;
    }

    @Override
    public HistoryModel findById(Long aLong) {
        return historyRepository.findById(aLong).orElse(null);
    }

    @Override
    public HistoryModel save(HistoryModel object) {
        return historyRepository.save(object);
    }

    @Override
    public void delete(HistoryModel object) {
        historyRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {

    }


}
