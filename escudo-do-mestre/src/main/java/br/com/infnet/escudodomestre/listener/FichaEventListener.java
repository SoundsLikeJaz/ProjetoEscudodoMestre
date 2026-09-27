package br.com.infnet.escudodomestre.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class FichaEventListener {

    @KafkaListener(
            topics = {
                    "${topics.ficha-criada}",
                    "${topics.ficha-excluida}"
            },
            groupId = "escudo-do-mestre"
    )
    public void consumir(ConsumerRecord<String, FichaEvent> record) {
        FichaEvent evento = record.value();

        String topico = record.topic();

        if (topico.equals("ficha-criada")) {
            System.out.println("Ficha criada: " + evento.fichaId());
        } else if (topico.equals("ficha-excluida")) {
            System.out.println("Ficha excluída: " + evento.fichaId());
        } else {
            System.out.println("Evento inesperado: " + evento.fichaId());
        }

        System.out.println("Jogador: " + evento.jogador());
        System.out.println("Mesa: " + evento.mesaId());
        System.out.println("Data: " + evento.ocorridoEm());
    }
}