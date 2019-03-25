package br.com.bluesoft.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.repository.ProdutoRepository;
import br.com.bluesoft.desafio.vo.FornecedorPrecosVO;

@Service
public class ProdutoServiceImpl implements ProdutoService{
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public Iterable<Produto> listarProdutos(){
		return produtoRepository.findAll();
	}
	
	@Override
	public List<Produto> listarTodosProdutos() {
		
		return (List<Produto>) produtoRepository.findAll();
	}

	@Override
	public FornecedorPrecosVO[] consultarProduto(String gtin) {
		RestTemplate restTemplate = new RestTemplate();
		FornecedorPrecosVO[] fornecedorVO = restTemplate.getForObject("https://egf1amcv33.execute-api.us-east-1.amazonaws.com/dev/produto/" + gtin, FornecedorPrecosVO[].class);
		return fornecedorVO;
	}
	
	@Override
	public Produto buscarProduto(String gtin) {
		return produtoRepository.findOne(gtin);
	}

}
