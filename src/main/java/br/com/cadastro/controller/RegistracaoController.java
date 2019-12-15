package br.com.cadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastro.model.Empresa;
import br.com.cadastro.service.NotificacaoService;

@RestController
public class RegistracaoController {

	@Autowired
	private NotificacaoService notificacaoService;
	
	
	@RequestMapping("/inscreva-se")
	public String inscreverSe() {
		return "Inscreva-se em quatro serviços";
	}
	
	@RequestMapping("/inscreveu-sucesso")
	public String inscritoSucesso() {
		
		Empresa empresa= new Empresa();
		empresa.setNomeEmpresa("Community Hack!!");
		empresa.setNomeUsuario("Fulano");
		
		try {
			
			this.notificacaoService.enviarNotificacao(empresa);
			
		} catch (MailException e) {
			System.out.println(e.getMessage());
		}
		
		
		return "Obrigado por ser registrar!!";
		
	}
	

}
