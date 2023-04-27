package com.precize.io.app.repositories;

import com.precize.io.app.models.SATResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SATResultRepository extends JpaRepository<SATResult, String> {
    List<SATResult> findAllByOrderBySatScoreDesc();

    SATResult findByName(String name);

    @Transactional
    void deleteByName(String name);
}