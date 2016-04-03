package entity;

public class Livro {
	private String isbn;
	private String titulo;
	private String autor;
	private String dtPublicacao;
	private String editora;
	private String categoria;
	private String resumo;
	private float precoCusto;
	private float precoVenda;
	private String indice;
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public String getDtPublicacao() {
		return dtPublicacao;
	}
	
	public void setDtPublicacao(String dtPublicacao) {
		this.dtPublicacao = dtPublicacao;
	}
	
	public String getEditora() {
		return editora;
	}
	
	public void setEditora(String editora) {
		this.editora = editora;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getResumo() {
		return resumo;
	}
	
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	
	public float getPrecoCusto() {
		return precoCusto;
	}
	
	public void setPrecoCusto(float precoCusto) {
		this.precoCusto = precoCusto;
	}
	
	public float getPrecoVenda() {
		return precoVenda;
	}
	
	public void setPrecoVenda(float precoVenda) {
		this.precoVenda = precoVenda;
	}
	
	public String getIndice() {
		return indice;
	}
	
	public void setIndice(String indice) {
		this.indice = indice;
	}
	
}
