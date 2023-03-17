package uea.pagamentos_api.repositories.lancamento;

import java.util.List;

import uea.pagamentos_api.ResumoLancamentoDto;
import uea.pagamentos_api.repositories.filters.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	public List<ResumoLancamentoDto> filtrar(LancamentoFilter lacamentoFilter);
	
}
