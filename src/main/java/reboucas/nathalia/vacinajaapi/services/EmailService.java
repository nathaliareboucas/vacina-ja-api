package reboucas.nathalia.vacinajaapi.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	private static String REMETENTE = "noreplyvacinaja@gmail.com";
	
	public void enviarEmail(final String destinatario, 
			final String assunto, final String mensagem) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(REMETENTE);
		message.setTo(destinatario);
		message.setSubject(assunto);
		message.setText(mensagem);
		
		mailSender.send(message);
	}
	
	public void enviarEmail(final List<String> destinatarios,
			final String assunto, final String mensagem) {
		if (Objects.nonNull(destinatarios) && !destinatarios.isEmpty()) {
			destinatarios.forEach(destinatario -> enviarEmail(destinatario, assunto, mensagem));
		}
	}

}
