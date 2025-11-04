package br.com.bruno.ultimos_campeoes.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TitulosRespondeDTO(
        String time,
        String qtdDiasSemTitulo,
        String campeonato,
        String imgUrl,
        String dataUltimaConquista,
        String anoUltimaConquista,
        String qtdTitulos
) {
}
