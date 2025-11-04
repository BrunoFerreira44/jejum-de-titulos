package br.com.bruno.ultimos_campeoes.repository;

import br.com.bruno.ultimos_campeoes.domain.Titulos;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TitulosRepository extends JpaRepository<Titulos, Long> {

    @Query(
            value = """
        WITH ranked_titulos AS (
            SELECT
                t.*,
                ROW_NUMBER() OVER (
                    PARTITION BY t.nome_time
                    ORDER BY t.data_ultima_conquista DESC
                ) AS rn
            FROM titulos t
        )
        SELECT *
        FROM ranked_titulos
        WHERE rn = 1
        ORDER BY data_ultima_conquista DESC
        """,
            nativeQuery = true
    )

    List<Titulos> findUltimoTitulo();

    @Query(
            value = """
        WITH ranked_titulos AS (
            SELECT
                t.*,
                ROW_NUMBER() OVER (
                    PARTITION BY t.nome_time
                    ORDER BY t.data_ultima_conquista DESC
                ) AS rn
            FROM titulos t
            WHERE t.campeonato <> 'Estadual'
        )
        SELECT *
        FROM ranked_titulos
        WHERE rn = 1
        ORDER BY data_ultima_conquista DESC;
        """,
            nativeQuery = true
    )
    List<Titulos> findUltimoTituloExcluindoEstadual();

    @Query(
            value = """
        WITH ranked_titulos AS (
            SELECT
                t.*,
                ROW_NUMBER() OVER (
                    PARTITION BY t.nome_time
                    ORDER BY t.data_ultima_conquista DESC
                ) AS rn
            FROM titulos t
            WHERE t.campeonato = :campeonato
        )
        SELECT *
        FROM ranked_titulos
        WHERE rn = 1
        ORDER BY data_ultima_conquista DESC;
        """,
            nativeQuery = true
    )
    List<Titulos> findUltimoTituloPorCampeonato(String campeonato);
}
