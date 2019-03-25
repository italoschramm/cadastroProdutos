package br.com.bluesoft.desafio.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FornecedorPrecosVO {
	
	private String cnpj;
	
	private String nome;
	
	@JsonProperty("precos")
	private List<PrecoVO> precos;

	/**
	 * @return the precos
	 */
	public List<PrecoVO> getPrecos() {
		return precos;
	}

	/**
	 * @param precos the precos to set
	 */
	public void setPrecos(List<PrecoVO> precos) {
		this.precos = precos;
	}

	/**
	 * @return the cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * @param cnpj the cnpj to set
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
