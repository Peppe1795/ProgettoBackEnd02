package progettoSettimanale;

public class Libri extends ElementiCoumniCatalogo {
	private String autore;
	private String genere;

	public Libri(String isbn, String titolo, int anno, int numeroPagine, String autore, String genere) {
		super(isbn, titolo, anno, numeroPagine);
		this.autore = autore;
		this.genere = genere;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	@Override
	public String toString() {

		return "Titolo: " + titolo + ", " + "anno di pubblicazione: " + anno + ", " + "Autore: " + autore + ", "
				+ "Genere: " + genere;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

}
