package views;

import java.text.SimpleDateFormat;
import java.util.Date;

import controllers.ProcessaCompra;
import controllers.ProcessaProduto;
import models.Compra;
import models.Produto;

public class TelaCompra {

	private Compra compra;
	private Produto produto;
	
	public void cadastrarCompra() {
		for (Produto p : ProcessaProduto.getProdutos()) {
			System.out.println(p.toString());
		}
		System.out.print("Digite o código do produto:");
		produto = new Produto(MainMenu.read.nextInt());
		if (ProcessaProduto.getProdutos().contains(produto)) {
			System.out.print("Digite a quantidade do produto:");
			compra = new Compra();
			compra.setNum(1);
			compra.setData(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			compra.setHora(new SimpleDateFormat("hh:mm").format(new Date()));
			int quantidade = MainMenu.read.nextInt();
			int indice = ProcessaProduto.getProdutos().indexOf(produto);
			if(ProcessaProduto.getProdutos().get(indice).darBaixa(quantidade)) {
				produto.setPreco(ProcessaProduto.getProdutos().get(indice).getPreco());
				compra.setProduto(produto);
				compra.setQuantidade(quantidade);
				ProcessaCompra.getCompras().add(compra);
			} else {
				System.out.println("Quantidade insuficiente no estoque.");
			}
		} else {
			System.out.println("Este produto não está cadastrado.");
		}
	}
	
	public void conferirCaixa() {
		int totalQuantidade = 0;
		double totalDinheiro = 0;
		for(Compra c: ProcessaCompra.getCompras()) {
			System.out.print(c.toString());
			System.out.printf("[subtotal = %.2f]\n",c.subtotal());
			totalQuantidade += c.getQuantidade();
			totalDinheiro += c.subtotal();
		}
		System.out.println("[A quantidade de ítens vendidos é: "+totalQuantidade+"]");
		System.out.printf("[O valor total do caixa é: R$%.2f\n",totalDinheiro);
	}
}
