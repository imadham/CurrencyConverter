package com.imad.demo.repositories;

import com.imad.demo.model.HistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<HistoryModel, Long > {


}
