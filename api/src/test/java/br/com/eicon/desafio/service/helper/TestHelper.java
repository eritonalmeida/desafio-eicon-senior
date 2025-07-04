package br.com.eicon.desafio.service.helper;

import br.com.eicon.desafio.entity.Credito;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

public class TestHelper {

    public static Credito getCredito() {
        Credito credito = new Credito();

        credito.setNumeroCredito("7891011");
        credito.setNumeroNfse("123456");
        credito.setDataConstituicao(LocalDate.of(2024, Month.FEBRUARY, 25));
        credito.setValorIssqn(new BigDecimal("1500.75"));
        credito.setTipoCredito("ISSQN");
        credito.setSimplesNacional(true);
        credito.setAliquota(new BigDecimal("5.0"));
        credito.setValorFaturado(new BigDecimal("30000.00"));
        credito.setValorDeducao(new BigDecimal("5000.00"));
        credito.setBaseCalculo(new BigDecimal("25000.00"));

        return credito;
    }
}
