package br.com.bluesoft.desafio.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bluesoft.desafio.service.ProdutoService;
import br.com.bluesoft.desafio.vo.FornecedorPrecosVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProduto {

	@Autowired
	private ProdutoService produtoService;
	
	@Test
	public void consultarProduto() {
		FornecedorPrecosVO[] lista = produtoService.consultarProduto("7894900011517");
		Assert.assertNotNull(lista);
	}
}
