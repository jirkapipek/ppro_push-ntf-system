package cz.uhk.ppro.pushntf.service;

import cz.uhk.ppro.pushntf.model.Investment;
import cz.uhk.ppro.pushntf.repository.InvestmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class InvestmentService implements BaseEntityService<Investment> {

    @Autowired
    private InvestmentRepository repository;

    @Override
    public List<Investment> findAll() {
        return repository.findAll();
    }

    @Override
    public Investment findByUUID(String uuid) {
        Investment Investment = repository.findByInvestmentId(uuid);
        return Investment;
    }

    @Override
    public Investment create(Investment Investment) {
        return repository.save(Investment);
    }

    @Override
    public Investment update(Investment Investment) {
        return repository.save(Investment);
    }

    @Override
    public void deleteByUUID(String uuid) {
        repository.deleteByInvestmentId(uuid);

    }
}
