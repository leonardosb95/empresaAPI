package br.com.cadastro.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cadastro.model.Empresa;
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {


	List<Empresa> findById(String nome);

	Page<Empresa> findByNomeEmpresa(String nomeCurso, Pageable paginacao);
	
	@Query(value="SELECT TOP 1* FROM EMPRESA ORDER BY 1 DESC",nativeQuery = true)
	Empresa buscaUltimoId();

	
	

	
	

}
