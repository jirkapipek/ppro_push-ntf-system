package cz.uhk.ppro.pushntf.controller;

import cz.uhk.ppro.pushntf.api.InvestmentApi;
import cz.uhk.ppro.pushntf.exception.NotFoundException;
import cz.uhk.ppro.pushntf.exception.NotMatchException;
import cz.uhk.ppro.pushntf.exception.NotValidException;
import cz.uhk.ppro.pushntf.exception.ObjectExistsException;
import cz.uhk.ppro.pushntf.model.Investment;
import cz.uhk.ppro.pushntf.service.InvestmentService;
import cz.uhk.ppro.pushntf.utils.UUIDValidator;
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
    private InvestmentService investmentService;
    @Autowired
    private UUIDValidator validator;
    @Override
    public ResponseEntity<Investment> findByUuid(String uuid) throws Exception {

        Investment investment = investmentService.findByUUID(uuid);

        if (investment == null) throw new NotFoundException("Investment with this UUID id not found");

        log.info("Returned investment with UUID " + uuid);
        return ResponseEntity.ok().body(investment);
    }

    @Override
    public Collection<Investment> findInvestments() {

        log.info("Returned " + investmentService.findAll().size() + " parties");
        return investmentService.findAll();
    }

    @Override
    public Investment updateInvestment(String uuid, Investment investment) throws Exception {

        if (investment.getInvestmentId() != null && !investment.getInvestmentId().equals(uuid))
            throw new NotMatchException("UUID in URL not match URL in body");

        Investment investmentDb = investmentService.findByUUID(uuid);
        if (investmentDb == null) throw new NotFoundException("Investment with this UUID id not found");

        if (investment.getBuyCurrency() != null) investmentDb.setBuyCurrency(investment.getBuyCurrency());
        if (investment.getBuyPrice() != null) investmentDb.setBuyPrice(investment.getBuyPrice());
        if (investment.getSellCurrency() != null) investmentDb.setSellCurrency(investment.getSellCurrency());
        if (investment.getSellPrice() != null) investmentDb.setSellPrice(investment.getSellPrice());
        if (investment.getCategory() != null) investmentDb.setCategory(investment.getCategory());
        if (investment.getType() != null) investmentDb.setType(investment.getType());

        log.info("Investment with UUID " + uuid + " has been updated");
        return investmentService.update(investmentDb);
    }

    @Override
    public ResponseEntity<Investment> createInvestment(Investment body) throws Exception {

        if(!validator.isUuidStringValid(body.getInvestmentId())) throw new NotValidException("UUID is not valid");

        if (investmentService.findByUUID(body.getInvestmentId()) != null)
            throw new ObjectExistsException("Entity with this UUID exists, if you want update, use PUT");

        log.info("Investment with UUID " + body.getInvestmentId() + " has been created");
        return new ResponseEntity<Investment>(investmentService.create(body), HttpStatus.CREATED);
    }

    @Override

    public long deleteInvestment(String uuid) throws Exception {
        if (investmentService.findByUUID(uuid) == null)
            throw new NotFoundException("Investment with this UUID id not found");

        investmentService.deleteByUUID(uuid);
        log.info("Investment with UUID " + uuid + " has been deleted");
        return 1;
    }
}
