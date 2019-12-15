package br.com.cadastro.atualizacao.form;

import br.com.cadastro.model.Empresa;
import br.com.cadastro.repository.EmpresaRepository;

public class AtualizaEmpresaForm {

	private String cnpj;
	private String nomeEmpresa;
	private String nomeUsuario;
	private String email;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
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

	public Empresa atualizar(Long id, EmpresaRepository repo) {
		Empresa empresa = repo.getOne(id);
		empresa.setCnpj(this.cnpj);
		empresa.setEmail(this.email);
		empresa.setNomeEmpresa(this.nomeEmpresa);
		empresa.setNomeUsuario(this.nomeUsuario);
		
		return empresa;
	}

}
