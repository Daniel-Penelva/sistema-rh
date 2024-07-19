package com.apprh.sistema_rh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apprh.sistema_rh.models.Candidato;
import com.apprh.sistema_rh.models.Vaga;
import com.apprh.sistema_rh.repositories.CandidatoRepository;
import com.apprh.sistema_rh.repositories.VagaRepository;

import jakarta.validation.Valid;
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

    // Esse método cadastra vaga
    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.POST)
    public String form(@Valid Vaga vaga, BindingResult bindingResult, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos");
            return "redirect:/cadastrarVaga";
        }

        vagaRepository.save(vaga);
        attributes.addFlashAttribute("mensagem", "Vaga cadastrada com sucesso");
        return "redirect:/cadastrarVaga";

    }

    // Esse método lista vagas
    @RequestMapping("/vagas")
    public ModelAndView listaVagas() {
        ModelAndView mv = new ModelAndView("vaga/listaVaga");
        Iterable<Vaga> vagas = vagaRepository.findAll();
        mv.addObject("vagas", vagas);    // Adiciona o objeto vagas ao modelo com a chave "vagas". Isso significa que na view (listaVaga) poderá acessar os dados das vagas usando essa chave.
        return mv;                                     // Retorna o ModelAndView que será usado pelo Spring MVC para renderizar a resposta.
    }

    // Esse método busca pelo código detalhes da vaga e dos candidatos
    @RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
    public ModelAndView detalhesVaga(@PathVariable("codigo") long codigo){
        Vaga vaga = vagaRepository.findByCodigo(codigo);                        // busca uma vaga pelo seu código.
        
        ModelAndView mv = new ModelAndView("vaga/detalhesVaga");       // cria um ModelAndView com a view vaga/detalhesVaga, aqui o Spring MVC vai procurar um template com esse nome para renderizar a resposta.
		mv.addObject("vaga", vaga);                               // Adiciona a vaga encontrada ao modelo com a chave "vaga". Isso permite que os dados da vaga sejam acessados na view.

        Iterable<Candidato> candidatos = candidatoRepository.findByVaga(vaga);  // busca candidatos associados à vaga específica
        mv.addObject("candidatos", candidatos);                   // Adiciona os candidatos encontrados ao modelo com a chave "candidatos". Isso permite que os dados dos candidatos sejam acessados na view.

		return mv;
    }

}
