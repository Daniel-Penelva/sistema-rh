package com.apprh.sistema_rh.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apprh.sistema_rh.models.Candidato;
import com.apprh.sistema_rh.models.Vaga;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    Iterable<Candidato> findByVaga(Vaga vaga);  // coleção que pode ter seus elementos alcançados por uma estrutura foreach

    Candidato findByRg(String rg);

    Candidato findById(long id);
    
    // Método para buscar candidatos
    @Query(value = "SELECT u FROM Candidato u WHERE u.nomeCandidato LIKE %?1%")
    List<Candidato> findByNomeCandidatos(String nomeCandidato);

}
