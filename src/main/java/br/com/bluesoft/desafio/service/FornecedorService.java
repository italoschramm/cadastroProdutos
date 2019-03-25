package br.com.bluesoft.desafio.service;

import br.com.bluesoft.desafio.model.Fornecedor;

public interface FornecedorService {

	public void salvarFornecedor(String cnpj, String nome);
	public boolean existeFornecedor(String cnpj);
	public Fornecedor buscarFornecedor(String cnpj);
}
