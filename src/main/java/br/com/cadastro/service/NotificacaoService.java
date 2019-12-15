package br.com.cadastro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.cadastro.model.Empresa;

@Service
public class NotificacaoService {

	private JavaMailSender javaMailSender;
	
	@Autowired
	public NotificacaoService(JavaMailSender javaMailSender) {
		this.javaMailSender=javaMailSender;
	}
	
	public void enviarNotificacao(Empresa empresa) {

		SimpleMailMessage mail= new SimpleMailMessage();
		empresa.setEmail("leonardo_santos_bispo@hotmail.com");
		mail.setTo(empresa.getEmail());
		mail.setFrom("leonardosantosbispo@gmail.com");
		mail.setSubject("Obrigado pela parceria!!");
		mail.setText("Email de notificação");
		
		javaMailSender.send(mail);
		
		
	}
	
	
	
}
