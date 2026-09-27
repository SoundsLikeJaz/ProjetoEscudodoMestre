package br.com.infnet.fichamicroservice.events;

import java.time.Instant;

public interface DomainEvent {
    Instant ocorridoEm();
    Long fichaId();
    String jogador();
    Long mesaId();
}
