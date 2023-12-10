package com.example.kameleontask.data.repository;

import com.example.kameleontask.data.entity.Quote;
import com.example.kameleontask.util.logging.annotation.LoggableDbQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    @LoggableDbQuery(description = "Save the quote")
    @Override
    <S extends Quote> S save(S quote);

    @LoggableDbQuery(description = "Get random quote")
    @Query("SELECT Q FROM Quote Q ORDER BY RAND() limit 1")
    Optional<Quote> findRandomQuote();

    @LoggableDbQuery(description = "Find first N quotes by negative or positive votes")
    @Query("SELECT Q FROM Quote Q LEFT JOIN Q.voteSet V WHERE V.isPositive = :isPositive GROUP BY Q.id ORDER BY COUNT(V) DESC")
    List<Quote> findTopQuotesOrderByPositiveVotes(@Param("isPositive") Short isPositive);

}
