package br.com.infnet.escudodomestre.listener;

import java.time.Instant;

public record FichaEvent(
        Long fichaId,
        String jogador,
        Long mesaId,
        Instant ocorridoEm
) {
}
