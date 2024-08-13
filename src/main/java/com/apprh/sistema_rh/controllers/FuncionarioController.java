package com.apprh.sistema_rh.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apprh.sistema_rh.models.Funcionario;
import com.apprh.sistema_rh.repositories.DependentesRepository;
import com.apprh.sistema_rh.repositories.FuncionarioRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FuncionarioController {
    
    private final FuncionarioRepository funcionarioRepository;
    private final DependentesRepository dependentesRepository;

    // http://localhost:8080/cadastrarFuncionario - Chama o form de casdatrar funcionários
    @RequestMapping(value = "/cadastrarFuncionario", method = RequestMethod.GET)
    public String form(){
        return "funcionario/formFuncionario";
    }

    // Método para cadastrar o funcionario
    @RequestMapping(value = "/cadastrarFuncionario", method = RequestMethod.POST)
    public String form(@Valid Funcionario funcionario, BindingResult bindingResult, RedirectAttributes attributes ){

        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrarFuncionario";
        }

        funcionarioRepository.save(funcionario);
        attributes.addFlashAttribute("mensagem", "Funcionário cadastrado com sucesso!");
		return "redirect:/cadastrarFuncionario";
    }

    // http://localhost:8080/funcionarios - Método para listar funcionário
    @RequestMapping("/funcionarios")
    public ModelAndView listaFuncionarios(){
        ModelAndView mv = new ModelAndView("funcionario/listaFuncionario");
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        mv.addObject("funcionarios", funcionarios);
        return mv;
    }
}
