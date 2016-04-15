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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.Iterator;

import boundary.FrmListaLivros;
import boundary.FrmPesquisaLivro;
import dao.ManipulaArquivoLivro;
import entity.Livro;

public class PesquisaController implements ActionListener {

	private JComboBox<String> cbItensPesquisa;
	private JTextField txtPesquisa;
	private JButton btnProcurar;
	private JFrame janelaPesquisa;
	
	public PesquisaController(JComboBox<String> cbItensPesquisa, 
			JTextField txtPesquisa, 
			JButton btnProcurar,
			JFrame janelaPesquisa) {
		this.cbItensPesquisa = cbItensPesquisa;
		this.txtPesquisa = txtPesquisa;
		this.btnProcurar = btnProcurar;
		this.janelaPesquisa = janelaPesquisa;
	}
	
	public void procurarLivro(String itemMenu, String texto){
		ManipulaArquivoLivro arqLivro = new ManipulaArquivoLivro();
		List<Livro> listLivros = new ArrayList();
		try {
			texto = texto.toUpperCase();
			for( Livro livro : arqLivro.lerLivro() ){
				if(itemMenu.equals("TITULO")){
					if(livro.getTitulo().contains( texto )){
						listLivros.add( livro );
					}
				} else if(itemMenu.toUpperCase().equals("AUTOR")){
					if(livro.getAutor().contains( texto )){
						listLivros.add( livro );
					}
				} else if(itemMenu.toUpperCase().equals("EDITORA")){
					if(livro.getEditora().contains( texto )){
						listLivros.add( livro );
					}
				} else if(itemMenu.toUpperCase().equals("CATEGORIA")){
					if(livro.getCategoria().contains( texto )){
						listLivros.add( livro );
					}
				}
			}
			if( listLivros.size() > 0 && !(itemMenu.isEmpty()) ){
				FrmListaLivros frmListaLivros = new FrmListaLivros(itemMenu, texto, listLivros);
			} else if( !(itemMenu.isEmpty()) ){
				JOptionPane.showMessageDialog(janelaPesquisa, "Nenhum resultado encontrado!");
			} else {
				JOptionPane.showMessageDialog(janelaPesquisa, "Digite algo ou escolha uma opção!");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource(); //verifica qual botÃ£o estÃ¡ solicitando a aÃ§Ã£o
		if(source == btnProcurar){
			procurarLivro(cbItensPesquisa.getSelectedItem().toString().toUpperCase(), 
					txtPesquisa.getText().toUpperCase());
			return;
		}
	}
}
