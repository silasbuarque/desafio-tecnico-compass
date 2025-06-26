package com.compass.challange.voting.system.domain.repository;

import com.compass.challange.voting.system.domain.model.Pauta;
import com.compass.challange.voting.system.domain.model.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotingSessionRepository extends JpaRepository<VotingSession, Long> {

    Optional<VotingSession> findByPauta(Pauta pauta);
    boolean existsByPautaId(Long pautaId);

}
