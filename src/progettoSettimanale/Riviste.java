package progettoSettimanale;

public class Riviste extends ElementiCoumniCatalogo {
	private Periodicita periodicita;

	public Riviste(String isbn, String titolo, int anno, int numeroPagine, Periodicita periodicita) {
		super(isbn, titolo, anno, numeroPagine);
		this.setPeriodicita(periodicita);
	}

	public Periodicita getPeriodicita() {
		return periodicita;
	}

	public void setPeriodicita(Periodicita periodicita) {
		this.periodicita = periodicita;
	}

	@Override
	public String toString() {

		return "Titolo: " + titolo + ", " + "anno di pubblicazione: " + anno + ", " + "numero delle pagine: "
				+ numeroPagine + ", " + "periodicità: " + periodicita;
	}
}
