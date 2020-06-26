package views;

import controllers.ProcessaCompra;
import controllers.ProcessaProduto;
import models.Compra;
import models.Produto;

public class TelaCompra {

	private Compra compra;
	private int codProd;
	private int quantidade;
	
	public void cadastrarCompra() {
		for(Produto p: ProcessaProduto.getProdutos()) {
			System.out.println(p.toString());
		}
		System.out.print("Digite o código do produto:");
		codProd = MainMenu.read.nextInt();
		System.out.print("Digite a quantidade do produto:");
		quantidade = MainMenu.read.nextInt();
		
		compra = new Compra();
		compra.setNum(1);
		compra.setData("26/06/2020");
		compra.setHora("11:27");
		compra.setProduto(new Produto(codProd));
		compra.setQuantidade(quantidade);
		
		ProcessaCompra.getCompras().add(compra);
	}
}
