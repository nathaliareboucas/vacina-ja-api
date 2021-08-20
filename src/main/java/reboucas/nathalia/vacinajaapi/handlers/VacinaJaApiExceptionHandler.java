package reboucas.nathalia.vacinajaapi.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import reboucas.nathalia.vacinajaapi.models.Erro;
import reboucas.nathalia.vacinajaapi.services.exceptions.CpfCadastradoException;
import reboucas.nathalia.vacinajaapi.services.exceptions.MenorDeIdadeException;

@RestController
@ControllerAdvice
public class VacinaJaApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Erro> erros = criarListaDeErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String mensagemUsuario = "Mensagem inválida";
		String mensagemDesenvolvedor = ex.getMessage();
		Erro erro = new Erro(mensagemUsuario, mensagemDesenvolvedor, System.currentTimeMillis());
		return handleExceptionInternal(ex, erro, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		String mensagemUsuario ="Recurso não encontrado.";
		String mensagemDesenvolvedor = ex.toString();
		Erro erro = new Erro(mensagemUsuario, mensagemDesenvolvedor, System.currentTimeMillis());
		
		return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(CpfCadastradoException.class)
	public ResponseEntity<Object> handleCpfCadastradoException(CpfCadastradoException ex,
			WebRequest request) {	
		
		Erro erro = new Erro(ex.getMessage(), ex.getMessage(), System.currentTimeMillis());
		
		return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(MenorDeIdadeException.class)
	public ResponseEntity<Object> handleMenorDeIdadeException(MenorDeIdadeException ex,
			WebRequest request) {	
		
		Erro erro = new Erro(ex.getMessage(), ex.getMessage(), System.currentTimeMillis());
		
		return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	private List<Erro> criarListaDeErros(BindingResult bindingResult) {
		List<Erro> erros = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String mensagemUsuario = fieldError.getDefaultMessage();
			String mensagemDesenvolvedor = fieldError.toString();
			erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor, System.currentTimeMillis()));
		}

		return erros;
	}

}
