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
//		buffer.append(livro.getIndice());
		buffer.append("Indice");
		buffer.append("\r\n");
		buffer.append("---");
		
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
//				livro.setIndice( leitor.readLine() );
				leitor.readLine(); //Futuro indice
				leitor.readLine(); //linha que separa os livros
				linha = leitor.readLine();
				arrayLivro.add(livro);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrayLivro;
	}
}
