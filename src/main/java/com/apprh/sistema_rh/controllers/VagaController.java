package com.apprh.sistema_rh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apprh.sistema_rh.repositories.CandidatoRepository;
import com.apprh.sistema_rh.repositories.VagaRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class VagaController {

    private final VagaRepository vagaRepository;
    private final CandidatoRepository candidatoRepository;

    // Esse método corresponde ao nome da página de visualização
    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.GET)
    public String form() {
        return "vaga/formVaga";
    }

}
