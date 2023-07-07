package progettoSettimanale;

public abstract class ElementiCoumniCatalogo {
	protected String isbn;
	protected String titolo;
	protected int anno;
	protected int numeroPagine;

	public ElementiCoumniCatalogo(String isbn, String titolo, int anno, int numeroPagine) {
		super();
		this.isbn = isbn;
		this.titolo = titolo;
		this.anno = anno;
		this.numeroPagine = numeroPagine;
	}
}
