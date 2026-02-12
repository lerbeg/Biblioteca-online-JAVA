package com.Ltech.LibraryTech.Service;

import com.Ltech.LibraryTech.DTO.BookDTO;
import com.Ltech.LibraryTech.Entity.Book;
import com.Ltech.LibraryTech.Repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public BookDTO saveBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setAuthor(bookDTO.getAuthor());
        book.setSummary(bookDTO.getSummary());
        book.setGenre(bookDTO.getGenre());
        book.setPrice(bookDTO.getPrice());
        Book bookSave = bookRepository.save(book);

        return new BookDTO(
                bookSave.getId(),
                bookSave.getAuthor(),
                bookSave.getSummary(),
                bookSave.getGenre(),
                bookSave.getPrice()
        );
    }

    public List<BookDTO> getAllBook() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getAuthor(),
                        book.getSummary(),
                        book.getGenre(),
                        book.getPrice()
                ))
                .collect(Collectors.toList());


    }

    public boolean deleteBook(Integer id){
        if(bookRepository.existsBookById(id)){
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
