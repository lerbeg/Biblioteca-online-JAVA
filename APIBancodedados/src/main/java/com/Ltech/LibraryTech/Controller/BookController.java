package com.Ltech.LibraryTech.Controller;

import com.Ltech.LibraryTech.DTO.BookDTO;
import com.Ltech.LibraryTech.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("v1/api/book")
@CrossOrigin(origins = "*")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO){
        try{
            BookDTO savedBook = bookService.saveBook(bookDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("X Error:" + e.getMessage());
        }

    }
}
