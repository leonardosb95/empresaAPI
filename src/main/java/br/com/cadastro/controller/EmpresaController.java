package br.com.cadastro.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cadastro.atualizacao.form.AtualizaEmpresaForm;
import br.com.cadastro.controller.dto.EmpresaDto;
import br.com.cadastro.controller.form.EmpresaForm;
import br.com.cadastro.model.Empresa;
import br.com.cadastro.repository.EmpresaRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/empresa")
public class EmpresaController {

	@Autowired
	private EmpresaRepository empresaRepository;

	@GetMapping
	@Cacheable(value = "listaDeEmpresas")
	public Page<EmpresaDto> lista(@RequestParam(required = false) String nomeCurso,
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {

		if (nomeCurso == null) {
			Page<Empresa> empresas = empresaRepository.findAll(paginacao);
			return EmpresaDto.converter(empresas);
		} else {
			Page<Empresa> empresas = empresaRepository.findByNomeEmpresa(nomeCurso, paginacao);
			return EmpresaDto.converter(empresas);
		}
	}
	
	public Empresa ultimoIdCadastrado() {
		Empresa empresa=empresaRepository.buscaUltimoId();
		return empresa;
		
	}
	

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeEmpresas", allEntries = true)
	public ResponseEntity<EmpresaDto> cadastrar(@RequestBody @Valid EmpresaForm form, UriComponentsBuilder uriBuilder) {
		Empresa empresa = form.converter(empresaRepository);
		empresaRepository.save(empresa);

		URI uri = uriBuilder.path("/empresas/{id}").buildAndExpand(empresa.getId()).toUri();
		return ResponseEntity.created(uri).body(new EmpresaDto(empresa));		
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmpresaDto> detalhar(@PathVariable Long id) {
		Optional<Empresa> empresa = empresaRepository.findById(id);
		if (empresa.isPresent()) {
			return ResponseEntity.ok(new EmpresaDto(empresa.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeEmpresas", allEntries = true)
	public ResponseEntity<EmpresaDto> atualizar(@PathVariable Long id,
			@RequestBody @Valid AtualizaEmpresaForm form) {
		Optional<Empresa> optional = empresaRepository.findById(id);
		if (optional.isPresent()) {
			Empresa empresa = form.atualizar(id, empresaRepository);
			return ResponseEntity.ok(new EmpresaDto(empresa));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeEmpresas", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Empresa> optional = empresaRepository.findById(id);
		if (optional.isPresent()) {
			empresaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
