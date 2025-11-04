package br.com.bruno.ultimos_campeoes.service;

import br.com.bruno.ultimos_campeoes.domain.Titulos;
import br.com.bruno.ultimos_campeoes.domain.dto.TitulosRespondeDTO;
import br.com.bruno.ultimos_campeoes.repository.TitulosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TitulosService {

    private final TitulosRepository repository;

    public List<TitulosRespondeDTO> getQtdDiasSemTitulo () {
        List<Titulos> ultimoTituloDeCadaTime = repository.findUltimoTitulo();
        return mapToDTOList(ultimoTituloDeCadaTime);
    }

    public List<TitulosRespondeDTO> getQtdDiasSemTituloExluindoEstadual () {
        List<Titulos> ultimoTituloDeCadaTimeExcluindoEstadual = repository.findUltimoTituloExcluindoEstadual();
        return mapToDTOList(ultimoTituloDeCadaTimeExcluindoEstadual);
    }

    public List<TitulosRespondeDTO> getQtdDiasSemTituloPorCampeonato (String campeonato) {
        List<Titulos> ultimoTituloDeCadaTimePorCampeonato = repository.findUltimoTituloPorCampeonato(campeonato);
        return mapToDTOList(ultimoTituloDeCadaTimePorCampeonato);
    }

    private List<TitulosRespondeDTO> mapToDTOList(List<Titulos> times) {

        List<TitulosRespondeDTO> dtosResponse = new ArrayList<>();
        LocalDate diaAtual = LocalDate.now();

        times.forEach(time -> {
            Long qtdDias = ChronoUnit.DAYS.between(time.getDataUltimaConquista(), diaAtual);
            dtosResponse.add(new TitulosRespondeDTO(
                    time.getNomeTime(),
                    qtdDias.toString(),
                    time.getCampeonato(),
                    time.getImgUrl(),
                    time.getDataUltimaConquista().toString(),
                    String.valueOf(time.getDataUltimaConquista().getYear()),
                    time.getQuantidadeDeTitulos().toString()));
        });

        return dtosResponse;
    }
}
