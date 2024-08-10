package com.apprh.sistema_rh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apprh.sistema_rh.repositories.DependentesRepository;
import com.apprh.sistema_rh.repositories.FuncionarioRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FuncionarioController {
    
    private final FuncionarioRepository funcionarioRepository;
    private final DependentesRepository dependentesRepository;

    // Chama o form de casdatrar funcionários
    @RequestMapping(value = "/cadastrarFuncionario", method = RequestMethod.GET)
    public String form(){
        return "funcionario/formFuncionario";
    }
}
