package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controllers.ProcessaCompra;
import models.Compra;

public class RelatorioForm extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTextArea areaDeTexto;
	private JScrollPane scroll;
	private String dados;

	RelatorioForm() {
		// Propriedades do Formulário
		setTitle("Relatório de Compras");
		setBounds(200, 149, 700, 450);
		panel = new JPanel();
		setContentPane(panel);
		setLayout(null);

		dados = "\n\t\t\tRelatório de Compras\n";
		dados += "\t-----------------------------------------------------------------------------------------------------------------------------------\n";
		dados += "\tNumero\tHora\tProduto\tPreço\tQuantidade\tSubtotal\n";
		dados += "\t-----------------------------------------------------------------------------------------------------------------------------------\n";
		String ultimaData = "";
		double total = 0;
		for (Compra c : ProcessaCompra.getCompras()) {
			if (!ultimaData.contentEquals(c.getData())) {
				dados += "\t"+c.getData() + "\n";
				ultimaData = c.getData();
			}
			dados += "\t"+c.getNum() + "\t" + c.getHora() + "\t" + c.getProduto().getCodigo() + "\t"
					+ c.getProduto().getPreco() + "\t";
			dados += c.getQuantidade() + "\t" + String.format("%.2f", c.getSubtotal()) + "\r\n";
			total += c.getSubtotal();
		}
		dados += "\t----------------------------------------------------------------------------------------------------------------------------------\n";
		dados += "\t\t\t\t\tTotal Geral = " + String.format("R$ %.2f", total) + "\n";
		dados += "\t----------------------------------------------------------------------------------------------------------------------------------\n";
		areaDeTexto = new JTextArea(dados);
		scroll = new JScrollPane(areaDeTexto);
		scroll.setBounds(10, 10, 665, 360);
		panel.add(scroll);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
