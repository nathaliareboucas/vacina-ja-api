package reboucas.nathalia.vacinajaapi.resources;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reboucas.nathalia.vacinajaapi.models.dto.DashboardDTO;
import reboucas.nathalia.vacinajaapi.models.dto.UsuarioDTO;
import reboucas.nathalia.vacinajaapi.models.entities.Usuario;
import reboucas.nathalia.vacinajaapi.services.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@RolesAllowed("user")
	@PostMapping
	public ResponseEntity<UsuarioDTO> salvar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
		final Usuario usuarioCriado = service.salvar(usuarioDTO.toEntity());
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado.toDTO());
	}
	
	@RolesAllowed("user")
	@GetMapping(value = "/{id}/dashboard")
	public ResponseEntity<DashboardDTO> getDadosDashboardPor(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getDadosDashboardPor(id));
	}

}
