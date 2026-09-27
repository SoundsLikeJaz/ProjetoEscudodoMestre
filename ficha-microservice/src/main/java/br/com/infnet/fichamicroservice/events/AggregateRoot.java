package br.com.infnet.fichamicroservice.events;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot {

    private final List<DomainEvent> eventos = new ArrayList<>();

    protected void registrarEvento(DomainEvent evento) {
        eventos.add(evento);
    }

    public List<DomainEvent> eventos() {
        return List.copyOf(eventos);
    }

    public void limparEventos() {
        eventos.clear();
    }
}
