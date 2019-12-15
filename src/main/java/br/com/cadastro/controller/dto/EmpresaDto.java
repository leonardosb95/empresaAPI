package br.com.cadastro.controller.dto;

import org.springframework.data.domain.Page;

import br.com.cadastro.model.Empresa;

public class EmpresaDto {

	private Long id;
	private String cnpj;
	private String empresa;
	private String nomeUsuario;
	private String email;
	
	
	
	public EmpresaDto(Empresa empresa) {
		this.id = empresa.getId();
		this.cnpj = empresa.getCnpj();
		this.empresa = empresa.getNomeEmpresa();
		this.nomeUsuario = empresa.getNomeUsuario();
		this.email=empresa.getEmail();
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static Page<EmpresaDto> converter(Page<Empresa> empresas) {
		return empresas.map(EmpresaDto::new);
	}

}
