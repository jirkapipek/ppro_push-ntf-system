package cz.uhk.ppro.pushntf.service;

import cz.uhk.ppro.pushntf.model.Party;
import cz.uhk.ppro.pushntf.repository.PartyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PartyService implements BaseEntityService<Party> {

    @Autowired
    private PartyRepository repository;

    @Override
    public List<Party> findAll() {
        return repository.findAll();
    }

    @Override
    public Party findByUUID(String uuid) {
        Party Party = repository.findByPartyId(uuid);
        return Party;
    }

    @Override
    public Party create(Party Party) {
        return repository.save(Party);
    }

    @Override
    public Party update(Party Party) {
        return repository.save(Party);
    }

    @Override
    public void deleteByUUID(String uuid) {
        repository.deleteByPartyId(uuid);

    }
}
