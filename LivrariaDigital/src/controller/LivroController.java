package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import dao.ManipulaArquivoLivro;
import entity.Livro;

public class LivroController implements ActionListener{
	private JTextField txtIsbn;
	private JTextField txtTitulo;
	private JComboBox<String> cbAutor;
	private JTextField txtDtPublicacao;
	private JComboBox<String> cbEditora;
	private JComboBox<String> cbCategoria;
	private JTextArea txtaResumo;
	private JTextField txtPrecoCusto;
	private JTextField txtPrecoVenda;
	private JTextArea txtaIndice;
	private JButton btnGravar;
	private JButton btnAnexar;
	private JButton btnPesquisar;
	private JLabel lblNomeArquivo;
	private Livro oldLivro = null;
	
	public LivroController(JTextField txtIsbn, JTextField txtTitulo,
			JComboBox<String> cbAutor, JTextField txtDtPublicacao,
			JComboBox<String> cbEditora, JComboBox<String> cbCategoria,
			JTextArea txtaResumo, JTextField txtPrecoCusto, 
			JTextField txtPrecoVenda, JTextArea txtaIndice,
			JButton btnGravar, JButton btnAnexar, JButton btnPesquisar,
			JLabel lblNomeArquivo){
		
		this.txtIsbn = txtIsbn;
		this.btnPesquisar = btnPesquisar;
		this.txtTitulo = txtTitulo;
		this.cbAutor = cbAutor;
		this.txtDtPublicacao = txtDtPublicacao;
		this.cbEditora = cbEditora;
		this.cbCategoria = cbCategoria;
		this.txtaResumo = txtaResumo;
		this.txtPrecoCusto = txtPrecoCusto;
		this.txtPrecoVenda = txtPrecoVenda;
		this.txtaIndice = txtaIndice;
		this.btnAnexar = btnAnexar;
		this.btnGravar = btnGravar;
		this.lblNomeArquivo = lblNomeArquivo;
	}
	
	public void gravarLivro(){
		Livro livro = new Livro();
		ManipulaArquivoLivro daoLivro = new ManipulaArquivoLivro();
		if( validaCampos() ){
			try {
				livro.setIsbn( txtIsbn.getText() );
				livro.setTitulo( txtTitulo.getText() );
				livro.setAutor( cbAutor.getSelectedItem().toString() );
				livro.setDtPublicacao( txtDtPublicacao.getText() );
				livro.setEditora( cbEditora.getSelectedItem().toString() );
				livro.setCategoria( cbCategoria.getSelectedItem().toString() );
				livro.setResumo( txtaResumo.getText() );
				livro.setPrecoCusto( Float.parseFloat( txtPrecoCusto.getText() ) );
				livro.setPrecoCusto( Float.parseFloat( txtPrecoVenda.getText() ) );
				livro.setIndice( anexarPdf() ); //grava o caminho completo do PDF no arquivo texto
				daoLivro.gravarLivro(livro);
				JOptionPane.showMessageDialog(null, "Livro gravado com sucesso!");
				limparCampos();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Houve um problema com a gravação do livro. \n"
							+ "Contate o administrador do sistema! \n Descrição Técnica: " + e.getMessage());
			}
		}
	}
	
	private void limparCampos(){
		txtIsbn.setText("");
		txtTitulo.setText("");
		cbAutor.setSelectedIndex(0);
		txtDtPublicacao.setText("");
		cbEditora.setSelectedIndex(0);
		cbCategoria.setSelectedIndex(0);
		txtaResumo.setText("");;
		txtPrecoCusto.setText("");
		txtPrecoVenda.setText("");
		lblNomeArquivo.setText("Nenhum Arquivo Anexado!");
	}
	
	public void pesquisarLivro(){
		ManipulaArquivoLivro daoLivro = new ManipulaArquivoLivro();
		StringBuffer buffer = new StringBuffer();
		try {
			List<Livro> arrayLivro = new ArrayList();
			//for each para ler o arquivo texto de livros e guardar 
			//a informação no array daqueles que satisfazem a pesquisa
			if( ! txtTitulo.getText().isEmpty() ) {
				for ( Livro livro : daoLivro.lerLivro() ){
					if ( livro.getTitulo().contains( txtTitulo.getText().toUpperCase() ) ) {
							arrayLivro.add( livro );
					}
				}
				//Trouxe mais de uma opção para pesquisa
				if ( arrayLivro.size() > 1 ){
					oldLivro = new Livro();
					//abre uma JOptionPane com as opções.
				//Trouxe apenas um resultado
				} else if ( arrayLivro.size() == 1){
					oldLivro = new Livro();
					oldLivro = arrayLivro.get( 0 );
					txtIsbn.setText( oldLivro.getIsbn() );
					txtTitulo.setText( oldLivro.getTitulo() );
					cbAutor.setSelectedItem( oldLivro.getAutor() ); //corrigir
					txtDtPublicacao.setText( oldLivro.getDtPublicacao() );
					cbEditora.setSelectedItem( oldLivro.getEditora() ); //corrigir
					cbCategoria.setSelectedItem( oldLivro.getEditora() ); //corrigir
					txtaResumo.setText( oldLivro.getResumo() );
					txtPrecoCusto.setText( Float.toString( oldLivro.getPrecoCusto() ) );
					txtPrecoVenda.setText( Float.toString( oldLivro.getPrecoVenda() ) );
					lblNomeArquivo.setText( oldLivro.getIndice() );
				}
			} else {
				JOptionPane.showMessageDialog(null, "Digite algum título para pesquisar");
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Houve um problema com a alteração do livro. \n"
					+ "Contate o administrador do sistema! \n Descrição Técnica: " + e.getMessage());
		}
	}
	public void alterarLivro(Livro oldLivro){
		ManipulaArquivoLivro daoLivro = new ManipulaArquivoLivro();
		Livro newLivro;
		StringBuffer buffer = new StringBuffer();
		if ( validaCampos() ){
			newLivro = new Livro();
			newLivro.setIsbn( txtIsbn.getText() );
			newLivro.setTitulo( txtTitulo.getText() );
			newLivro.setAutor( cbAutor.getSelectedItem().toString() );
			newLivro.setDtPublicacao( txtDtPublicacao.getText() );
			newLivro.setEditora( cbEditora.getSelectedItem().toString() );
			newLivro.setCategoria( cbCategoria.getSelectedItem().toString() );
			newLivro.setResumo( txtaResumo.getText() );
			newLivro.setPrecoCusto( Float.parseFloat( txtPrecoCusto.getText() ) );
			newLivro.setPrecoCusto( Float.parseFloat( txtPrecoVenda.getText() ) );
			newLivro.setIndice( anexarPdf() );
			daoLivro.atualizarLivro(oldLivro, newLivro);
		}
		
	}
	public boolean validaCampos(){
		boolean isValido = false;
		StringBuffer mensagem = new StringBuffer();
		//Verifica se os campos estão vazios
		if(txtIsbn.getText().isEmpty() ){
			mensagem.append("ISBN, ");
		} 
		if( txtTitulo.getText().isEmpty() ){
			mensagem.append("Titulo, ");
		}
		if( cbAutor.getSelectedItem().toString().isEmpty() ){
			mensagem.append("Autor, ");
		}
		if( txtDtPublicacao.getText().isEmpty() ){
			mensagem.append("Data de Publicacao, ");
		}
		if( cbEditora.getSelectedItem().toString().isEmpty() ){
			mensagem.append("Editora, ");
		}
		if( cbCategoria.getSelectedItem().toString().isEmpty() ){
			mensagem.append("Categoria, ");
		}
		if( txtaResumo.getText().isEmpty() ){
			mensagem.append("Resumo, ");
		}
		if( ! txtPrecoCusto.getText().isEmpty() ){
			//Verifica se foi digitado virgula. Se sim substitui por ponto
			if(txtPrecoCusto.getText().contains(",")){
				txtPrecoCusto.setText( txtPrecoCusto.getText().replace(",", ".") );
			}
		} else{
			mensagem.append("Preco Custo, ");
		}
		if( ! txtPrecoVenda.getText().isEmpty() ){
			if(txtPrecoVenda.getText().contains(",")){
				txtPrecoVenda.setText( txtPrecoVenda.getText().replace(",", ".") );
			}
		} else{
			mensagem.append("Preco Venda, ");
		}
		if ( lblNomeArquivo.getText().contains("Nenhum") ){
			mensagem.append("Indice, ");
		}
		if( mensagem.length() > 0){
			return false;
		}
		return true;
	}
	
	public String anexarPdf(){
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivo PDF", "pdf");
		String diretorioBase = System.getProperty("user.home") + "/Desktop";
		File dir = new File(diretorioBase);
		JFileChooser choose = new JFileChooser();
		choose.setCurrentDirectory(dir);
		choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
		choose.setAcceptAllFileFilterUsed(false);
		choose.addChoosableFileFilter(filtro);
		String caminhoCompletoArquivo = "";
		String nomeArquivo = "";
		int retorno = choose.showOpenDialog(null);
		if( retorno == JFileChooser.APPROVE_OPTION ){
			caminhoCompletoArquivo = choose.getSelectedFile().getAbsolutePath();
			nomeArquivo = choose.getSelectedFile().getName();
			lblNomeArquivo.setText( nomeArquivo );
		}
		return caminhoCompletoArquivo;
	}
	
	
//	public Livro excluirLivro(){
//		//TODO
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource(); //verifica qual botão está solicitando a ação
		boolean pesquisarPressionado = false;
		if( source == btnGravar ){
			//Se o pesquisar foi pressionado, então o metodo alterarLivro precisa ser chamado
			if( pesquisarPressionado ){
				alterarLivro(oldLivro);
			} else {
				gravarLivro();
			}
			return;
		}
		//Se o botão pesquisar foi pressionado, o botao gravar muda sua função
		if( source == btnPesquisar ){
			pesquisarPressionado = true;
			pesquisarLivro();
		}
		if( source == btnAnexar ){
			anexarPdf();
		}
	}
}
