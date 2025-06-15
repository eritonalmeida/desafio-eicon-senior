package br.com.eicon.desafio;

import br.com.eicon.desafio.controller.api.CreditosController;
import br.com.eicon.desafio.repository.CreditoRepository;
import br.com.eicon.desafio.service.CreditoService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DesafioEiconApplicationTest {

    @Autowired
    private CreditoRepository repository;

    @Autowired
    private CreditoService creditoService;

    @Autowired
    private CreditosController creditosController;

    @Test
    void contextLoads() {
        assertThat(repository).isNotNull();

        assertThat(creditoService).isNotNull();

        assertThat(creditosController).isNotNull();
    }

}
