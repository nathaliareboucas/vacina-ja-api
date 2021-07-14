package reboucas.nathalia.vacinajaapi.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DashboardDTO {
	
	private String usuarioId;
	private boolean usuarioProcessado;
	private boolean usuarioCadastrado;
	private Integer totalCadastrado;
	private Integer totalVacinado; // total de registros processados
	private Integer totalNaoVacinado; // total de registros que ainda não foram processados
	private int posicaoUsuarioNaFila;

	public Integer calcularPosicaoUsuarioNaFila(List<String> idsUsuariosParaVacinar) {
		if (usuarioCadastrado && usuarioProcessado) {
			posicaoUsuarioNaFila = -1; // Já recebeu a notificação = já se vacinou
		}
		
		for (int posicao = 0; posicao < idsUsuariosParaVacinar.size(); posicao++) {
			if (idsUsuariosParaVacinar.get(posicao).equals(usuarioId)) {
				posicaoUsuarioNaFila = posicao + 1;
			}
		}
		
		return posicaoUsuarioNaFila;
	}
	
}
