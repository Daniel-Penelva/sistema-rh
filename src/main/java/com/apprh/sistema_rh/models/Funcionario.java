package com.apprh.sistema_rh.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue
    private long id;

    @NotEmpty
    @Size(min = 5, max = 50, message = "O nome de usuário deve ter entre 5 e 50 caracteres")
    private String nome;

    @NotEmpty
    private String data;

    @NotEmpty
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail deve ser válido")
    private String email;

}
