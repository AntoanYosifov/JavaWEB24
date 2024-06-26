package bg.softuni.booksserver.service.impl;

import bg.softuni.booksserver.model.dto.AuthorDTO;
import bg.softuni.booksserver.model.dto.BookDTO;
import bg.softuni.booksserver.model.entity.AuthorEntity;
import bg.softuni.booksserver.model.entity.BookEntity;
import bg.softuni.booksserver.repository.AuthorRepository;
import bg.softuni.booksserver.repository.BookRepository;
import bg.softuni.booksserver.service.BookService;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {

        return bookRepository.findAll()
                .stream()
                .map(BookServiceImpl::mapBookToDTO)
                .toList();

    }

    @Override
    public Optional<BookDTO> findBookById(Long id) {
        return bookRepository.findById(id)
                .map(BookServiceImpl::mapBookToDTO);

    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
        public Long createBook(BookDTO bookDTO) {
        AuthorEntity author = authorRepository.findByName(bookDTO.getAuthorDTO().getName()).orElse(null);


        if(author == null){
            author = new AuthorEntity()
                    .setName(bookDTO.getAuthorDTO().getName());
            authorRepository.save(author);
        }


        BookEntity newBook = new BookEntity()
                .setAuthor(author)
                .setTitle(bookDTO.getTitle())
                .setIsbn(bookDTO.getIsbn());

        return bookRepository.save(newBook).getId();

        }


    private static BookDTO mapBookToDTO(BookEntity bookEntity) {
        AuthorDTO authorDTO = new AuthorDTO();

        authorDTO.setName(bookEntity.getAuthor().getName());

        return new BookDTO()
                .setId(bookEntity.getId())
                .setAuthorDTO(authorDTO)
                .setTitle(bookEntity.getTitle())
                .setIsbn(bookEntity.getIsbn());
    }
}
