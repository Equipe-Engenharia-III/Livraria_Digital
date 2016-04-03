package boundary;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.PesquisaController;

public class FrmPesquisaLivro {
	private JFrame janelaPesquisa;
	private JLabel lblItem;
	private JComboBox<String> cbItensPesquisa;
	private JTextField txtPesquisa;
	private JButton btnProcurar;
	
	public FrmPesquisaLivro() {
		JPanel panelPrincipal = new JPanel();
		janelaPesquisa = new JFrame("Pesquisa de Livros");
		lblItem = new JLabel("Selecione o item: ");
		cbItensPesquisa = new JComboBox<String>();
		txtPesquisa = new JTextField(20);
		btnProcurar = new JButton("Procurar");
		
		cbItensPesquisa.addItem("");
		cbItensPesquisa.addItem("Titulo");
		cbItensPesquisa.addItem("Autor");
		cbItensPesquisa.addItem("Editora");
		cbItensPesquisa.addItem("Categoria");
		
		panelPrincipal.add(lblItem);
		panelPrincipal.add(cbItensPesquisa);
		panelPrincipal.add(txtPesquisa);
		panelPrincipal.add(btnProcurar);
		
		janelaPesquisa.setContentPane( panelPrincipal );
		janelaPesquisa.setSize( 600, 100 );
		janelaPesquisa.setVisible( true );
		janelaPesquisa.setLocationRelativeTo(null); //Centraliza a janela
		janelaPesquisa.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		PesquisaController pesqController = new PesquisaController(
				cbItensPesquisa, txtPesquisa, btnProcurar);
		btnProcurar.addActionListener(pesqController);
	}
	
	public static void main(String[] args) {
		new FrmPesquisaLivro();
	}
}
