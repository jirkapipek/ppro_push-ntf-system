package cz.uhk.ppro.pushntf.repository;

import cz.uhk.ppro.pushntf.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    Optional<Investment> findByInvestmentId(String uuid);

    @Transactional
    long deleteByInvestmentId(String uuid);
}
