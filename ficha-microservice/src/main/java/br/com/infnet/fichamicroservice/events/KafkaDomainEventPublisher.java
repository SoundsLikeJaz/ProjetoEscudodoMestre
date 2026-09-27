package br.com.infnet.fichamicroservice.events;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class KafkaDomainEventPublisher {
    private final KafkaTemplate<String,Object> kafkaTemplate;

    @Value("${topics.ficha-criada}")
    private String TOPIC_FICHA_CRIADA;

    @Value("${topics.ficha-excluida}")
    private String TOPIC_FICHA_EXCLUIDA;

    public KafkaDomainEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publicar(Collection<DomainEvent> eventos) {
        eventos.forEach(this::enviar);
    }

    private void enviar(DomainEvent evento) {
        if(evento instanceof FichaCriadaEvent) {
            kafkaTemplate.send(
                    TOPIC_FICHA_CRIADA,
                    evento.fichaId().toString(),
                    evento
            );
        }

        if(evento instanceof FichaExcluidaEvent) {
            kafkaTemplate.send(
                    TOPIC_FICHA_EXCLUIDA,
                    evento.fichaId().toString(),
                    evento
            );
        }
    }
}
