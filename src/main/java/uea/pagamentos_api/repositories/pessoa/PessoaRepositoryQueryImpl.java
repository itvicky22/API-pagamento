package uea.pagamentos_api.repositories.pessoa;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import uea.pagamentos_api.ResumoPessoaDto;
import uea.pagamentos_api.models.Pessoa;
import uea.pagamentos_api.repositories.filters.PessoaFilter;

public class PessoaRepositoryQueryImpl implements PessoaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<ResumoPessoaDto> filtrar(PessoaFilter pessoaFilter) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoPessoaDto> criteria = builder.createQuery(ResumoPessoaDto.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);
		criteria.select(builder.construct(ResumoPessoaDto.class, 
				root.get("nome"), root.get("ativo"), root.get("endereco").get("cidade"), root.get("endereco").get("estado")));
		Predicate[] predicates = criarRestricoes(pessoaFilter, builder, root);
		if(predicates.length > 0) {
			criteria.where(predicates);
		}
		List<ResumoPessoaDto> returnList = manager.createQuery(criteria).getResultList();
		return returnList;
	}

	private Predicate[] criarRestricoes(PessoaFilter pessoaFilter, CriteriaBuilder builder, Root<Pessoa> root) {
		List<Predicate> predicates = new ArrayList<>();

		if(!ObjectUtils.isEmpty(pessoaFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get("nome")),
					"%" + pessoaFilter.getNome().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
