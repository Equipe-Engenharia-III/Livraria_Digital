package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entity.Livro;

public class ManipulaArquivoLivro {
	public void gravarLivro(Livro livro) throws IOException
    {
		StringBuffer buffer = new StringBuffer();
		buffer.append(livro.getIsbn().toUpperCase());
		buffer.append("\r\n");
		buffer.append(livro.getTitulo().toUpperCase());
		buffer.append("\r\n");
		buffer.append(livro.getAutor().toUpperCase());
		buffer.append("\r\n");
		buffer.append(livro.getDtPublicacao().toUpperCase());
		buffer.append("\r\n");
		buffer.append(livro.getEditora().toUpperCase());
		buffer.append("\r\n");
		buffer.append(livro.getCategoria().toUpperCase());
		buffer.append("\r\n");
		buffer.append(livro.getResumo().toUpperCase());
		buffer.append("\r\n");
		buffer.append(Float.toString(livro.getPrecoCusto()));
		buffer.append("\r\n");
		buffer.append(Float.toString(livro.getPrecoVenda()));
		buffer.append("\r\n");
		buffer.append( livro.getIndice() );
		buffer.append("\r\n");
		buffer.append("---");//Separador de livros - Utilizado no metodo atualizarLivro() desta classe
		
		String fileName = "regLivro.txt";
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
	
	public ArrayList<Livro> lerLivro() throws FileNotFoundException{
		String fileName = "regLivro.txt";
		BufferedReader leitor = new BufferedReader(new FileReader(fileName));
		ArrayList<Livro> arrayLivro = new ArrayList<Livro>();
		Livro livro;
		try {
			String linha = leitor.readLine();
			while( linha != null ){
				livro = new Livro();
				livro.setIsbn( linha );
				livro.setTitulo( leitor.readLine() );
				livro.setAutor( leitor.readLine() );
				livro.setDtPublicacao( leitor.readLine() );
				livro.setEditora( leitor.readLine() );
				livro.setCategoria( leitor.readLine() );
				livro.setResumo( leitor.readLine() );
				livro.setPrecoCusto( Float.parseFloat( leitor.readLine() ) );
				livro.setPrecoVenda( Float.parseFloat( leitor.readLine() ) );
				livro.setIndice( leitor.readLine() );
				leitor.readLine(); //linha que separa os livros
				linha = leitor.readLine();
				arrayLivro.add(livro);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrayLivro;
	}
	
	public void atualizarLivro(Livro oldLivro, Livro newLivro){
		String fileName = "regLivro.txt";
		try {
			BufferedReader leitor = new BufferedReader(new FileReader( fileName ));
			File arq = new File( fileName );
			FileWriter escreveArquivo;
			escreveArquivo = new FileWriter(arq, true); //arquivo deve existir
	        PrintWriter gravaDados = new PrintWriter(escreveArquivo);
			String linha = leitor.readLine(); //Lê o ISBN
			while( linha != null ){
				//sobrescreve o ISBN caso ele tenha sido alterado
				if( linha.equals( oldLivro.getIsbn() ) ){
					gravaDados.append( newLivro.getIsbn() );
				}
				//Lê a proxima linha e verifica se o titulo foi alterado
				if( leitor.readLine().equals( oldLivro.getTitulo() ) ){
					gravaDados.append( newLivro.getTitulo() );
				}
				//Lê a proxima linha e verifica se o Autor foi alterado e assim por diante...
				if( leitor.readLine().equals( oldLivro.getAutor() ) ){
					gravaDados.append( newLivro.getAutor() );
				}
				if( leitor.readLine().equals( oldLivro.getDtPublicacao() ) ){
					gravaDados.append( newLivro.getDtPublicacao() );
				}
				if( leitor.readLine().equals( oldLivro.getEditora() ) ){
					gravaDados.append( newLivro.getEditora() );
				}
				if( leitor.readLine().equals( oldLivro.getCategoria() ) ){
					gravaDados.append( newLivro.getCategoria() );
				}
				if( leitor.readLine().equals( oldLivro.getResumo() ) ){
					gravaDados.append( newLivro.getResumo() );
				}
				if( leitor.readLine().equals( oldLivro.getPrecoCusto() ) ){
					gravaDados.append( Float.toString( newLivro.getPrecoCusto() ) );
				}
				if( leitor.readLine().equals( oldLivro.getPrecoVenda() ) ){
					gravaDados.append( Float.toString( newLivro.getPrecoVenda() ) );
				}
				if( leitor.readLine().equals( oldLivro.getIndice() ) ){
					gravaDados.append( newLivro.getIndice() );
				}
				leitor.readLine(); //linha que separa os livros
				linha = leitor.readLine();
			}
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
