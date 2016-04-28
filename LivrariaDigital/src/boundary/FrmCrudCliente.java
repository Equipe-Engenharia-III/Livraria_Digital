package boundary;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.ClienteController;

public class FrmCrudCliente {
	
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblEndereco;
	private JTextField txtEndereco;
	private JLabel lblTelefone;
	private JTextField txtTelefone;
	private JLabel lblCpf;
	private JTextField txtCpf;
	private JLabel lblDtNasc;
	private JTextField txtDtNasc;
	private JFrame janelaCliente;
	private JButton btnGravar;
	private JButton btnExcluir;
	private JButton btnPesquisar;
	
	public FrmCrudCliente() {
		JPanel painelPrincipal= new JPanel();
		JPanel painelBotoes = new JPanel();
		JPanel painelCentro = new JPanel();
		JPanel painelEsquerda = new JPanel();
		JPanel painelDireita = new JPanel();
		
		janelaCliente = new JFrame("Livraria Digital - Registro de Cliente");
		lblNome = new JLabel("Nome: ");
		txtNome = new JTextField();
		lblEndereco = new JLabel("Endereco: ");
		txtEndereco = new JTextField();
		lblTelefone = new JLabel("Telefone: ");
		txtTelefone = new JTextField(12);
		lblCpf = new JLabel("CPF: ");
		txtCpf = new JTextField();
		lblDtNasc = new JLabel("Data de Nascimento: ");
		txtDtNasc = new JTextField();
		btnGravar = new JButton("Gravar");
		btnExcluir = new JButton("Excluir");
		btnPesquisar = new JButton("Pesquisar");
		painelPrincipal.setLayout( new BorderLayout() );
		painelCentro.setLayout( new GridLayout(12, 1) );
		painelEsquerda.setLayout( new GridLayout(12, 1) );
		painelDireita.setLayout( new GridLayout(12, 1) );
		btnExcluir.setEnabled( false );
		painelEsquerda.add( lblCpf );
		painelCentro.add( txtCpf );
		painelEsquerda.add( lblNome );
		painelCentro.add( txtNome );
		painelEsquerda.add( lblEndereco );
		painelCentro.add( txtEndereco );
		painelEsquerda.add( lblTelefone );
		painelCentro.add( txtTelefone );
		painelEsquerda.add( lblDtNasc );
		painelCentro.add( txtDtNasc );
		painelDireita.add( btnPesquisar );
		painelBotoes.add( btnGravar );
		painelBotoes.add( btnExcluir );
		
		painelPrincipal.add( painelCentro, BorderLayout.CENTER );
		painelPrincipal.add( painelBotoes, BorderLayout.SOUTH );
		painelPrincipal.add( painelEsquerda, BorderLayout.WEST );
		painelPrincipal.add( painelDireita, BorderLayout.EAST );
		
		janelaCliente.setContentPane( painelPrincipal );
		janelaCliente.setSize( 800, 600 );
		janelaCliente.setVisible( true );
		janelaCliente.setLocationRelativeTo(null); //Centraliza a janela
		janelaCliente.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		ClienteController cController = new ClienteController(janelaCliente, txtNome, 
				txtCpf, txtEndereco, txtDtNasc, btnGravar, btnPesquisar, 
				btnExcluir, txtTelefone);
		
		btnGravar.addActionListener( cController );
		btnPesquisar.addActionListener( cController );
		btnExcluir.addActionListener( cController );
	}
	
	public static void main(String[] args) {
		new FrmCrudCliente();
	}

}
