package br.com.cadastro.controller.form;

import br.com.cadastro.model.Empresa;
import br.com.cadastro.repository.EmpresaRepository;

public class EmpresaForm {

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

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public Empresa converter(EmpresaRepository repo) {
		return new Empresa(cnpj,nomeEmpresa,nomeUsuario,email);
	}
	

}
