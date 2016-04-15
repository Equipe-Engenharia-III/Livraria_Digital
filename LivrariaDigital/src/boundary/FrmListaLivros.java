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

import controller.ButtonColumn;

public class FrmListaLivros {
	private JFrame janelaListaLivros;
	private JTable tblListaLivros;
	private DefaultTableModel tblModel;
	private JScrollPane scrollPane;
	private ButtonColumn btnColumnDetalhesLivro;
	private ButtonColumn btnColumnAddCarrinho;
	
	public FrmListaLivros(String parametro, String pesquisa, List<Livro> livro) {
		String columnNames[] = {"TITULO", "AUTOR",
				"PREÃ‡O", "DETALHES DO LIVRO", "ADD CARRINHO"};
		int size = 0;
		JPanel panelPrincipal = new JPanel();
		janelaListaLivros = new JFrame("Resultado da pesquisa por " 
		+ parametro + " - " + pesquisa);
		tblModel = new DefaultTableModel();
		scrollPane = new JScrollPane();
		tblModel.setColumnIdentifiers(columnNames);
		size = tblModel.getRowCount();
		tblModel.setRowCount(size + livro.size());
		for(int i = 0; i < livro.size(); i++){
			tblModel.setValueAt(livro.get(i).getTitulo(), i, 0); //Titulo
			tblModel.setValueAt(livro.get(i).getAutor(), i, 1);  //Autor
			tblModel.setValueAt(livro.get(i).getPrecoVenda(), i, 2); //Preco
			tblModel.setValueAt("Detalhes do Livro", i, 3); //Mostrar a imagem do botao
			tblModel.setValueAt("Add ao Carrinho", i, 4);//Mostrar a imagem do botao
		}
		tblListaLivros = new JTable(tblModel);
		btnColumnDetalhesLivro = new ButtonColumn(tblListaLivros, null, 3); //mudar o null para o nome da ação
		btnColumnAddCarrinho = new ButtonColumn(tblListaLivros, null, 4); //mudar o null para o nome da ação corresponde
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
