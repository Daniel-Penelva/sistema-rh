package com.apprh.sistema_rh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.apprh.sistema_rh.repositories.CandidatoRepository;
import com.apprh.sistema_rh.repositories.DependentesRepository;
import com.apprh.sistema_rh.repositories.FuncionarioRepository;
import com.apprh.sistema_rh.repositories.VagaRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BuscaController {

    private final VagaRepository vagaRepository;
    private final CandidatoRepository candidatoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final DependentesRepository dependentesRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView abrirIndex() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView buscarIndex(@RequestParam("buscar") String buscar, @RequestParam("nome") String nome) {

        ModelAndView mv = new ModelAndView("index");
        String mensagem = "Resultados da busca por " + buscar;

        if (nome.equals("nomefuncionario")) {
            mv.addObject("funcionarios", funcionarioRepository.findByNome(buscar));
        } else if (nome.equals("nomedependente")) {
            mv.addObject("dependentes", dependentesRepository.findByNome(buscar));
        } else if (nome.equals("nomecandidato")) {
            mv.addObject("candidatos", candidatoRepository.findByNomeCandidato(buscar));
        } else if (nome.equals("titulovaga")) {
            mv.addObject("vagas", vagaRepository.findByNome(nome));
        } else {
            mv.addObject("funcionarios", funcionarioRepository.findByNome(buscar));
            mv.addObject("dependentes", dependentesRepository.findByNome(buscar));
            mv.addObject("candidatos", candidatoRepository.findByNomeCandidato(buscar));
            mv.addObject("vagas", vagaRepository.findByNome(nome));
        }

        mv.addObject("mensagem", mensagem);
        return mv;
    }

}
