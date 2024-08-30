package com.apprh.sistema_rh.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apprh.sistema_rh.models.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Funcionario findById(long id);

    Funcionario findByNome(String nome);

    // Método para buscar funcionários
    @Query(value = "SELECT u FROM Funcionario u WHERE u.nomeFuncionario LIKE %?1%")
    List<Funcionario> findByNomeFuncionarios(String nomeFuncionario);
}
