package cz.uhk.ppro.pushntf.repository;

import cz.uhk.ppro.pushntf.model.Party;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PartyRepositoryTest {

    @Autowired
    private PartyRepository partyRepositoryTest;


    @Test
    void isShouldFindPartyByPartyId() {
        //give
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdeb";
        Party party = new Party(uuid,null,"John","Doe", Party.Status.REGISTERED,LocalDateTime.now());

        partyRepositoryTest.save(party);

        //when
        Party expected = partyRepositoryTest.findByPartyId(uuid);
        //then
        assertThat(expected).isNotNull();
    }

    @Test
    void isShouldDeletePartyByPartyId() {
        //give
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdeb";
        Party party = new Party(uuid,null,"John","Doe", Party.Status.REGISTERED,LocalDateTime.now());
        partyRepositoryTest.save(party);

        //when
        partyRepositoryTest.deleteByPartyId(uuid);
        Party expected = partyRepositoryTest.findByPartyId(uuid);
        //then
        assertThat(expected).isNull();
    }
}