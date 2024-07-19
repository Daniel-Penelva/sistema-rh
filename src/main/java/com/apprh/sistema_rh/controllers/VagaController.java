package com.apprh.sistema_rh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

}
