package com.vicente.controleponto.api.configs;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class SmtpEnvioEmailService implements EnvioEmailService {

	@Autowired JavaMailSender javaMailSender;
	
	@Autowired EmailProperties emailProperties;
	
	@Autowired Configuration freemarkerConfiguration;
	
	@Override
	public void enviar(Mensagem mensagem) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			
			String corpo = processarTemplate(mensagem);
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom(emailProperties.getRemetente());
			helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
			helper.setSubject(mensagem.getAssunto());
			helper.setText(corpo, true);
			
			
			// linhas abaixo apenas para testes de envio de email com anexo
			String filePath = "C:/Users/vicen/OneDrive/Imagens/memes/EMVfkdLW4AAsF1D.jpg";
			FileSystemResource file = new FileSystemResource(new File(filePath).getCanonicalPath());
			helper.addAttachment("ibage.jpg", file);
			
			
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private String processarTemplate(Mensagem mensagem) {
		try {
			Template template = freemarkerConfiguration.getTemplate(mensagem.getCorpo());
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
