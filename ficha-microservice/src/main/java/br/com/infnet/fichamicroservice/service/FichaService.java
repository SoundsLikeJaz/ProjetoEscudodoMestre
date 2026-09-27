package br.com.infnet.fichamicroservice.service;

import br.com.infnet.fichamicroservice.events.AggregateRoot;
import br.com.infnet.fichamicroservice.events.FichaCriadaEvent;
import br.com.infnet.fichamicroservice.events.FichaExcluidaEvent;
import br.com.infnet.fichamicroservice.events.KafkaDomainEventPublisher;
import br.com.infnet.fichamicroservice.model.Ficha;
import br.com.infnet.fichamicroservice.repository.FichaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FichaService extends AggregateRoot {

    private final FichaRepository fichaRepository;
    private final KafkaDomainEventPublisher kafkaDomainEventPublisher;

    public FichaService(FichaRepository fichaRepository,  KafkaDomainEventPublisher kafkaDomainEventPublisher) {
        this.fichaRepository = fichaRepository;
        this.kafkaDomainEventPublisher = kafkaDomainEventPublisher;
    }

    public Ficha cadastrar(Ficha ficha) {
        Ficha fichaCriada = this.fichaRepository.save(ficha);

        registrarEvento(new FichaCriadaEvent(
                ficha.getId(),
                ficha.getJogador(),
                ficha.getMesaId()
        ));

        finalizarEvento();

        return fichaCriada;
    }

    public List<Ficha> listarPorJogador(String jogador) {
        return this.fichaRepository.findByJogador(jogador);
    }

    public List<Ficha> listarPorMesa(Long mesaId) {
        return this.fichaRepository.findByMesaId(mesaId);
    }

    public Ficha buscarPorId(Long id) {
        return this.fichaRepository.findById(id)
                .orElseThrow(this::notFound);
    }

    public Ficha atualizar(Long id, Ficha ficha) {
        this.buscarPorId(id);

        return this.fichaRepository.save(ficha);
    }

    public void remover(Long id) {
        Ficha ficha = this.buscarPorId(id);

        registrarEvento(new FichaExcluidaEvent(
                ficha.getId(),
                ficha.getJogador(),
                ficha.getMesaId()
        ));

        finalizarEvento();

        this.fichaRepository.deleteById(id);
    }

    private ResponseStatusException notFound() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado");
    }

    private void finalizarEvento() {
        kafkaDomainEventPublisher.publicar(this.eventos());
        this.limparEventos();
    }
}
