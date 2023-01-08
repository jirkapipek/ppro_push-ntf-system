package cz.uhk.ppro.pushntf.service;

import cz.uhk.ppro.pushntf.model.Party;
import cz.uhk.ppro.pushntf.repository.PartyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PartyServiceTest {

    @Mock
    private PartyRepository partyRepository;
    private PartyService partyService;

    @BeforeEach
    void setUp() {
        partyService = new PartyService(partyRepository);
    }

    @Test
    void canFindAllParties() {
        //when
        partyService.findAll();
        //then
        verify(partyRepository).findAll();
    }

    @Test
    void findPartyByUUID() {
        //given
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdec";
        //when
        partyService.findByUUID(uuid);
        //then
        verify(partyRepository).findByPartyId(uuid);
    }

    @Test
    void canCreateParty() {
        //given
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdec";
        Party party = new Party(uuid, null, "John", "Doe", Party.Status.REGISTERED, LocalDateTime.now());

        //when
        partyService.create(party);

        //then
        ArgumentCaptor<Party> partyArgumentCaptor = ArgumentCaptor.forClass(Party.class);

        verify(partyRepository).save(partyArgumentCaptor.capture());

        Party capturedParty = partyArgumentCaptor.getValue();

        assertThat(capturedParty).isEqualTo(party);
    }

    @Test
    void canUpdateParty() {
        //given
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdec";
        Party party = new Party(uuid, null, "John", "Doe", Party.Status.REGISTERED, LocalDateTime.now());

        //when
        partyService.update(party);

        //then
        ArgumentCaptor<Party> partyArgumentCaptor = ArgumentCaptor.forClass(Party.class);

        verify(partyRepository).save(partyArgumentCaptor.capture());

        Party capturedParty = partyArgumentCaptor.getValue();

        assertThat(capturedParty).isEqualTo(party);
    }

    @Test
    void deletePartyByUUID() {
        //given
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdec";
        //when
        partyService.deleteByUUID(uuid);
        //then
        verify(partyRepository).deleteByPartyId(uuid);
    }
}