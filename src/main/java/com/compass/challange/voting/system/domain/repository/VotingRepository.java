package com.compass.challange.voting.system.domain.repository;

import com.compass.challange.voting.system.api.dto.output.VotingResultDTO;
import com.compass.challange.voting.system.domain.model.Voting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotingRepository extends JpaRepository<Voting, Long> {

    boolean existsByAssociateDocumentAndPautaId(String associateDocument, Long pautaId);

    @Query(name = "Voting.getVotingResult", nativeQuery = true)
    Optional<VotingResultDTO> getVotingResult(@Param("pautaId") Long pautaId);
}
