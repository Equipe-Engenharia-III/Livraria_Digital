package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
	
	public LivroController(JTextField txtIsbn, JTextField txtTitulo,
			JComboBox<String> cbAutor, JTextField txtDtPublicacao,
			JComboBox<String> cbEditora, JComboBox<String> cbCategoria,
			JTextArea txtaResumo, JTextField txtPrecoCusto, 
			JTextField txtPrecoVenda, JTextArea txtaIndice,
			JButton btnGravar, JButton btnAnexar, JButton btnPesquisar){
		
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
	}
	
	public void gravarLivro(){
		Livro livro = new Livro();
		ManipulaArquivoLivro daoLivro = new ManipulaArquivoLivro();
		StringBuffer mensagem = new StringBuffer();
		//Verifica se os campos estão vazios
		if( ! txtIsbn.getText().isEmpty() ){
			livro.setIsbn( txtIsbn.getText() );
		} else{
			mensagem.append("ISBN, ");
		}
		if( ! txtTitulo.getText().isEmpty() ){
			livro.setTitulo( txtTitulo.getText() );
		} else{
			mensagem.append("Titulo, ");
		}
		if( ! cbAutor.getSelectedItem().toString().isEmpty() ){
			livro.setAutor( cbAutor.getSelectedItem().toString() );
		} else{
			mensagem.append("Autor, ");
		}
		if( ! txtDtPublicacao.getText().isEmpty() ){
			livro.setDtPublicacao( txtDtPublicacao.getText() );
		} else{
			mensagem.append("Data de Publicacao, ");
		}
		if( ! cbEditora.getSelectedItem().toString().isEmpty() ){
			livro.setEditora( cbEditora.getSelectedItem().toString() );
		} else{
			mensagem.append("Editora, ");
		}
		if( ! cbCategoria.getSelectedItem().toString().isEmpty() ){
			livro.setCategoria( cbCategoria.getSelectedItem().toString() );
		} else{
			mensagem.append("Categoria, ");
		}
		if( ! txtaResumo.getText().isEmpty() ){
			livro.setResumo( txtaResumo.getText() );
		} else{
			mensagem.append("Resumo, ");
		}
		if( ! txtPrecoCusto.getText().isEmpty() ){
			//Verifica se foi digitado virgula. Se sim substitui por ponto
			if(txtPrecoCusto.getText().contains(",")){
				txtPrecoCusto.setText( txtPrecoCusto.getText().replace(",", ".") );
			}
			livro.setPrecoCusto( Float.parseFloat( txtPrecoCusto.getText() ) );
		} else{
			mensagem.append("Preco Custo, ");
		}
		if( ! txtPrecoVenda.getText().isEmpty() ){
			livro.setPrecoVenda( Float.parseFloat( txtPrecoVenda.getText() ) );
		} else{
			mensagem.append("Preco Venda, ");
		}
		//Se houver campos vazios eh apresentada a mensagem, caso contrario grava o conteudo
		if( mensagem.length() != 0 ){
			JOptionPane.showMessageDialog(null, "Preencha os campos: (" + mensagem.toString() + ")");
		} else{
			try {
				daoLivro.gravarLivro(livro);
				JOptionPane.showMessageDialog(null, "Livro gravado com sucesso!");
				limparCampos();
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Houve um problema com a gravação do livro");
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
	}
//	
//	public Livro alterarLivro(){
//		//TODO
//	}
//	
//	public Livro excluirLivro(){
//		//TODO
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource(); //verifica qual botão está solicitando a ação
		if(source == btnGravar){
			gravarLivro();
			return;
		}
	}
}
