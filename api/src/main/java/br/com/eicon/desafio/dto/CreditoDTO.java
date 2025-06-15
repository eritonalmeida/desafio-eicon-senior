package br.com.eicon.desafio.dto;

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

    public CreditoDTO() {

    }
}
