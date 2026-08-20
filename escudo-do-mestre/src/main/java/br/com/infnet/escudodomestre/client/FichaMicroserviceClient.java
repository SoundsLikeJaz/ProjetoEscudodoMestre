package br.com.infnet.escudodomestre.client;

import br.com.infnet.escudodomestre.dto.FichaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ficha-microservice")
public interface FichaMicroserviceClient {
    @GetMapping("/fichas/mesa/{mesaId}")
    List<FichaDTO> listarPorMesa(@PathVariable Long mesaId);
}
