package reboucas.nathalia.vacinajaapi.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import reboucas.nathalia.vacinajaapi.models.entities.Usuario;
import reboucas.nathalia.vacinajaapi.services.EmailService;
import reboucas.nathalia.vacinajaapi.services.UsuarioService;

@Component
@EnableScheduling
public class AgendamentoTask {
 
    private static final String ZONE_BR = "America/Sao_Paulo";
    private static final int QTD_USUARIOS_NOTIFICAR = 2;
    private static final String ASSUNTO_EMAIL = "Agendamento vacinação";
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private EmailService emailService;
 
   @Scheduled(cron = "0 30 19 * * *", zone = ZONE_BR) // Cron = todos os dias as 19:30
   public void agendarParaDiaSeguinte() {
	   final List<Usuario> usuariosNotificar = usuarioService.getUsuariosNotificar(QTD_USUARIOS_NOTIFICAR);
	   
	   if (Objects.nonNull(usuariosNotificar) && !usuariosNotificar.isEmpty()) {
		   final LocalDate dataAgendamento = LocalDate.now().plusDays(1);
		   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		   String dataAgendamentoFormatado = dataAgendamento.format(formatter);
		   
		   final StringBuilder mensagem = new StringBuilder("Parabéns! Seu agendamento está marcado para o dia "
		   		+ dataAgendamentoFormatado + ". Dirija-se ao posto de vacinação mais próximo de sua residência.");
		   
		   final List<String> emailsDestinatarios = usuariosNotificar.stream()
				   .map(Usuario::getEmail)
				   .collect(Collectors.toList());
		   
		   emailService.enviarEmail(emailsDestinatarios, ASSUNTO_EMAIL, mensagem.toString());
		   usuarioService.atualizarUsuariosProcessados(usuariosNotificar);
	   }
   }
   
}