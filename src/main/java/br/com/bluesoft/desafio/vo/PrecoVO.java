package br.com.bluesoft.desafio.vo;

import java.math.BigDecimal;

public class PrecoVO {
	
	private BigDecimal preco;
	
	private Integer quantidade_minima;

	/**
	 * @return the preco
	 */
	public BigDecimal getPreco() {
		return preco;
	}

	/**
	 * @param preco the preco to set
	 */
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	/**
	 * @return the quantidade_minima
	 */
	public Integer getQuantidade_minima() {
		return quantidade_minima;
	}

	/**
	 * @param quantidade_minima the quantidade_minima to set
	 */
	public void setQuantidade_minima(Integer quantidade_minima) {
		this.quantidade_minima = quantidade_minima;
	}

}
