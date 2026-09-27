package br.com.infnet.fichamicroservice.events;

import java.time.Instant;

public record FichaExcluidaEvent(Long fichaId,
                                 String jogador,
                                 Long mesaId,
                                 Instant ocorridoEm) implements DomainEvent {
    public FichaExcluidaEvent(
            Long fichaId,
            String jogador,
            Long mesaId
    ) {
        this(
                fichaId,
                jogador,
                mesaId,
                Instant.now()
        );
    }
}
