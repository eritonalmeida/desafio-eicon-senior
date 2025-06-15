package br.com.eicon.desafio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "credito")
public class Credito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_credito", length = 50)
    private String numeroCredito;

    @Column(name = "numero_nfse", length = 50)
    private String numeroNfse;

    @Column(name = "data_constituicao")
    private LocalDate dataConstituicao;

    @Column(name = "valor_issqn", precision = 15, scale = 2)
    private BigDecimal valorIssqn;

    @Column(name = "tipo_credito", length = 50)
    private String tipoCredito;

    @Column(name = "simples_nacional")
    private boolean simplesNacional;

    @Column(name = "aliquota", precision = 5, scale = 2)
    private BigDecimal aliquota;

    @Column(name = "valor_faturado", precision = 15, scale = 2)
    private BigDecimal valorFaturado;

    @Column(name = "valor_deducao", precision = 15, scale = 2)
    private BigDecimal valorDeducao;

    @Column(name = "base_calculo", precision = 15, scale = 2)
    private BigDecimal baseCalculo;
}
