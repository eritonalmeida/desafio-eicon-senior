package br.com.eicon.desafio.controller.api;

import br.com.eicon.desafio.dto.CreditoDTO;
import br.com.eicon.desafio.entity.Credito;
import br.com.eicon.desafio.service.CreditoService;
import br.com.eicon.desafio.service.CreditosKafkaPublisher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/creditos")
@Tag(name = "Créditos")
public class CreditosController {

    @Autowired
    CreditoService creditoService;

    @Autowired
    private CreditosKafkaPublisher creditosKafkaPublisher;

    @Operation(
            summary = "Créditos por numeroNfse",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = CreditoDTO[].class)))
            }
    )
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

    @Operation(
            summary = "Créditos por numeroCredito",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = CreditoDTO.class))),
                @ApiResponse(
                        responseCode = "204",
                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = Void.class)))

            }
    )
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
