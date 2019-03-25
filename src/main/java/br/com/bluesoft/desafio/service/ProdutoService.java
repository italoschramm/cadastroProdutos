package br.com.bluesoft.desafio.service;

import java.util.List;

import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.vo.FornecedorPrecosVO;

public interface ProdutoService {

	public List<Produto> listarTodosProdutos();
	public Iterable<Produto> listarProdutos();
	public FornecedorPrecosVO[] consultarProduto(String gtin);
	public Produto buscarProduto(String gtin);
}
