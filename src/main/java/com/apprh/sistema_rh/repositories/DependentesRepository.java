package com.apprh.sistema_rh.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apprh.sistema_rh.models.Dependentes;
import com.apprh.sistema_rh.models.Funcionario;

@Repository
public interface DependentesRepository extends JpaRepository<Dependentes, Long> {

    Iterable<Dependentes> findByFuncionario(Funcionario funcionario);

    Dependentes findByCpf(String cpf);

    Dependentes findById(long id);

    List<Dependentes> findByNome(String nome);

    // Método para buscar dependentes
    @Query(value = "SELECT u FROM Dependentes u WHERE u.nome LIKE %?1%")
    List<Dependentes> findByNomeDependentes(String nome);  
}
