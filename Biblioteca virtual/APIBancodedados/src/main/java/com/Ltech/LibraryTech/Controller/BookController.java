package com.Ltech.LibraryTech.Controller;

import com.Ltech.LibraryTech.DTO.BookDTO;
import com.Ltech.LibraryTech.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("v1/api/book")
@CrossOrigin(origins = "*")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO) {
        try {
            BookDTO savedBook = bookService.saveBook(bookDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("X Error:" + e.getMessage());
        }

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBook() {
        try {
            List<BookDTO> listBook = bookService.getAllBook();
            return ResponseEntity.ok(listBook);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:" + e);
        }


    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<?> deleteByid(@PathVariable Integer id) {
        try {
            boolean success = bookService.deleteBook(id);
            if (!success){return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado"); }
            return ResponseEntity.ok("Livro deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:" + e);
        } ;
    }
}
