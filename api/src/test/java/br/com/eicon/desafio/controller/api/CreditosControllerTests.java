package br.com.eicon.desafio.controller.api;

import br.com.eicon.desafio.dto.CreditoDTO;
import br.com.eicon.desafio.entity.Credito;
import br.com.eicon.desafio.service.CreditoService;
import br.com.eicon.desafio.service.CreditosKafkaPublisher;
import br.com.eicon.desafio.helper.TestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CreditosController.class)
public class CreditosControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    CreditoService service;

    @MockitoBean
    CreditosKafkaPublisher CreditosKafkaPublisher;

    @Autowired
    private ObjectMapper objectMapper;

    private Credito credito = new Credito();

    private CreditoDTO dto = new CreditoDTO();

    String dataConstituicao;

    @BeforeEach
    void setUp() {
        credito = TestHelper.getCredito();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        dataConstituicao = LocalDate.of(2024, Month.FEBRUARY, 25).format(formatter);
    }

    @Test
    public void whenGetByNumeroNfse() throws Exception {
        Mockito.when(service.findByNumeroNfse(anyString()))
                .thenReturn(List.of(credito));

        String numeroNfse = "123456";

        mockMvc.perform(get("/api/creditos/" + numeroNfse)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].numeroCredito", is("7891011")))
                .andExpect(jsonPath("$[0].numeroNfse", is(numeroNfse)))
                .andExpect(jsonPath("$[0].dataConstituicao", is(dataConstituicao)))
                .andExpect(jsonPath("$[0].valorIssqn", is(1500.75)))
                .andExpect(jsonPath("$[0].tipoCredito", is("ISSQN")))
                .andExpect(jsonPath("$[0].simplesNacional", is("Sim")))
                .andExpect(jsonPath("$[0].aliquota", is(5.0)))
                .andExpect(jsonPath("$[0].valorFaturado", is(30000.00)))
                .andExpect(jsonPath("$[0].valorDeducao", is(5000.00)))
                .andExpect(jsonPath("$[0].baseCalculo", is(25000.00)))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

    @Test
    public void whenGetByNumeroCredito() throws Exception {
        Mockito.when(service.findByNumeroCredito(anyString()))
                .thenReturn(credito);

        String numeroCredito = "7891011";

        mockMvc.perform(get("/api/creditos/credito/" + numeroCredito)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.numeroCredito", is(numeroCredito)))
                .andExpect(jsonPath("$.numeroNfse", is("123456")))
                .andExpect(jsonPath("$.dataConstituicao", is(dataConstituicao)))
                .andExpect(jsonPath("$.valorIssqn", is(1500.75)))
                .andExpect(jsonPath("$.tipoCredito", is("ISSQN")))
                .andExpect(jsonPath("$.simplesNacional", is("Sim")))
                .andExpect(jsonPath("$.aliquota", is(5.0)))
                .andExpect(jsonPath("$.valorFaturado", is(30000.00)))
                .andExpect(jsonPath("$.valorDeducao", is(5000.00)))
                .andExpect(jsonPath("$.baseCalculo", is(25000.00)))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

    @Test
    public void whenGetByNumeroCreditoNotFound() throws Exception {
        Mockito.when(service.findByNumeroCredito(anyString()))
                .thenReturn(null);

        String numeroCredito = "654321";

        mockMvc.perform(get("/api/creditos/credito/" + numeroCredito)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn().getResponse();
    }
}
