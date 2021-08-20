package reboucas.nathalia.vacinajaapi.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import reboucas.nathalia.vacinajaapi.models.dto.EmailDTO;
import reboucas.nathalia.vacinajaapi.services.EmailService;

@CrossOrigin
@Api(value = "Email", description = "Endpoint de Email", tags = "Email")
@RestController
@RequestMapping("/emails")
public class EmailResource {
	
	@Autowired
	private EmailService service;
	
	@ApiOperation(value = "Enviar um email")
//	@RolesAllowed("admin") TODO: Criar admin no keycloak
	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody @Valid EmailDTO emailDTO) {
		service.enviarEmail(emailDTO.getDestinatario(), emailDTO.getAssunto(), emailDTO.getMensagem());		
		return ResponseEntity.noContent().build();
	}

}
