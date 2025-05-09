package com.task.Book.Service.service;

import com.task.Book.Service.exception.BookAlreadyExistsException;
import com.task.Book.Service.exception.BookNotFoundException;
import com.task.Book.Service.mapper.BookMapper;
import com.task.Book.Service.model.dto.BookDto;
import com.task.Book.Service.model.entity.Book;
import com.task.Book.Service.repository.BookRepo;
import com.task.workflow.service.model.Status;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//@ComponentScan(value = "com.task.workflow.service")
public class BookServiceImpl implements BookService {
    Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepo bookRepo;
    private final BookMapper bookMapper;
//    private final RestTemplate restTemplate;

    @Override
    public Book createBook(BookDto bookDto) {
        Optional<Book> book = bookRepo.findById(bookDto.getId());
        if (book.isEmpty()) {
//            log.info("Get the step based on the role");
//            String check_url = "http://localhost:9092/workflowService/check";
//            Status status = restTemplate.getForObject(check_url, Status.class);
            log.info("You are allowed to create a new book");
            return bookRepo.save(bookMapper.toEntity(bookDto));
        } else {
            log.error("Forbidden operation,you are not allowed to create a book");
            throw new BookAlreadyExistsException("Forbidden operation");
        }
    }

    @Query(value = "SELECT books.author FROM books ORDER BY id")
    @Override
    public List<BookDto> getAllBooks(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Book> pages = bookRepo.findAll(pageable);
        List<Book> bookList = pages.getContent();
        return bookList.stream().map(s -> bookMapper.toDto(s)).collect(Collectors.toList());
    }

    @Override
    public BookDto viewBook(int id) {
        Optional<Book> book = bookRepo.findById(id);
        if (book.isPresent()) {
            return bookMapper.toDto(book.get());
        } else {
            log.error("book is not found");
            throw new BookNotFoundException("book with this id not found");
        }
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        Book existingBook = bookRepo.findById(bookDto.getId()).get();
        existingBook.setId(bookDto.getId());
        existingBook.setTitle(bookDto.getTitle());
        existingBook.setAuthor(bookDto.getAuthor());
        return bookMapper.toDto(bookRepo.save(existingBook));
    }

    @Override
    public void deleteBook(int id) {
        bookRepo.deleteById(id);
    }

    @Override
    public String getToken(String token) {
        return "Token: " +token;
    }
}
/*@Override
    public Book approveBookByAdmin(int book_id) {
        Book book = bookRepo.findById(book_id).orElseThrow(()-> new BookNotFoundException("book not found"));
        book.setStatus("ALLOWED");
        return bookRepo.save(book);
    }*/
/*    @Override
    public Book createBook(User user, BookDto bookDto) {
        Optional<User> user1 = userRepo.findById(user.getId());
        String get_url = "http://localhost:9092/workflowService/getRole?token="
                +token;
        Role role=restTemplate.getForObject(get_url,Role.class);
        if (role == Role.ADMIN) {
            return bookRepo.save(bookMapper.toEntity(bookDto));
        } else{
                log.info("You are not allowed to create a book");
                throw new BookAlreadyExistsException("Book is already existing");
            }
        }*/