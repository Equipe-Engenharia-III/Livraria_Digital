package boundary;

import java.awt.BorderLayout;
import java.util.List;

import entity.Livro;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FrmListaLivros {
	private JFrame janelaListaLivros;
	private JTable tblListaLivros;
	private JButton btnDetalhesLivro[];
	private JButton btnAddCarrinho[];
	private DefaultTableModel tblModel;
	private JScrollPane scrollPane;
	
	public FrmListaLivros(String parametro, String pesquisa, List<Livro> livro) {
		String columnNames[] = {"TITULO", "AUTOR",
				"PREÇO", "DETALHES DO LIVRO", "ADD CARRINHO"};
		int size = 0;
		JPanel panelPrincipal = new JPanel();
		janelaListaLivros = new JFrame("Resultado da pesquisa por " 
		+ parametro + " - " + pesquisa);
		tblModel = new DefaultTableModel();
		tblListaLivros = new JTable();
		btnAddCarrinho = new JButton[livro.size()];
		btnDetalhesLivro = new JButton[livro.size()];
		scrollPane = new JScrollPane();
		for(int i = 0; i < livro.size(); i++){
			btnAddCarrinho[i] = new JButton("ADD CARINHO");
			btnDetalhesLivro[i] = new JButton("DETALHES");
		}
		tblModel.setColumnIdentifiers(columnNames);
		size = tblModel.getRowCount();
		tblModel.setRowCount(size + livro.size());
		for(int i = 0; i < livro.size(); i++){
			tblModel.setValueAt(livro.get(i).getTitulo(), i, 0); //Titulo
			tblModel.setValueAt(livro.get(i).getAutor(), i, 1);  //Autor
			tblModel.setValueAt(livro.get(i).getPrecoVenda(), i, 2); //Preço
			tblModel.setValueAt(btnDetalhesLivro[i], i, 3); //Mostrar a imagem do botão
			tblModel.setValueAt(btnAddCarrinho[i], i, 4);//Mostrar a imagem do botão
		}
		tblListaLivros.setModel(tblModel);
		scrollPane.add(tblListaLivros);
		scrollPane.setHorizontalScrollBar(new JScrollBar());
		panelPrincipal.setLayout( new BorderLayout() );
		panelPrincipal.add(tblListaLivros, BorderLayout.CENTER);
		janelaListaLivros.setContentPane( panelPrincipal );
		janelaListaLivros.setSize( 800, 600 );
		janelaListaLivros.setVisible( true );
		janelaListaLivros.setLocationRelativeTo(null); //Centraliza a janela
		janelaListaLivros.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
	}
}
