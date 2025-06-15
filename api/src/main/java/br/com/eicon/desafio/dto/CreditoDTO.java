package br.com.eicon.desafio.dto;

import br.com.eicon.desafio.entity.Credito;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class CreditoDTO {

    private String numeroCredito;

    private String numeroNfse;

    private LocalDate dataConstituicao;

    private BigDecimal valorIssqn;

    private String tipoCredito;

    private String simplesNacional;

    private BigDecimal aliquota;

    private BigDecimal valorFaturado;

    private BigDecimal valorDeducao;

    private BigDecimal baseCalculo;

    public CreditoDTO1() {

    }

    public CreditoDTO1(Credito credito) {

        numeroCredito = credito.getNumeroCredito();

        numeroNfse = credito.getNumeroNfse();

        dataConstituicao = credito.getDataConstituicao();

        valorIssqn = credito.getValorIssqn();

        tipoCredito = credito.getTipoCredito();

        simplesNacional = credito.isSimplesNacional() ? "Sim" : "Nao";

        aliquota = credito.getAliquota();

        valorFaturado = credito.getValorFaturado();

        valorDeducao = credito.getValorDeducao();

        baseCalculo = credito.getBaseCalculo();
    }
}
