package com.example.kameleontask.data.repository;

import com.example.kameleontask.buisness.service.VoteService;
import com.example.kameleontask.data.entity.Quote;
import com.example.kameleontask.data.entity.Vote;
import com.example.kameleontask.util.logging.annotation.LoggableDbQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    @LoggableDbQuery(description = "Save the vote")
    @Override
    <S extends Vote> S save(S vote);

}
