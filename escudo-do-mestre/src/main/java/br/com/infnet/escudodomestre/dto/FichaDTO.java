package br.com.infnet.escudodomestre.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FichaDTO {
    private Long id;

    private String jogador;
    private String nome;
    private String descricao;
    private String ancestralidade;
    private String aprendiz;
    private String especialista;
    private int nivel;
    private String profissao;
    private int poder;
    private int intelecto;
    private int agilidade;
    private int forca;
    private int saude;
    private int vontade;
    private int percepcao;
    private double deslocamento;
    private int tamanho;
    private int defesa;
    private int corrupcao;
    private int insanidade;

    private Long mesaId;
}
