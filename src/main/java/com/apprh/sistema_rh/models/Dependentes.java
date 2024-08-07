package com.apprh.sistema_rh.models;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dependentes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dependentes {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "CPF deve ser válido")
    private String cpf;

    @NotEmpty
    @Size(min = 5, max = 50, message = "O nome de usuário deve ter entre 5 e 50 caracteres")
    private String nome;

    @NotEmpty
    private String dataNascimento;
    
}
