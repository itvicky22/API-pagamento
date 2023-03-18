package uea.pagamentos_api.repositories.pessoa;

import java.util.List;

import uea.pagamentos_api.ResumoPessoaDto;
import uea.pagamentos_api.repositories.filters.PessoaFilter;

public interface PessoaRepositoryQuery {
	public List<ResumoPessoaDto> filtrar(PessoaFilter pessoaFilter);
}