package br.com.eicon.desafio.controller.api;

import br.com.eicon.desafio.dto.CreditoDTO;
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
public class CreditosControllerErrorTest {

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    private String getUrl(String path) {
        return "http://localhost:" + port + "/" + path;
    }

    @Test
    void tesCreditosNumeroNfse() throws Exception {

        String numeroNfse = "123";

        String url = getUrl("/api/creditos/" + numeroNfse);

        ResponseEntity<CreditoDTO[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(""), CreditoDTO[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        CreditoDTO[] creditos = response.getBody();

        assertThat(creditos.length).isEqualTo(0);
    }

    @Test
    void tesCreditosNumeroCredito() throws Exception {

        String numeroCredito = "456";

        String url = getUrl("/api/creditos/credito/" + numeroCredito);

        ResponseEntity<CreditoDTO> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(""), CreditoDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        CreditoDTO credito = response.getBody();

        assertThat(credito).isNull();

    }
}
