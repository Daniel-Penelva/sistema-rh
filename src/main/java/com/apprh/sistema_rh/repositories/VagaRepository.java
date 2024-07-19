package com.apprh.sistema_rh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apprh.sistema_rh.models.Vaga;
import java.util.List;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

    Vaga findByCodigo(long codigo);

    List<Vaga> findByNome(String nome);

}
