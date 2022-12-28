package cz.uhk.ppro.pushntf.controller;

import cz.uhk.ppro.pushntf.api.InvestmentApi;
import cz.uhk.ppro.pushntf.exception.NotFoundException;
import cz.uhk.ppro.pushntf.exception.NotMatchException;
import cz.uhk.ppro.pushntf.exception.ObjectExistsException;
import cz.uhk.ppro.pushntf.model.Investment;
import cz.uhk.ppro.pushntf.repository.InvestmentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Log4j2
public class InvestmentApiController implements InvestmentApi {

    @Autowired
    private InvestmentRepository repository;

    @Override
    public ResponseEntity<Investment> findByUuid(String uuid) throws Exception {

        Investment investment = repository.findByInvestmentId(uuid)
                .orElseThrow(() -> new NotFoundException("Investment with this UUID id not found"));
        log.info("Returned investment with UUID " +uuid);
        return ResponseEntity.ok().body(investment);
    }

    @Override
    public Collection<Investment> findInvestments() {
        log.info("Returned " +repository.findAll().size()+ " parties");
        return repository.findAll();
    }

    @Override
    public Investment updateInvestment(String uuid, Investment investment) throws Exception  {
        System.out.println(investment.getInvestmentId() +"\n");
        System.out.println(uuid);
        if (investment.getInvestmentId() != null && !investment.getInvestmentId().equals(uuid)) throw new NotMatchException("UUID in URL not match URL in body");
        Investment investmentDb = repository.findByInvestmentId(uuid).orElseThrow(() -> new NotFoundException("Investment with this UUID id not found"));
        if (investment.getBuyCurrency() != null) investmentDb.setBuyCurrency(investment.getBuyCurrency());
        if (investment.getBuyPrice() != null) investmentDb.setBuyPrice(investment.getBuyPrice());
        if (investment.getSellCurrency() != null) investmentDb.setSellCurrency(investment.getSellCurrency());
        if (investment.getSellPrice() != null) investmentDb.setSellPrice(investment.getSellPrice());
        if (investment.getCategory() != null) investmentDb.setCategory(investment.getCategory());
        if (investment.getType() != null) investmentDb.setType(investment.getType());

        log.info("Investment with UUID " +uuid+ " has been updated");
        return repository.save(investmentDb);
    }

    @Override
    public ResponseEntity<Investment> createInvestment(Investment body) throws Exception {

        if(repository.findByInvestmentId(body.getInvestmentId()).isPresent() == true){
            throw new ObjectExistsException("Entity with this UUID exists, if you want update, use PUT");
        }
        log.info("Investment with UUID " +body.getInvestmentId()+ " has been created");
        return new ResponseEntity<Investment>(repository.save(body), HttpStatus.CREATED);
    }

    @Override

    public long deleteInvestment(String uuid) throws Exception {
        repository.findByInvestmentId(uuid).orElseThrow(() -> new NotFoundException("Investment with this UUID id not found"));
        repository.deleteByInvestmentId(uuid);
        log.info("Investment with UUID " +uuid+ " has been deleted");
        return 1;
    }
}
