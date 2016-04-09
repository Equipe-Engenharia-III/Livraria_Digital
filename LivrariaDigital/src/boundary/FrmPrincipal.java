package boundary;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.PesquisaController;
import javax.swing.JSeparator;

public class FrmPrincipal {
	private JFrame janelaPesquisa;
	private JLabel lblItem;
	private JComboBox<String> cbItensPesquisa;
	private JTextField txtPesquisa;
	private JButton btnProcurar;
	private JLabel lblFiltro;
	private JButton btnMeuCarrinho;
	private JTextField textField;
	
	public FrmPrincipal() {
		JPanel panelPrincipal = new JPanel();
		janelaPesquisa = new JFrame("Principal");
		lblItem = new JLabel("Pesquisa");
		lblItem.setBounds(41, 112, 66, 16);
		cbItensPesquisa = new JComboBox<String>();
		cbItensPesquisa.setBounds(420, 107, 96, 27);
		txtPesquisa = new JTextField(20);
		txtPesquisa.setBounds(106, 106, 254, 28);
		btnProcurar = new JButton("Ir");
		btnProcurar.setBounds(527, 106, 75, 29);
		
		cbItensPesquisa.addItem("");
		cbItensPesquisa.addItem("Titulo");
		cbItensPesquisa.addItem("Autor");
		cbItensPesquisa.addItem("Editora");
		cbItensPesquisa.addItem("Categoria");
		panelPrincipal.setLayout(null);
		
		panelPrincipal.add(lblItem);
		panelPrincipal.add(cbItensPesquisa);
		panelPrincipal.add(txtPesquisa);
		panelPrincipal.add(btnProcurar);
		
		janelaPesquisa.setContentPane( panelPrincipal );
		janelaPesquisa.setSize( 1024, 768 );
		janelaPesquisa.setVisible( true );
		janelaPesquisa.setLocationRelativeTo(null); //Centraliza a janela
		janelaPesquisa.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		PesquisaController pesqController = new PesquisaController(
				cbItensPesquisa, txtPesquisa, btnProcurar, janelaPesquisa);
		
		lblFiltro = new JLabel("Filtrar");
		lblFiltro.setBounds(372, 112, 43, 16);
		panelPrincipal.add(lblFiltro);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(192, 176, 461, 4);
		panelPrincipal.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 146, 1012, 12);
		panelPrincipal.add(separator_1);
		
		JButton btnNewButton = new JButton("Entre ou Cadastre-se");
		btnNewButton.setBounds(634, 106, 160, 29);
		panelPrincipal.add(btnNewButton);
		
		btnMeuCarrinho = new JButton("Meu Carrinho");
		btnMeuCarrinho.setBounds(809, 106, 117, 29);
		panelPrincipal.add(btnMeuCarrinho);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setBounds(938, 106, 50, 28);
		panelPrincipal.add(textField);
		textField.setColumns(10);
		btnProcurar.addActionListener(pesqController);
	}
	
	public static void main(String[] args) {
		new FrmPrincipal();
	}
}
