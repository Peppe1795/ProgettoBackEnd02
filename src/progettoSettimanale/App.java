package progettoSettimanale;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

public class App {
	private static File file = new File("disk.txt");

	public static void main(String[] args) {

		Set<ElementiCoumniCatalogo> archivio = new LinkedHashSet<>();

		Libri libro1 = new Libri("6154985125632", "Storia di una ladra di libri", 2021, 350, "Markus Zusak ",
				"Autobiografico");
		Libri libro2 = new Libri("6515116516516", "Non dirmi che hai paura", 2023, 200, "Giuseppe Catozzella",
				"Romanzo");
		Libri libro3 = new Libri("6515111561655", "La fattoria degli animali", 1965, 430, "George Orwell", "Favola");
		Libri libro4 = new Libri("5161615165151", "L'invito", 2022, 1200, "Ruth Ware  ", "Horror");
		Libri libro5 = new Libri("6516165165151", "The Harvester", 2000, 800, "Ahra Manyu  ", "Thriller");

		Riviste rivista1 = new Riviste("1816151511664", "Focus", 2023, 45, Periodicita.TIRMESTRALE);
		Riviste rivista2 = new Riviste("6515151651651", "The Lancet", 2023, 60, Periodicita.SETTIMANALE);
		Riviste rivista3 = new Riviste("6511151651515", "Settimana Enigmistica", 2021, 15, Periodicita.SETTIMANALE);
		Riviste rivista4 = new Riviste("5151516516516", "Il Politico", 2023, 30, Periodicita.MENISLE);
		Riviste rivista5 = new Riviste("6511155616516", "Cosmos", 2007, 65, Periodicita.MENISLE);
		System.out.println("------------- Archivio -----------");
		addElement(archivio, libro1);
		addElement(archivio, libro2);
		addElement(archivio, libro3);
		addElement(archivio, libro4);
		addElement(archivio, libro5);
		addElement(archivio, rivista1);
		addElement(archivio, rivista2);
		addElement(archivio, rivista3);
		addElement(archivio, rivista4);
		addElement(archivio, rivista5);

		printArchivio(archivio);

		removeElement(archivio, "5151516516516");
		removeElement(archivio, "6515116516516");

		System.out.println();
		System.out.println("--------- Archivio Modificato ----------");
		System.out.println();

		printArchivio(archivio);

		System.out.println();
		System.out.println("---------- Ricerca in base all'ISbn -----------");
		System.out.println();

		ElementiCoumniCatalogo findElementForISbn = searchForIsbn(archivio, "6511151651515");
		System.out.println(findElementForISbn.toString());

		System.out.println();
		System.out.println("--------- Ricerca in base alla data di pubblicazione ----------");
		System.out.println();

		Set<ElementiCoumniCatalogo> searchDate = searchForDate(archivio, 2023);
		searchDate.forEach(s -> System.out.println(s.toString()));

		System.out.println();
		System.out.println("--------- Ricerca in base all Authore ----------");
		System.out.println();

		Set<ElementiCoumniCatalogo> searchAuthor = searchForAuthor(archivio, "Markus Zusak ");
		searchAuthor.forEach(s -> System.out.println(s.toString()));

		saveDisk(archivio);
		System.out.println();
		System.out.println("--------- Lettura file txt ----------");
		System.out.println();

		try {
			readDisk();
		} catch (IOException e) {
			e.printStackTrace();
		}
		;
	};

	public static void printArchivio(Set<ElementiCoumniCatalogo> archivio) {
		archivio.forEach(s -> {
			if (s instanceof Libri) {
				Libri libro = (Libri) s;
				System.out.println("Titolo: " + s.titolo);
				System.out.println("ISBN: " + s.isbn);

				System.out.println("Autore: " + libro.getAutore());
				System.out.println("Genere: " + libro.getGenere());
				System.out.println("---------------------------");
			} else if (s instanceof Riviste) {
				Riviste rivista = (Riviste) s;
				System.out.println("Titolo: " + s.titolo);
				System.out.println("ISBN: " + s.isbn);
				System.out.println("Periodicità: " + rivista.getPeriodicita());
				System.out.println("---------------------------");
			}
		});
	}

	public static void addElement(Set<ElementiCoumniCatalogo> archivio, ElementiCoumniCatalogo addElement) {
		archivio.add(addElement);
	}

	public static void removeElement(Set<ElementiCoumniCatalogo> archivio, String isbn) {
		Predicate<ElementiCoumniCatalogo> isThisIsbn = p -> p.isbn.equals(isbn);
		archivio.removeIf(isThisIsbn);
	}

	public static ElementiCoumniCatalogo searchForIsbn(Set<ElementiCoumniCatalogo> archivio, String isbn) {
		Predicate<ElementiCoumniCatalogo> isThisisbn = p -> p.isbn.equals(isbn);
		return archivio.stream().filter(isThisisbn).findFirst().orElse(null);
	}

	public static Set<ElementiCoumniCatalogo> searchForDate(Set<ElementiCoumniCatalogo> archivio, int date) {
		Predicate<ElementiCoumniCatalogo> isThisDate = p -> p.anno == date;
		return archivio.stream().filter(isThisDate).collect(Collectors.toSet());

	}

	public static Set<ElementiCoumniCatalogo> searchForAuthor(Set<ElementiCoumniCatalogo> archivio, String author) {
		return archivio.stream()
				.filter(element -> element instanceof Libri && ((Libri) element).getAutore().equals(author))
				.collect(Collectors.toSet());
	}

	public static void saveDisk(Set<ElementiCoumniCatalogo> text) {
		try {
			List<String> lines = new ArrayList<>();
			for (ElementiCoumniCatalogo elemento : text) {
				lines.add(elemento.toString());
			}
			FileUtils.writeLines(file, "UTF-8", lines, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String readDisk() throws IOException {
		if (file.exists()) {
			String contenuto = FileUtils.readFileToString(file, "UTF-8");
			System.out.println(contenuto);
			return contenuto;

		} else {
			System.out.println("File non trovato");
			return "";
		}

	}

}
