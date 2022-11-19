package cz.uhk.ppro.pushntf.controller;

import cz.uhk.ppro.pushntf.api.PartyApi;
import cz.uhk.ppro.pushntf.exception.NotFoundException;
import cz.uhk.ppro.pushntf.exception.NotMatchException;
import cz.uhk.ppro.pushntf.exception.ObjectExistsException;
import cz.uhk.ppro.pushntf.model.Party;
import cz.uhk.ppro.pushntf.repository.PartyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@Log4j2
public class PartyApiController implements PartyApi {

    @Autowired
    private PartyRepository repository;

    @Override
    public ResponseEntity<Party> findByUuid(String uuid) throws Exception {

        Party party = repository.findByPartyId(uuid)
                .orElseThrow(() -> new NotFoundException("Party with this UUID id not found"));

        return ResponseEntity.ok().body(party);
    }

    @Override
    public Collection<Party> findParties() {
        return repository.findAll();
    }

    @Override
    public Party updateParty(String uuid, Party party) throws Exception  {
        if(party.getPartyId() == uuid) {
            Party oldParty = repository.findByPartyId(uuid)
                    .orElseThrow(() -> new NotFoundException("Party with this UUID id not found"));
            return repository.save(party);
        }
        else throw new NotMatchException("UUID in URL not match URL in body");
    }

    @Override
    public ResponseEntity<Party> createParty(Party body) throws Exception {

        if(repository.findByPartyId(body.getPartyId()).isPresent() == true){
            throw new ObjectExistsException("Entity with this UUID exists, if you want update, use PUT");
        }
        return new ResponseEntity<Party>(repository.save(body), HttpStatus.CREATED);
    }

    @Override

    public long deleteParty(String uuid) throws Exception {
        repository.deleteByPartyId(uuid);
        return 0;
    }
}
