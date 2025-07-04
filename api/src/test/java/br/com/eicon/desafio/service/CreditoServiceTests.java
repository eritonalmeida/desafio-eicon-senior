package br.com.eicon.desafio.service;

import br.com.eicon.desafio.entity.Credito;
import br.com.eicon.desafio.repository.CreditoRepository;
import br.com.eicon.desafio.helper.TestHelper;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreditoServiceTests {

    @Mock
    private CreditoRepository repository;

    @InjectMocks
    CreditoService service;

    private Credito credito = new Credito();

    @BeforeEach
    void setUp() {
        credito = TestHelper.getCredito();
    }

    @Test
    void whenFindByNumeroNfseOk() {
        Mockito.when(this.repository.findByNumeroNfse(anyString()))
                .thenReturn(List.of(credito));

        List<Credito> creditos = service.findByNumeroNfse("456");

        assertThat(creditos.size()).isEqualTo(1);

        Credito mockCredito = creditos.get(0);

        assertThat(mockCredito.getNumeroCredito()).isEqualTo(credito.getNumeroCredito());

        assertThat(mockCredito.getNumeroNfse()).isEqualTo(credito.getNumeroNfse());

        assertThat(mockCredito.getDataConstituicao()).isEqualTo(credito.getDataConstituicao());

        assertThat(mockCredito.getValorIssqn()).isEqualTo(credito.getValorIssqn());

        assertThat(mockCredito.getTipoCredito()).isEqualTo(credito.getTipoCredito());

        assertThat(mockCredito.isSimplesNacional()).isEqualTo(credito.isSimplesNacional());

        assertThat(mockCredito.getAliquota()).isEqualTo(credito.getAliquota());

        assertThat(mockCredito.getValorFaturado()).isEqualTo(credito.getValorFaturado());

        assertThat(mockCredito.getValorDeducao()).isEqualTo(credito.getValorDeducao());

        assertThat(mockCredito.getBaseCalculo()).isEqualTo(credito.getBaseCalculo());
    }

    @Test
    void wheFindByNumeroCreditoOk() {
        Mockito.when(this.repository.findByNumeroCredito(anyString()))
                .thenReturn(Optional.of(credito));

        Credito mockCredito = service.findByNumeroCredito("123");

        assertThat(mockCredito.getNumeroCredito()).isEqualTo(credito.getNumeroCredito());

        assertThat(mockCredito.getNumeroNfse()).isEqualTo(credito.getNumeroNfse());

        assertThat(mockCredito.getDataConstituicao()).isEqualTo(credito.getDataConstituicao());

        assertThat(mockCredito.getValorIssqn()).isEqualTo(credito.getValorIssqn());

        assertThat(mockCredito.getTipoCredito()).isEqualTo(credito.getTipoCredito());

        assertThat(mockCredito.isSimplesNacional()).isEqualTo(credito.isSimplesNacional());

        assertThat(mockCredito.getAliquota()).isEqualTo(credito.getAliquota());

        assertThat(mockCredito.getValorFaturado()).isEqualTo(credito.getValorFaturado());

        assertThat(mockCredito.getValorDeducao()).isEqualTo(credito.getValorDeducao());

        assertThat(mockCredito.getBaseCalculo()).isEqualTo(credito.getBaseCalculo());
    }
}
