package br.com.eicon.desafio.controller.api;

import br.com.eicon.desafio.dto.CreditoDTO;
import java.math.BigDecimal;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreditosControllerOkTest {

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    private String getUrl(String path) {
        return "http://localhost:" + port + "/" + path;
    }

    @Test
    void tesCreditosNumeroNfse() throws Exception {

        String numeroNfse = "7891011";

        String numeroCredito = "789012";

        String url = getUrl("/api/creditos/" + numeroNfse);

        ResponseEntity<CreditoDTO[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(""), CreditoDTO[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        CreditoDTO[] creditos = response.getBody();

        assertThat(creditos.length).isEqualTo(2);

        CreditoDTO credito = Arrays.stream(creditos)
                .filter(cred -> numeroCredito.equals(cred.getNumeroCredito()))
                .findFirst()
                .get();

        assertThat(credito).isNotNull();

        assertThat(credito.getNumeroCredito()).isEqualTo(numeroCredito);

        assertThat(credito.getNumeroNfse()).isEqualTo(numeroNfse);

        assertThat(credito.getDataConstituicao()).isEqualTo("2024-02-26");

        assertThat(credito.getValorIssqn()).isEqualTo(new BigDecimal("1200.50"));

        assertThat(credito.getTipoCredito()).isEqualTo("ISSQN");

        assertThat(credito.getSimplesNacional()).isEqualTo("Nao");

        assertThat(credito.getAliquota()).isEqualTo(new BigDecimal("4.50"));

        assertThat(credito.getValorFaturado()).isEqualTo(new BigDecimal("25000.00"));

        assertThat(credito.getValorDeducao()).isEqualTo(new BigDecimal("4000.00"));

        assertThat(credito.getBaseCalculo()).isEqualTo(new BigDecimal("21000.00"));
    }

    @Test
    void tesCreditosNumeroCredito() throws Exception {

        String numeroCredito = "654321";

        String url = getUrl("/api/creditos/credito/" + numeroCredito);

        ResponseEntity<CreditoDTO> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(""), CreditoDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        CreditoDTO credito = response.getBody();

        assertThat(credito).isNotNull();

        assertThat(credito.getNumeroCredito()).isEqualTo(numeroCredito);

        assertThat(credito.getNumeroNfse()).isEqualTo("1122334");

        assertThat(credito.getDataConstituicao()).isEqualTo("2024-01-15");

        assertThat(credito.getValorIssqn()).isEqualTo(new BigDecimal("800.50"));

        assertThat(credito.getTipoCredito()).isEqualTo("Outros");

        assertThat(credito.getSimplesNacional()).isEqualTo("Sim");

        assertThat(credito.getAliquota()).isEqualTo(new BigDecimal("3.50"));

        assertThat(credito.getValorFaturado()).isEqualTo(new BigDecimal("20000.00"));

        assertThat(credito.getValorDeducao()).isEqualTo(new BigDecimal("3000.00"));

        assertThat(credito.getBaseCalculo()).isEqualTo(new BigDecimal("17000.00"));
    }
}
