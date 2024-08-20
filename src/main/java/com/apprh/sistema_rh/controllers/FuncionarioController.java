package com.apprh.sistema_rh.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apprh.sistema_rh.models.Dependentes;
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

    // http://localhost:8080/dependentes/{id} - Método para listar os dependentes de um funcionário específico.
    @RequestMapping(value = "/dependentes/{id}", method = RequestMethod.GET)
    public ModelAndView dependentes(@PathVariable("id") long id){
        Funcionario funcionario = funcionarioRepository.findById(id);
        ModelAndView mv = new ModelAndView("funcionario/dependentes");
        mv.addObject("funcionarios", funcionario);

        Iterable<Dependentes> dependentes = dependentesRepository.findByFuncionario(funcionario);  // busca todos os dependentes relacionados ao funcionário fornecido.
        mv.addObject("dependentes", dependentes);
        return mv;
    }

    // http://localhost:8080/dependentes/{id} - Método é para processar a submissão de um formulário de adição de dependente.
    @RequestMapping(value = "/dependentes/{id}", method = RequestMethod.POST)
    public String dependentesPost(@PathVariable("id") long id, Dependentes dependentes, BindingResult bindingResult,
    RedirectAttributes attributes){


        if(bindingResult.hasErrors()) {                                                                     // Verifica se há erros de validação no objeto dependentes.
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");  // Adiciona uma mensagem que será exibida ao usuário após o redirecionamento.
			return "redirect:/dependentes/{id}";
		}
		if(dependentesRepository.findByCpf(dependentes.getCpf()) != null) {                                // Verifica se já existe um dependente com o mesmo CPF no banco de dados. Se existir, uma mensagem de erro é adicionada aos atributos de redirecionamento, e o usuário é redirecionado de volta.
			attributes.addFlashAttribute("mensagem_erro", "CPF duplicado");
			return "redirect:/dependentes/{id}";
		}

        Funcionario funcionario = funcionarioRepository.findById(id);
        dependentes.setFuncionario(funcionario);
        dependentesRepository.save(dependentes);
        attributes.addFlashAttribute("mensagem", "Dependente adicionado com sucesso");
		return "redirect:/dependentes/{id}";
    }

    // http://localhost:8080/deletarFuncionario - Método para deletar um funcionário por id
    @RequestMapping("/deletarFuncionario")
    public String deletarFuncionario(long id){
        Funcionario funcionario = funcionarioRepository.findById(id);
        funcionarioRepository.delete(funcionario);
        return "redirect:/funcionarios";
    }
}


/*Lembrete:
 * BindingResult bindingResult: Este objeto captura possíveis erros de validação que possam ocorrer durante o processo de binding dos dados do 
 * formulário ao objeto Dependentes.
 * 
 * RedirectAttributes attributes: Usado para adicionar atributos que serão passados em um redirecionamento, como mensagens de erro ou sucesso.
*/