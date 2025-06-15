package br.com.eicon.desafio.service;

import br.com.eicon.desafio.entity.Credito;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CreditosKafkaPublisher {

    @Value("${topicos.creditos.request.topic}")
    private String creditosRequestTopic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void send(Credito credito) {

        try {
            String json = objectMapper.writeValueAsString(credito);

            kafkaTemplate.send(creditosRequestTopic, json);
        } catch (JsonProcessingException ex) {
            log.error("CreditosKafkaProducer.send: {}", ex.getMessage());
        }
    }
}
