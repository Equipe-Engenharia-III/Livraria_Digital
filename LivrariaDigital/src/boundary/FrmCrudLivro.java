package boundary;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.LivroController;

public class FrmCrudLivro {
	private JFrame janelaLivro;
	private JLabel lblIsbn;
	private JTextField txtIsbn;
	private JLabel lblTitulo;
	private JTextField txtTitulo;
	private JLabel lblAutor;
	private JComboBox<String> cbAutor;
	private JLabel lblDtPublicacao;
	private JTextField txtDtPublicacao;
	private JLabel lblEditora;
	private JComboBox<String> cbEditora;
	private JLabel lblCategoria;
	private JComboBox<String> cbCategoria;
	private JLabel lblResumo;
	private JTextArea txtaResumo;
	private JLabel lblPrecoCusto;
	private JTextField txtPrecoCusto;
	private JLabel lblPrecoVenda;
	private JTextField txtPrecoVenda;
	private JLabel lblIndice;
	private JTextArea txtaIndice; //Provavelmente este campo seja imagem
	private JLabel lblArquivo;
	private JLabel lblNomeArquivo;
	private JLabel lblOcupaEspaco;
	private JButton btnGravar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnAnexar;
	private JButton btnPesquisar;
	
	public FrmCrudLivro(){
		JPanel painelPrincipal= new JPanel();
		JPanel painelBotoes = new JPanel();
		JPanel painelCentro = new JPanel();
		JPanel painelEsquerda = new JPanel();
		JPanel painelDireita = new JPanel();
		janelaLivro = new JFrame("Livraria Digital - Registro de Livros");
		lblIsbn = new JLabel("ISBN: ");
		txtIsbn = new JTextField();
		lblTitulo = new JLabel("Titulo: ");
		txtTitulo = new JTextField();
		lblAutor = new JLabel("Autor: ");
		cbAutor = new JComboBox<String>();
		lblDtPublicacao = new JLabel("Data de Publicacao: ");
		txtDtPublicacao = new JTextField(10);
		lblEditora = new JLabel("Editora: ");
		cbEditora = new JComboBox<String>();
		lblCategoria = new JLabel("Categoria: ");
		cbCategoria = new JComboBox<String>();
		lblResumo = new JLabel("Resumo: ");
		txtaResumo = new JTextArea(3, 20);
		lblPrecoCusto = new JLabel("Preço de Custo: ");
		txtPrecoCusto = new JTextField(7);
		lblPrecoVenda = new JLabel("Preço de Venda: ");
		txtPrecoVenda = new JTextField(7);
		lblIndice = new JLabel("Indice: ");
		btnAnexar = new JButton("Anexar Indice");
		btnGravar = new JButton("Gravar");
		btnPesquisar = new JButton("Pesquisar");
		lblArquivo = new JLabel("Arquivo: ");
		lblNomeArquivo = new JLabel("Nenhum arquivo anexado");
		lblOcupaEspaco = new JLabel();
		//TemporÃ¡rio
		cbAutor.addItem("");
		cbAutor.addItem("Eduardo Bezerra"); 
		cbAutor.addItem("Katy Sierra");
		cbAutor.addItem("Paulo Silveira");
		cbEditora.addItem("");
		cbEditora.addItem("Editora 1");
		cbEditora.addItem("Editora 2");
		cbEditora.addItem("Editora 3");
		cbCategoria.addItem("");
		cbCategoria.addItem("PROGRAMACAO");
		cbCategoria.addItem("REDES");
		cbCategoria.addItem("ENGENHARIA DE SOFTWARE");
		
		painelPrincipal.setLayout( new BorderLayout() );
		painelCentro.setLayout( new GridLayout(12, 1) );
		painelEsquerda.setLayout( new GridLayout(12, 1) );
		painelDireita.setLayout( new GridLayout(12, 1) );
		painelEsquerda.add( lblIsbn );
		painelCentro.add( txtIsbn );
		painelEsquerda.add( lblTitulo );
		painelCentro.add( txtTitulo );
		painelEsquerda.add( lblAutor );
		painelCentro.add( cbAutor );
		painelEsquerda.add( lblDtPublicacao );
		painelCentro.add( txtDtPublicacao );
		painelEsquerda.add( lblEditora );
		painelCentro.add( cbEditora );
		painelEsquerda.add( lblCategoria );
		painelCentro.add( cbCategoria );
		painelEsquerda.add( lblResumo );
		painelCentro.add( txtaResumo );
		painelEsquerda.add( lblPrecoCusto );
		painelCentro.add( txtPrecoCusto );
		painelEsquerda.add( lblPrecoVenda );
		painelCentro.add( txtPrecoVenda );
		painelEsquerda.add( lblIndice );
		painelCentro.add( btnAnexar );
		painelEsquerda.add( lblArquivo );
		painelCentro.add( lblNomeArquivo );
		painelDireita.add( lblOcupaEspaco );
		painelDireita.add( btnPesquisar );
		painelBotoes.add( btnGravar );
		
		painelPrincipal.add( painelCentro, BorderLayout.CENTER );
		painelPrincipal.add( painelBotoes, BorderLayout.SOUTH );
		painelPrincipal.add( painelEsquerda, BorderLayout.WEST );
		painelPrincipal.add( painelDireita, BorderLayout.EAST );
		
		janelaLivro.setContentPane( painelPrincipal );
		janelaLivro.setSize( 800, 600 );
		janelaLivro.setVisible( true );
		janelaLivro.setLocationRelativeTo(null); //Centraliza a janela
		janelaLivro.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		LivroController livroController = new LivroController(txtIsbn, txtTitulo, 
				cbAutor, txtDtPublicacao, cbEditora, cbCategoria, 
				txtaResumo, txtPrecoCusto, txtPrecoVenda, txtaIndice, 
				btnGravar, btnAnexar, btnPesquisar, lblNomeArquivo);
		btnGravar.addActionListener(livroController);
		btnAnexar.addActionListener(livroController);
		btnPesquisar.addActionListener(livroController);
	}
	
	public static void main(String[] args) {
		new FrmCrudLivro();
	}
}
