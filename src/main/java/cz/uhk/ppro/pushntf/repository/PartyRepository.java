package cz.uhk.ppro.pushntf.repository;

import cz.uhk.ppro.pushntf.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

public interface PartyRepository extends JpaRepository<Party, Long> {
    Optional<Party> findByPartyId(String uuid);
    @Transactional
    long deleteByPartyId(String uuid);
}
