package cz.uhk.ppro.pushntf.repository;

import cz.uhk.ppro.pushntf.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
    Party findByPartyId(String uuid);

    @Transactional
    long deleteByPartyId(String uuid);
}
