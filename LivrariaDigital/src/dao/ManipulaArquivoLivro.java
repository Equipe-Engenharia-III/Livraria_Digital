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
			Float valores = 0.0f;
			StringBuffer buffer = new StringBuffer();
			BufferedReader leitor = new BufferedReader(new FileReader( fileName ));
			File arq = new File( fileName );
			String linha = leitor.readLine(); //Le o ISBN
			while( linha != null ){
				//sobrescreve o ISBN caso ele tenha sido alterado
				if( linha.equals( oldLivro.getIsbn() ) ){
					buffer.append( newLivro.getIsbn() );
					System.out.println("Old ISBN: " + oldLivro.getIsbn() + "\nNew ISBN: " + newLivro.getIsbn());
				} else {
					buffer.append(linha); 
				}
				buffer.append("\r\n");
				linha = leitor.readLine();
				//verifica se o titulo foi alterado
				if( linha.equals( oldLivro.getTitulo() ) ){
					buffer.append( newLivro.getTitulo() );
					System.out.println("Old Titulo: " + oldLivro.getTitulo() + "\nNew Titulo: " + newLivro.getTitulo());
				} else {
					buffer.append(linha); //corrigir
				}
				buffer.append("\r\n");
				linha = leitor.readLine();
				//Le a proxima linha e verifica se o Autor foi alterado e assim por diante...
				if( linha.equals( oldLivro.getAutor() ) ){
					buffer.append( newLivro.getAutor() );
				} else {
					buffer.append(linha);
				}
				buffer.append("\r\n");
				linha = leitor.readLine();
				if( linha.equals( oldLivro.getDtPublicacao() ) ){
					buffer.append( newLivro.getDtPublicacao() );
				} else {
					buffer.append(linha);
				}
				buffer.append("\r\n");
				linha = leitor.readLine();
				if( linha.equals( oldLivro.getEditora() ) ){
					buffer.append( newLivro.getEditora() );
				} else {
					buffer.append(linha);
				}
				buffer.append("\r\n");
				linha = leitor.readLine();
				if( linha.equals( oldLivro.getCategoria() ) ){
					buffer.append( newLivro.getCategoria() );
				} else {
					buffer.append(linha);
				}
				buffer.append("\r\n");
				linha = leitor.readLine();
				if( linha.equals( oldLivro.getResumo() ) ){
					buffer.append( newLivro.getResumo() );
				} else {
					buffer.append(linha);
				}
				buffer.append("\r\n");
				linha = leitor.readLine();
				valores = Float.parseFloat( linha );
				if( valores.equals( oldLivro.getPrecoCusto() ) ){
					buffer.append( Float.toString( newLivro.getPrecoCusto() ) );
				} else {
					buffer.append(linha);
				}
				buffer.append("\r\n");
				linha = leitor.readLine();
				valores = Float.parseFloat( linha );
				if( valores.equals( oldLivro.getPrecoVenda() ) ){
					buffer.append( Float.toString( newLivro.getPrecoVenda() ) );
				} else {
					buffer.append(linha);
				}
				buffer.append("\r\n");
				linha = leitor.readLine();
				if( linha.equals( oldLivro.getIndice() ) ){
					buffer.append( newLivro.getIndice() );
				} else {
					buffer.append(linha);
				}
				buffer.append("\r\n");
				linha = leitor.readLine(); //linha que separa os livros
				buffer.append(linha);
				buffer.append("\r\n");
				linha = leitor.readLine(); //le o proximo ISBN
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
	
	public void excluirLivro(Livro oldLivro){
		String fileName = "regLivro.txt";
		try {
			boolean flag = false;
			Float valores = 0.0f;
			StringBuffer buffer = new StringBuffer();
			BufferedReader leitor = new BufferedReader(new FileReader( fileName ));
			File arq = new File( fileName );
			
			String linha = leitor.readLine(); //Le o ISBN
			while( linha != null ){
				if( ! linha.equals( oldLivro.getIsbn() ) ){
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
				linha = leitor.readLine(); //le o proximo ISBN
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
