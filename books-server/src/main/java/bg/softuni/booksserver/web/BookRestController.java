package bg.softuni.booksserver.web;

import bg.softuni.booksserver.model.dto.BookDTO;
import bg.softuni.booksserver.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findBookById(@PathVariable Long id){
        Optional<BookDTO> bookById = bookService.findBookById(id);

//        return bookById.map(bookDTO -> ResponseEntity.ok(bookDTO))
//                .orElseGet(() -> ResponseEntity.notFound().build());

        return bookById.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);

        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO, UriComponentsBuilder uriComponentsBuilder){

        long newBookID = bookService.createBook(bookDTO);

        return ResponseEntity.created(
                uriComponentsBuilder.path("/api/books/{id}")
                        .build(newBookID)).build();
    }
}
