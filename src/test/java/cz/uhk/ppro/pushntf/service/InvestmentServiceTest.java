package cz.uhk.ppro.pushntf.service;

import cz.uhk.ppro.pushntf.model.Investment;
import cz.uhk.ppro.pushntf.repository.InvestmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InvestmentServiceTest {

    @Mock
    private InvestmentRepository investmentRepository;
    private InvestmentService investmentService;

    @BeforeEach
    void setUp() {
        investmentService = new InvestmentService(investmentRepository);
    }

    @Test
    void canFindAllInvestments() {
        //when
        investmentService.findAll();
        //then
        verify(investmentRepository).findAll();
    }

    @Test
    void findInvestmentByUUID() {
        //given
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdec";
        //when
        investmentService.findByUUID(uuid);
        //then
        verify(investmentRepository).findByInvestmentId(uuid);
    }

    @Test
    void canCreateInvestment() {
        //given
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdeb";
        Investment investment = new Investment(uuid, Investment.Category.CRYPTOCURRENCIES, "Bitcoin",255.55, "EUR", 34000.5,"CZK",LocalDateTime.now(), LocalDateTime.now());

        //when
        investmentService.create(investment);

        //then
        ArgumentCaptor<Investment> investmentArgumentCaptor = ArgumentCaptor.forClass(Investment.class);

        verify(investmentRepository).save(investmentArgumentCaptor.capture());

        Investment capturedInvestment = investmentArgumentCaptor.getValue();

        assertThat(capturedInvestment).isEqualTo(investment);
    }

    @Test
    void canUpdateInvestment() {
        //given
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdeb";
        Investment investment = new Investment(uuid, Investment.Category.CRYPTOCURRENCIES, "Bitcoin",255.55, "EUR", 34000.5,"CZK",LocalDateTime.now(), LocalDateTime.now());

        //when
        investmentService.update(investment);

        //then
        ArgumentCaptor<Investment> investmentArgumentCaptor = ArgumentCaptor.forClass(Investment.class);

        verify(investmentRepository).save(investmentArgumentCaptor.capture());

        Investment capturedInvestment = investmentArgumentCaptor.getValue();

        assertThat(capturedInvestment).isEqualTo(investment);
    }

    @Test
    void deleteInvestmentByUUID() {
        //given
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdec";
        //when
        investmentService.deleteByUUID(uuid);
        //then
        verify(investmentRepository).deleteByInvestmentId(uuid);
    }
}