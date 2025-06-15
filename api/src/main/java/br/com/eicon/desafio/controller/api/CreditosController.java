package br.com.eicon.desafio.controller.api;

import br.com.eicon.desafio.dto.CreditoDTO;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/creditos")
public class CreditosController {

    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<CreditoDTO>> numeroNfse(@PathVariable("numeroNfse") String numeroNfse) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<CreditoDTO> numeroCredito(@PathVariable("numeroCredito") String numeroCredito) {

        CreditoDTO dto = new CreditoDTO();

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

}
