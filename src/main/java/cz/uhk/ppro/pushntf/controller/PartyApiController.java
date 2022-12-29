package cz.uhk.ppro.pushntf.controller;

import cz.uhk.ppro.pushntf.api.PartyApi;
import cz.uhk.ppro.pushntf.exception.NotFoundException;
import cz.uhk.ppro.pushntf.exception.NotMatchException;
import cz.uhk.ppro.pushntf.exception.NotValidException;
import cz.uhk.ppro.pushntf.exception.ObjectExistsException;
import cz.uhk.ppro.pushntf.model.Party;
import cz.uhk.ppro.pushntf.service.PartyService;
import cz.uhk.ppro.pushntf.utils.UUIDValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Log4j2
public class PartyApiController implements PartyApi {

    @Autowired
    private PartyService partyService;
    @Autowired
    private UUIDValidator validator;
    @Override
    public ResponseEntity<Party> findByUuid(String uuid) throws Exception {

        Party party = partyService.findByUUID(uuid);
        if(party == null) throw new NotFoundException("Party with this UUID id not found");
        log.info("Returned party with UUID " + uuid);
        return ResponseEntity.ok().body(party);
    }

    @Override
    public Collection<Party> findParties() {
        log.info("Returned " + partyService.findAll().size() + " parties");
        return partyService.findAll();
    }

    @Override
    public Party updateParty(String uuid, Party party) throws Exception {

        if (party.getPartyId() != null && !party.getPartyId().equals(uuid))
            throw new NotMatchException("UUID in URL not match URL in body");

        Party partyDb = partyService.findByUUID(uuid);
        if(partyDb == null) throw new NotFoundException("Party with this UUID id not found");

        if (party.getFirstName() != null) partyDb.setFirstName(party.getFirstName());
        if (party.getLastName() != null) partyDb.setLastName(party.getLastName());
        if (party.getIco() != null) partyDb.setIco(party.getIco());
        if (party.getRegStatus() != null) partyDb.setRegStatus(party.getRegStatus());

        log.info("Party with UUID " + uuid + " has been updated");
        return partyService.update(partyDb);
    }

    @Override
    public ResponseEntity<Party> createParty(Party body) throws Exception {

        if(!validator.isUuidStringValid(body.getPartyId())) throw new NotValidException("UUID is not valid");

        if (partyService.findByUUID(body.getPartyId()) != null) throw new ObjectExistsException("Entity with this UUID exists, if you want update, use PUT");

        log.info("Party with UUID " + body.getPartyId() + " has been created");
        return new ResponseEntity<Party>(partyService.create(body), HttpStatus.CREATED);
    }

    @Override

    public long deleteParty(String uuid) throws Exception {
        if(partyService.findByUUID(uuid) == null) throw new NotFoundException("Party with this UUID id not found");

        partyService.deleteByUUID(uuid);

        log.info("Party with UUID " + uuid + " has been deleted");
        return 1;
    }
}
