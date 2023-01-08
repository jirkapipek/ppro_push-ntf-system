package cz.uhk.ppro.pushntf.repository;

import cz.uhk.ppro.pushntf.model.Investment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvestmentRepositoryTest {

    @Autowired
    private InvestmentRepository investmentRepositoryTest;


    @Test
    void isShouldFindInvestmentByInvestmentId() {
        //given
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdeb";
        Investment investment = new Investment(uuid, Investment.Category.CRYPTOCURRENCIES, "Bitcoin",255.55, "EUR", 34000.5,"CZK",LocalDateTime.now(), LocalDateTime.now());

        investmentRepositoryTest.save(investment);

        //when
        Investment expected = investmentRepositoryTest.findByInvestmentId(uuid);
        //then
        assertThat(expected).isNotNull();
    }

    @Test
    void isShouldDeleteInvestmentByInvestmentId() {
        //give
        String uuid = "5567d933-5d2a-46e3-a58e-34d614e9bdeb";
        Investment investment = new Investment(uuid, Investment.Category.CRYPTOCURRENCIES, "Bitcoin",255.55, "EUR", 34000.5,"CZK",LocalDateTime.now(), LocalDateTime.now());
        investmentRepositoryTest.save(investment);

        //when
        investmentRepositoryTest.deleteByInvestmentId(uuid);
        Investment expected = investmentRepositoryTest.findByInvestmentId(uuid);
        //then
        assertThat(expected).isNull();
    }

}