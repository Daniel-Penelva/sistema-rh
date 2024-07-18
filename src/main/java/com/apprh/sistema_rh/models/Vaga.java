package com.apprh.sistema_rh.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vaga")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vaga implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigo;

    @NotEmpty            // Garante que o valor do campo não seja null e que, se for uma string ou uma coleção, não seja vazio.
    @Size(min = 5, max = 15, message = "O nome de usuário deve ter entre 5 e 15 caracteres")
    private String nome;

    @NotEmpty
    @Size(max = 100, message = "Número máximo é de 100 caracteres")
    private String descricao;

    @NotEmpty
    private String data;

    @NotEmpty
    private String salario;

    // Uma Vaga para muitos candidatos
    @OneToMany(mappedBy = "vaga", cascade = CascadeType.REMOVE)
    private List<Candidato> candidatos = new ArrayList<>();
    
}
