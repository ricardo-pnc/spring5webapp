/**
 * 
 */
package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

/**
 * @author rponciano
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {
	
	// Assim fazemos dependency injection
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;

	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Started in Bootstrap");
		
		Publisher packtPublishers = new Publisher();
		packtPublishers.setName("Packt Publisher");
		packtPublishers.setAddressLine1("Long Road, 35");
		packtPublishers.setCity("Los Angeles");
		packtPublishers.setState("California");
		packtPublishers.setZip("POBOX 23234");
		
		publisherRepository.save(packtPublishers);
		
		System.out.println("Number of publishers: " + publisherRepository.count());

		Author eric = new Author("Eric", "Evans");
		Book bookDDD = new Book("Domain Driven Design", "31324235325");
		eric.getBooks().add(bookDDD);
		bookDDD.getAuthors().add(eric);
		
		bookDDD.setPublisher(packtPublishers);
		packtPublishers.getBooks().add(bookDDD);
		
		authorRepository.save(eric);
		bookRepository.save(bookDDD);
		publisherRepository.save(packtPublishers);
		
		Author rod = new Author("Rod", "Johnson");
		Book bookNoEJB = new Book("J2EE Development without EJB", "1256465562");
		rod.getBooks().add(bookNoEJB);
		bookNoEJB.getAuthors().add(rod);
		
		bookNoEJB.setPublisher(packtPublishers);
		packtPublishers.getBooks().add(bookDDD);
		
		authorRepository.save(rod);
		bookRepository.save(bookNoEJB);
		publisherRepository.save(packtPublishers);
		
		System.out.println("Number of Books: " + bookRepository.count());
		System.out.println("Publisher number of books: " + packtPublishers.getBooks().size());
	}

}
