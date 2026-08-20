package br.com.infnet.fichamicroservice.repository;

import br.com.infnet.fichamicroservice.model.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FichaRepository extends JpaRepository<Ficha, Long> {
    List<Ficha> findByJogador(String jogador);

    List<Ficha> findByMesaId(Long mesaId);
}
