package com.apprh.sistema_rh.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name = "candidato")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidato {

    @Id
    @GeneratedValue
    private long codigo;

    @NotEmpty
    @Column(unique = true)
    private String rg;

    @NotEmpty
    @Size(min = 5, max = 15, message = "O nome de usuário deve ter entre 5 e 15 caracteres")
    private String nomeCandidato;

    @NotEmpty
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail deve ser válido")
    private String email;

    // Muitas candidatos para uma vaga
    @ManyToOne
    private Vaga vaga;

}
