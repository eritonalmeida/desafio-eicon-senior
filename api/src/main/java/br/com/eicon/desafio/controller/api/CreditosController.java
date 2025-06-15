package br.com.eicon.desafio.controller.api;

import br.com.eicon.desafio.dto.CreditoDTO;
import br.com.eicon.desafio.entity.Credito;
import br.com.eicon.desafio.service.CreditoService;
import br.com.eicon.desafio.service.CreditosKafkaPublisher;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/creditos")
public class CreditosController {

    @Autowired
    CreditoService creditoService;

    @Autowired
    private CreditosKafkaPublisher creditosKafkaPublisher;

    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<CreditoDTO>> numeroNfse(@PathVariable("numeroNfse") String numeroNfse) {

        List<Credito> list = creditoService.findByNumeroNfse(numeroNfse);

        for (Credito credito : list) {
            creditosKafkaPublisher.send(credito);
        }

        List<CreditoDTO> listDto = list.stream()
                .map(credito -> new CreditoDTO(credito))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(listDto);
    }

    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<CreditoDTO> numeroCredito(@PathVariable("numeroCredito") String numeroCredito) {

        Credito credito = creditoService.findByNumeroCredito(numeroCredito);

        if (credito == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        CreditoDTO dto = new CreditoDTO(credito);

        creditosKafkaPublisher.send(credito);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

}
