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

    // http://localhost:8080/cadastrarVaga
    // Esse método cadastra vaga
    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.POST)
    public String form(@Valid Vaga vaga, BindingResult bindingResult, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {                                                                   // BindingResult bindingResult - este objeto é usado para verificar se houve erros de validação durante o mapeamento do formulário para o objeto Vaga.
            attributes.addFlashAttribute("mensagem", "Verifique os campos");  // RedirectAttributes attributes - é usado para adicionar atributos de redirecionamento (flash attributes) que persistem apenas durante o redirecionamento.
            return "redirect:/cadastrarVaga";
        }

        vagaRepository.save(vaga);
        attributes.addFlashAttribute("mensagem", "Vaga cadastrada com sucesso");
        return "redirect:/cadastrarVaga";

    }

    // http://localhost:8080/listaVaga
    // Esse método lista vagas
    @RequestMapping("/vagas")
    public ModelAndView listaVagas() {
        ModelAndView mv = new ModelAndView("vaga/listaVaga");
        Iterable<Vaga> vagas = vagaRepository.findAll();
        mv.addObject("vagas", vagas);    // Adiciona o objeto vagas ao modelo com a chave "vagas". Isso significa que na view (listaVaga) poderá acessar os dados das vagas usando essa chave.
        return mv;                                     // Retorna o ModelAndView que será usado pelo Spring MVC para renderizar a resposta.
    }

    // http://localhost:8080/detalhesVaga
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

    // Esse método deleta vaga do repositório com base em seu código.
    @RequestMapping("/deletarVagas")
    public String deletarVagas(long codigo) {
        Vaga vaga = vagaRepository.findByCodigo(codigo);   // busca uma vaga específica pelo código fornecido.
        vagaRepository.delete(vaga);                       // deleta a vaga encontrada do repositório.
        return "redirect:/vagas";                          // redirecionar o navegador para a lista de vagas, URL /vagas.
    }

    
    // Este método adiciona um candidato a uma vaga específica, com base no código da vaga.
	@RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
	public String detalhesVagaPost(@PathVariable("codigo") long codigo, @Valid Candidato candidato,
			BindingResult bindingResult, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {                                                                   // Verifica se houve erros - o bindingResult contém o resultado da validação do objeto candidato.                                                            
            attributes.addFlashAttribute("mensagem", "Verifique os campos");  // RedirectAttributes attributes - adiciona uma mensagem de erro aos atributos de redirecionamento.
            return "redirect:/{codigo}";                                                                   // Redireciona o usuário de volta para a página da vaga, mantendo o código na URL.
        }

        if(candidatoRepository.findByRg(candidato.getRg()) != null){                                       // Verifica se já existe um candidato com o mesmo RG no repositório.
            attributes.addFlashAttribute("mensagem_erro", "RG duplicado");    // Adiciona uma mensagem de erro aos atributos de redirecionamento se um candidato com o mesmo RG for encontrado.
			return "redirect:/{codigo}";                                                                   // Redireciona o usuário de volta para a página da vaga se um RG duplicado for encontrado.
        }

        Vaga vaga = vagaRepository.findByCodigo(codigo);                                                   // Busca a vaga pelo código fornecido.
        candidato.setVaga(vaga);                                                                           // Associa a vaga ao candidato.
        candidatoRepository.save(candidato);
        attributes.addFlashAttribute("mensagem", "Candidato adcionado com sucesso!");
		return "redirect:/{codigo}";                                                                       // Redireciona o usuário de volta para a página da vaga após o candidato ser adicionado com sucesso.
    }

    // Este método deleta o candidato pelo RG
	@RequestMapping("/deletarCandidato")
    public String deletarCandidato(String rg) { 
        
        Candidato candidato = candidatoRepository.findByRg(rg);       // busca um candidato pelo RG fornecido.
        Vaga vaga = candidato.getVaga();                              // obtém a vaga associada ao candidato encontrado.
        String codigo = "" + vaga.getCodigo();                        // concatena uma string vazia com o código da vaga para convertê-lo em uma string. Isso é feito para preparar o código da vaga para ser usado na URL de redirecionamento.
        candidatoRepository.delete(candidato);                        // deleta o candidato do repositório.
        return "redirect:/" + codigo;                                 // redireciona o usuário para a URL da vaga após a deleção do candidato. A URL de redirecionamento será / seguida pelo código da vaga.
    }

    // http://localhost:8080/atualizarVaga
    // Este método exibe o formulário de edição de uma vaga específica, com base no código.
    @RequestMapping(value = "/editar-vaga", method = RequestMethod.GET)
	public ModelAndView editarVaga(long codigo) {
        
        Vaga vaga = vagaRepository.findByCodigo(codigo);                    // busca uma vaga específica pelo código fornecido.
        ModelAndView mv = new ModelAndView("vaga/atualizarVaga");  // cria um novo objeto ModelAndView, especificando o nome da vista (view) que deve ser renderizada, neste caso, "vaga/atualizarVaga".
        mv.addObject("vaga", vaga);                           // adiciona a vaga encontrada como um atributo do modelo com o nome "vaga". Isso permite que a view acesse e exiba os detalhes da vaga para edição.
        return mv;
    }

    // Este método é responsável por lidar com requisições HTTP POST para atualizar os detalhes de uma vaga específica.
    @RequestMapping(value = "/editar-vaga", method = RequestMethod.POST)
	public String updateVaga(@Valid Vaga vaga, BindingResult binding, RedirectAttributes attributes) { 
        
        vagaRepository.save(vaga);                                                                           // Salva a vaga atualizada no repositório. Se a vaga já existir, ela será atualizada; caso contrário, uma nova entrada será criada.
        attributes.addFlashAttribute("success", "Vaga editada com sucesso!");   // Adiciona uma mensagem de sucesso aos atributos de redirecionamento.

        /*Obtém o código da vaga atualizada e o converte para uma string. Isso é feito para preparar o código para ser usado na URL de redirecionamento.*/
        long codigoLong = vaga.getCodigo();
        String codigo = "" + codigoLong;
        
        return "redirect:/" + codigo;                                                                        // Redireciona o usuário para a página da vaga usando o código da vaga. 
    }

}