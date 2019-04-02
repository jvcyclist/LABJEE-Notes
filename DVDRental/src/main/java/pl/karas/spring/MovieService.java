package pl.karas.spring;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieService {
	private static MovieService instance;
	private static final Logger LOGGER = Logger.getLogger(MovieService.class.getName());

	private final HashMap<Long, Movie> contacts = new HashMap<>();
	private long nextId = 0;

	private MovieService() {
	}

	/**
	 * @return a reference to an example facade for Customer objects.
	 */
	public static MovieService getInstance() {
		if (instance == null) {
			instance = new MovieService();
			instance.ensureTestData();
		}
		return instance;
	}

	/**
	 * @return all available Customer objects.
	 */
	public synchronized List<Movie> findAll() {
		return findAll(null);
	}

	
	public synchronized List<Movie> findAll(String stringFilter) {
		ArrayList<Movie> arrayList = new ArrayList<>();
		for (Movie contact : contacts.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(contact.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(MovieService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Movie>() {

			@Override
			public int compare(Movie o1, Movie o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		return arrayList;
	}

	/**
	 * Finds all Customer's that match given filter and limits the resultset.
	 *
	 * @param stringFilter
	 *            filter that returned objects should match or null/empty string
	 *            if all objects should be returned.
	 * @param start
	 *            the index of first result
	 * @param maxresults
	 *            maximum result count
	 * @return list a Customer objects
	 */
	public synchronized List<Movie> findAll(String stringFilter, int start, int maxresults) {
		ArrayList<Movie> arrayList = new ArrayList<>();
		for (Movie contact : contacts.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(contact.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(MovieService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Movie>() {

			@Override
			public int compare(Movie o1, Movie o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		int end = start + maxresults;
		if (end > arrayList.size()) {
			end = arrayList.size();
		}
		return arrayList.subList(start, end);
	}

	/**
	 * @return the amount of all customers in the system
	 */
	public synchronized long count() {
		return contacts.size();
	}

	/**
	 * Deletes a customer from a system
	 *
	 * @param value
	 *            the Customer to be deleted
	 */
	public synchronized void delete(Movie value) {
		contacts.remove(value.getId());
	}

	/**
	 * Persists or updates customer in the system. Also assigns an identifier
	 * for new Customer instances.
	 *
	 * @param entry
	 */
	public synchronized void save(Movie entry) {
		if (entry == null) {
			LOGGER.log(Level.SEVERE,
					"Customer is null. Are you sure you have connected your form to the application as described in tutorial chapter 7?");
			return;
		}
		if (entry.getId() == null) {
			entry.setId(nextId++);
		}
		try {
			entry = (Movie) entry.clone();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		contacts.put(entry.getId(), entry);
	}

	/**
	 * Sample data generation
	 */
	public void ensureTestData() {
		if (findAll().isEmpty()) {
			final String[] names = new String[] { "Title1Test Desc1Test", "Title1Test Desc1Test", 
					"Title1Test Desc1Test", "Title1Test Desc1Test", "Title1Test Desc1Test", "Title1Test Desc1Test", 
					"Title1Test Desc1Test", "Title1Test Desc1Test", "Title1Test Desc1Test", "Title1Test Desc1Test", 
					"Title1Test Desc1Test", "Title1Test Desc1Test", "Title1Test Desc1Test", "Title1Test Desc1Test", 
					"Title1Test Desc1Test", "Title1Test Desc1Test", "Title1Test Desc1Test", "Title1Test Desc1Test", 
					"Title1Test Desc1Test", "Title1Test Desc1Test", "Title1Test Desc1Test", "Title1Test Desc1Test", 
					"Title1Test Desc1Test", "Title1Test Desc1Test", "Title1Test Desc1Test", "Title1Test Desc1Test", 
					"Title1Test Desc1Test", "Title1Test Desc1Test", "Title1Test Desc1Test", "Title1Test Desc1Test", 
					"Title1Test Desc1Test", "Title1Test Desc1Test", "Title1Test Desc1Test", "Title1Test Desc1Test", 
					"Title1Test Desc1Test", "Title1Test Desc1Test", "Title1Test Desc1Test", "Title1Test Desc1Test", };
			Random r = new Random(0);
			for (String name : names) {
				String[] split = name.split(" ");
				Movie c = new Movie();
				c.setTitle(split[0]);
				c.setDescription(split[1]);
				
				save(c);
			}
		}
	}

}