package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import authentication.ValidaCpf;
import dao.ManipulaArquivoCliente;
import dao.ManipulaArquivoLivro;
import entity.Cliente;
import entity.Livro;


public class ClienteController implements ActionListener {

	private JFrame janelaCliente;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEndereco;
	private JTextField txtTelefone;
	private JTextField txtDtNasc;
	private JButton btnGravar;
	private JButton btnPesquisar;
	private JButton btnExcluir;
	private Cliente oldCliente = null;
	private boolean pesquisarPressionado = false;
	
	public ClienteController(JFrame janelaCliente, JTextField txtNome,
			JTextField txtCpf, JTextField txtEndereco, JTextField txtDtNasc,
			JButton btnGravar, JButton btnPesquisar, JButton btnExcluir,
			JTextField txtTelefone) {
		this.janelaCliente = janelaCliente;
		this.txtNome = txtNome;
		this.txtCpf = txtCpf;
		this.txtEndereco = txtEndereco;
		this.txtDtNasc = txtDtNasc;
		this.btnExcluir = btnExcluir;
		this.btnGravar = btnGravar;
		this.btnPesquisar = btnPesquisar;
		this.txtTelefone = txtTelefone;
	}
	
	public void gravarCliente(){
		Cliente cliente;
		ManipulaArquivoCliente daoCliente = new ManipulaArquivoCliente();
		if( validaCampos() ){
			try {
				cliente = new Cliente();
				cliente.setCpf( txtCpf.getText() );
				cliente.setNome( txtNome.getText() );
				cliente.setEndereco( txtEndereco.getText() );
				cliente.setTelefone( txtTelefone.getText() );
				cliente.setDtNasc( txtDtNasc.getText() );
				daoCliente.gravarCliente( cliente );
				limparCampos();
				JOptionPane.showMessageDialog(janelaCliente, "Cliente gravado com sucesso!");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(janelaCliente, "Houve um problema com a gravacao do cliente. \n"
							+ "Contate o administrador do sistema! \n Descricao Tecnica: " + e.getMessage());
			}
		}
	}
	
	private void limparCampos(){
		txtCpf.setText("");
		txtNome.setText("");
		txtDtNasc.setText("");
		txtEndereco.setText("");
		txtTelefone.setText("");
	}
	
	public void pesquisarCliente(){
		ManipulaArquivoCliente daoCliente = new ManipulaArquivoCliente();
		StringBuffer buffer = new StringBuffer();
		oldCliente = null;
		try {
			List<Cliente> arrayCliente = new ArrayList();
			//for each para ler o arquivo texto de clientes e guardar 
			//a informacao no array daqueles que satisfazem a pesquisa
			if( ! txtNome.getText().isEmpty() ) {
				for ( Cliente cliente : daoCliente.lerCliente() ){
					if ( cliente.getNome().contains( txtNome.getText().toUpperCase() ) ) {
							arrayCliente.add( cliente );
					}
				}
				//Trouxe mais de uma opcao para pesquisa
				//abre uma JOptionPane com as opcoes.
				if ( arrayCliente.size() > 1 ){
					oldCliente = new Cliente();
					Object[] possibilities = new Object[ arrayCliente.size() ];
					for( int i = 0; i < arrayCliente.size(); i++ ){
						possibilities[i] = new Object();
						possibilities[i] = arrayCliente.get( i ).getNome();
					}
				    String s = (String) JOptionPane.showInputDialog(janelaCliente, "Escolha o cliente:\n", "A pesquisa "
				    		+ "retornou mais de 1 resultado",JOptionPane.INFORMATION_MESSAGE, 
				    		null, possibilities, possibilities[0]);
					if (s != null && s.length() > 0) {
						for(int i = 0; i < arrayCliente.size(); i++){
							if ( arrayCliente.get( i ).getNome().equals( s ) ){
								oldCliente = arrayCliente.get( i );
							}
						}
					}
				}
				//Trouxe apenas um resultado
				else if ( arrayCliente.size() == 1){
					oldCliente = new Cliente();
					oldCliente = arrayCliente.get( 0 );
				}
				//Se o livro for diferente de vazio
				if(oldCliente != null){
					txtCpf.setText( oldCliente.getCpf() );
					txtNome.setText( oldCliente.getNome() );
					txtEndereco.setText( oldCliente.getEndereco() );
					txtTelefone.setText( oldCliente.getTelefone() );
					txtDtNasc.setText( oldCliente.getDtNasc() );
				} else{
					JOptionPane.showMessageDialog(janelaCliente, "A pesquisa n„o encontrou nenhum resultado!");
				}
			} else {
				JOptionPane.showMessageDialog(janelaCliente, "Digite algum nome para pesquisar");
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(janelaCliente, "Houve um problema com a alteracao do cliente. \n"
					+ "Contate o administrador do sistema! \n Descricao Tecnica: " + e.getMessage());
		}
	}
	
	public void alterarCliente(Cliente oldCliente){
		ManipulaArquivoCliente daoCliente = new ManipulaArquivoCliente();
		Cliente newCliente;
		StringBuffer buffer = new StringBuffer();
		if ( validaCampos() ){
			newCliente = new Cliente();
			newCliente.setCpf( txtCpf.getText() );
			newCliente.setNome( txtNome.getText() );
			newCliente.setEndereco( txtEndereco.getText() );
			newCliente.setTelefone( txtTelefone.getText() );
			daoCliente.atualizarCliente(oldCliente, newCliente);
			JOptionPane.showMessageDialog(janelaCliente, "Cliente alterado com sucesso!");
			limparCampos();
		}
	}
	
	public boolean validaCampos(){
		boolean isValido = false;
	    StringBuffer mensagem = new StringBuffer();
	    int idade = 0;
	    DateFormat dateFormat = new SimpleDateFormat("yyyy/HH/dd");
	    Date currentDate = new Date();
	    if(txtCpf.getText().isEmpty() ){
			mensagem.append("CPF, ");
		} else if ( ! ValidaCpf.isCpf( txtCpf.getText() ) ) {
			mensagem.append("CPF invalido, ");
		}
		if( txtNome.getText().isEmpty() ){
			mensagem.append("Nome, ");
		}
		if( txtEndereco.getText().isEmpty() ){
			mensagem.append("Endereco, ");
		}
		if( txtDtNasc.getText().isEmpty() ){
			mensagem.append("Data de Nascimento, ");
		}
		idade = Integer.parseInt( dateFormat.format( currentDate ).substring(0, 4) ) 
				- Integer.parseInt( txtDtNasc.getText().substring( 6 ) );
		if( idade < 18 ){
			mensagem.append("o cliente deve ser maior de idade!");
		}
		if( mensagem.length() > 0){
			JOptionPane.showMessageDialog(janelaCliente, "Preencha/Altere os campos: " + mensagem.toString());
			return false;
		}
		return true;
	}
	
	public void excluirCliente(){
		ManipulaArquivoCliente daoCliente = new ManipulaArquivoCliente();
		Cliente oldCliente = new Cliente();
		if ( validaCampos() ){
			oldCliente.setCpf( txtCpf.getText() );
			oldCliente.setNome( txtNome.getText() );
			oldCliente.setEndereco( txtEndereco.getText() );
			oldCliente.setTelefone( txtTelefone.getText() );
			oldCliente.setDtNasc( txtDtNasc.getText() );
			daoCliente.excluirCliente(oldCliente);
			JOptionPane.showMessageDialog(janelaCliente, "Cliente: " + oldCliente.getNome() + " excluido com sucesso!");
			limparCampos();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource(); //verifica qual botao esta solicitando a acao
		if( source == btnGravar ){
			//Se o pesquisar foi pressionado, ent√£o o metodo alterarLivro precisa ser chamado
			if( pesquisarPressionado ){
				alterarCliente( oldCliente );
				pesquisarPressionado = false;
			} else {
				btnExcluir.setEnabled( false );
				gravarCliente();
			}
			return;
		}
		//Se o botao pesquisar foi pressionado, o botao gravar muda sua funcao
		if( source == btnPesquisar ){
			pesquisarPressionado = true;
			pesquisarCliente();
			btnExcluir.setEnabled( true ); //se o botao pesquisar foi pressionado o excluir tambem fica
		}
		if( source == btnExcluir ){
			excluirCliente();
			btnExcluir.setEnabled( false );
		}
	}
}
