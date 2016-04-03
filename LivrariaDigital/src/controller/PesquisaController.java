package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.text.html.HTMLDocument.Iterator;

import dao.ManipulaArquivoLivro;
import entity.Livro;

public class PesquisaController implements ActionListener {

	private JComboBox<String> cbItensPesquisa;
	private JTextField txtPesquisa;
	private JButton btnProcurar;
	
	public PesquisaController(JComboBox<String> cbItensPesquisa, JTextField txtPesquisa,
			JButton btnProcurar) {
		this.cbItensPesquisa = cbItensPesquisa;
		this.txtPesquisa = txtPesquisa;
		this.btnProcurar = btnProcurar;
	}
	
	private void procurarLivro(String itemMenu, String texto){
		ManipulaArquivoLivro arqLivro = new ManipulaArquivoLivro();
		Set<Livro> listLivros = new HashSet();
		try {
			texto = texto.toUpperCase();
			for( Livro livro : arqLivro.lerLivro() ){
				if(itemMenu.toUpperCase().equals("TITULO")){
					if(livro.getTitulo().toUpperCase().contains( texto )){
						listLivros.add(livro);
					}
				} else if(itemMenu.toUpperCase().equals("AUTOR")){
					if(livro.getAutor().toUpperCase().contains(texto)){
						listLivros.add(livro);
					}
				} else if(itemMenu.toUpperCase().equals("EDITORA")){
					if(livro.getEditora().toUpperCase().contains(texto)){
						listLivros.add(livro);
					}
				} else if(itemMenu.toUpperCase().equals("CATEGORIA")){
					if(livro.getCategoria().toUpperCase().contains(texto)){
						listLivros.add(livro);
					}
				}
			}
			
			//Somente para teste
			for( Livro livro : listLivros ){
				System.out.println("ISBN: " + livro.getIsbn());
				System.out.println("Titulo: " + livro.getTitulo());
				System.out.println("Autor: " + livro.getAutor());
				System.out.println("Categoria: " + livro.getCategoria());
				System.out.println("Resumo: " + livro.getResumo());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource(); //verifica qual botão está solicitando a ação
		if(source == btnProcurar){
			procurarLivro(cbItensPesquisa.getSelectedItem().toString(), txtPesquisa.getText());
			return;
		}
	}

}
