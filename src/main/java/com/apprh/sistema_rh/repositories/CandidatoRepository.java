package com.apprh.sistema_rh.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apprh.sistema_rh.models.Candidato;
import com.apprh.sistema_rh.models.Vaga;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    Iterable<Candidato> findByVaga(Vaga vaga);  // coleção que pode ter seus elementos alcançados por uma estrutura foreach

    Candidato findByRg(String rg);

    Candidato findById(long id);

    List<Candidato> findByNomeCandidato(String nomeCandidato);

}
