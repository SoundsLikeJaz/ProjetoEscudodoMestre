package br.com.infnet.escudodomestre.repositories;

import br.com.infnet.escudodomestre.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
    List<Mesa> findByMestre(String mestre);
}
