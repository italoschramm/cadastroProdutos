package br.com.bluesoft.desafio.vo;

import java.util.List;

public class PedidoVO {

	private List<ItemVO> itens;
	
	private Integer id;
	
	private ItemFornecedorVO fornecedor;

	/**
	 * @return the itens
	 */
	public List<ItemVO> getItens() {
		return itens;
	}

	/**
	 * @param itens the itens to set
	 */
	public void setItens(List<ItemVO> itens) {
		this.itens = itens;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the fornecedor
	 */
	public ItemFornecedorVO getFornecedor() {
		return fornecedor;
	}

	/**
	 * @param fornecedor the fornecedor to set
	 */
	public void setFornecedor(ItemFornecedorVO fornecedor) {
		this.fornecedor = fornecedor;
	}
}
