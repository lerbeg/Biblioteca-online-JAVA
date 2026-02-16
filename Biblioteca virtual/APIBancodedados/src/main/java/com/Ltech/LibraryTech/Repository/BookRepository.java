package com.Ltech.LibraryTech.Repository;
import com.Ltech.LibraryTech.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  BookRepository extends JpaRepository<Book,Integer> {
    Book findBookByAuthor(String nameAuthor);
    Book findBookByGenre(String genre);
    boolean existsBookById(Integer id);
}
