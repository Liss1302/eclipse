package views;

import java.util.Scanner;

import controllers.ProcessaCompra;
import controllers.ProcessaProduto;

public class MainMenu {

	protected static Scanner read = new Scanner(System.in);
	private static TelaProduto telaProdutos = new TelaProduto();
	private static TelaCompra telaCompra = new TelaCompra();
	private static int menu = 0;

	public static void main(String[] args) {
		ProcessaProduto.abrir();
		ProcessaCompra.abrir();
		while (menu != 5) {
			System.out.println(
					"\n1.Cadastro de Produtos\n2.Resgistrar Compras\n3.Conferir Caixa\n4.Salvar\n5.Sair do Sistema\n");
			menu = read.nextInt();
			switch (menu) {
			case 1:
				telaProdutos.crud();
				break;
			case 2:
				telaCompra.cadastrarCompra();
				break;
			case 3:
				telaCompra.conferirCaixa();
				break;
			case 4:
				if(ProcessaProduto.salvar()&&ProcessaCompra.salvar()) {
					System.out.println("Arquivos salvos com sucesso");
				} else {
					System.out.println("Erro ao salvar arquvivos");
				}
				break;
			case 5:
				System.out.println("Até Logo.");
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
		}

	}

}
