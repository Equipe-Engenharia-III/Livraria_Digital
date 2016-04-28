package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import entity.Cliente;
import entity.Livro;

public class ManipulaArquivoCliente {

	public void gravarCliente(Cliente cliente) throws IOException
    {
		StringBuffer buffer = new StringBuffer();
		buffer.append(cliente.getCpf().toUpperCase());
		buffer.append("\r\n");
		buffer.append(cliente.getNome().toUpperCase());
		buffer.append("\r\n");
		buffer.append(cliente.getEndereco().toUpperCase());
		buffer.append("\r\n");
		buffer.append(cliente.getTelefone().toUpperCase());
		buffer.append("\r\n");
		buffer.append(cliente.getDtNasc().toUpperCase());
		buffer.append("\r\n");
		buffer.append("---");//Separador de clientes - Utilizado no metodo atualizarCliente() desta classe
		
		String fileName = "regCliente.txt";
        File arq = new File(fileName);
        boolean arquivoExiste;
        if(arq.exists()){
        	arquivoExiste = true;
        } else {
        	arquivoExiste = false;
        }
        
        FileWriter escreveArquivo = new FileWriter(arq, arquivoExiste);
        PrintWriter gravaDados = new PrintWriter(escreveArquivo);
		gravaDados.write(buffer.toString());
		gravaDados.flush();
		gravaDados.close();
		escreveArquivo.close();
    }
	public ArrayList<Cliente> lerCliente() throws FileNotFoundException{
		String fileName = "regCliente.txt";
		BufferedReader leitor = new BufferedReader(new FileReader(fileName));
		ArrayList<Cliente> arrayCliente = new ArrayList<Cliente>();
		Cliente cliente;
		try {
			String linha = leitor.readLine();
			while( linha != null ){
				cliente = new Cliente();
				cliente.setCpf( linha );
				cliente.setNome( leitor.readLine() );
				cliente.setEndereco( leitor.readLine() );
				cliente.setTelefone( leitor.readLine() );
				cliente.setDtNasc( leitor.readLine() );
				leitor.readLine(); //linha que separa os clientes
				linha = leitor.readLine();
				arrayCliente.add( cliente );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrayCliente;
	}
	
	public void atualizarCliente(Cliente oldCliente, Cliente newCliente){
		String fileName = "regCliente.txt";
		try {
			Float valores = 0.0f;
			StringBuffer buffer = new StringBuffer();
			BufferedReader leitor = new BufferedReader(new FileReader( fileName ));
			File arq = new File( fileName );
			String linha = leitor.readLine(); //Le o ISBN
			while( linha != null ){
				//sobrescreve o cpf caso ele tenha sido alterado
				if( linha.equals( oldCliente.getCpf() ) ){
					buffer.append( newCliente.getCpf() );
					System.out.println("Old CPF: " + oldCliente.getCpf() + "\nNew CPF: " + newCliente.getCpf());
				} else {
					buffer.append(linha); 
				}
				buffer.append("\r\n");
				linha = leitor.readLine();
				//verifica se o nome foi alterado
				if( linha.equals( oldCliente.getNome() ) ){
					buffer.append( newCliente.getNome() );
					System.out.println("Old Nome: " + oldCliente.getNome() + "\nNew Nome: " + newCliente.getNome());
				} else {
					buffer.append(linha); //corrigir
				}
				buffer.append("\r\n");
				linha = leitor.readLine();
				//LÃª a proxima linha e verifica se o endereco foi alterado e assim por diante...
				if( linha.equals( oldCliente.getEndereco() ) ){
					buffer.append( newCliente.getEndereco() );
				} else {
					buffer.append(linha);
				}
				buffer.append("\r\n");
				linha = leitor.readLine();
				if( linha.equals( oldCliente.getTelefone() ) ){
					buffer.append( newCliente.getTelefone() );
				} else {
					buffer.append(linha);
				}
				buffer.append("\r\n");
				linha = leitor.readLine();
				if( linha.equals( oldCliente.getDtNasc() ) ){
					buffer.append( newCliente.getDtNasc() );
				} else {
					buffer.append(linha);
				}
				buffer.append("\r\n");
				linha = leitor.readLine(); //linha que separa os livros
				buffer.append(linha);
				buffer.append("\r\n");
				linha = leitor.readLine(); //le o proximo CPF
			}
			FileWriter escreveArquivo;
			escreveArquivo = new FileWriter(arq, false); //cria um novo arquivo com as atualizações
	        PrintWriter gravaDados = new PrintWriter(escreveArquivo);
			gravaDados.write(buffer.toString());
	        gravaDados.flush();
			gravaDados.close();
			escreveArquivo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void excluirCliente(Cliente oldCliente){
		String fileName = "regCliente.txt";
		try {
			boolean flag = false;
			Float valores = 0.0f;
			StringBuffer buffer = new StringBuffer();
			BufferedReader leitor = new BufferedReader(new FileReader( fileName ));
			File arq = new File( fileName );
			
			String linha = leitor.readLine(); //Le o ISBN
			while( linha != null ){
				if( ! linha.equals( oldCliente.getCpf() ) ){
					buffer.append(linha);
					buffer.append("\r\n");
				} else {
					flag = true;
				}
				if ( !flag ){
					int i = 0;
					while(i < 10){
						linha = leitor.readLine();
						buffer.append(linha);  
						buffer.append("\r\n");
						i++;
					}
				} else {
					int i = 0;
					while(i < 10){
						linha = leitor.readLine(); 
						i++;
						flag = false;
					}
				}
				linha = leitor.readLine(); //le o proximo cpf
			}
			
			FileWriter escreveArquivo;
			escreveArquivo = new FileWriter(arq, false); //cria um novo arquivo com as atualizações
	        PrintWriter gravaDados = new PrintWriter(escreveArquivo);
			gravaDados.write(buffer.toString());
	        gravaDados.flush();
			gravaDados.close();
			escreveArquivo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
