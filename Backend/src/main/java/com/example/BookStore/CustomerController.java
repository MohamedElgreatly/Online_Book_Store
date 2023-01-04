package com.example.BookStore;

import com.example.BookStore.Models.*;
import com.example.BookStore.Repository.BookRepo;
import com.example.BookStore.Repository.CartRepo;
import com.example.BookStore.Repository.CustomerRepo;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/customer")
public class CustomerController {
  CustomerRepo customerRepo = new CustomerRepo();
// get customer by id
    @GetMapping("/get/{username}")
    public Customer getCustomer(@PathVariable String username) {
        customerRepo.setConnection(BookStoreApplication.dbConnection);
        return customerRepo.getCustomer(username);
    }


    //1. Edit his personal information including his password
    @PutMapping("/editCustomer")
    public boolean editCustomer(@RequestBody Customer customer) {
        customerRepo.setConnection(BookStoreApplication.dbConnection);
         return customerRepo.editCustomer(customer);
    }

    //2. Search for books by any of the book’s attributes. (Use indices to speed up searches when possible)
    // search by title
    ////Book_ISBN, title, Publication_Year, Category, price, amount, Publisher
    BookRepo bookRepo = new BookRepo();
    @GetMapping("/searchByTitle/{title}")
    public ArrayList<Book> searchByTitle(@PathVariable String title) throws SQLException {
        bookRepo.setConnection(BookStoreApplication.dbConnection);
       return   bookRepo.search("title", title);
    }
    // search by author
    @GetMapping("/searchByAuthor/{author}")
    public ArrayList<Book> searchByAuthor(@PathVariable String author) throws SQLException {
        bookRepo.setConnection(BookStoreApplication.dbConnection);

        return bookRepo.searchByAuthor(author);
    }
    // search by category
    @GetMapping("/searchByCategory/{category}")
    public ArrayList<Book> searchByCategory(@PathVariable String category) throws SQLException {
        bookRepo.setConnection(BookStoreApplication.dbConnection);
        return bookRepo.search("category", category);
    }
    // search by price
    @GetMapping("/searchByIsbn/{isbn}")
    public ArrayList<Book> searchByIsbn(@PathVariable String isbn) throws SQLException {
        bookRepo.setConnection(BookStoreApplication.dbConnection);
        return bookRepo.search("isbn", isbn);
            }
    // search by publisher
    @GetMapping("/searchByPublisher/{publisher}")
    public ArrayList<Book> searchByPublisher(@PathVariable String publisher) throws SQLException {
        bookRepo.setConnection(BookStoreApplication.dbConnection);
        return bookRepo.search("Publisher",publisher);
    }

    @GetMapping("/getBooks")
       public ArrayList<Book> getBooks() {
        bookRepo.setConnection(BookStoreApplication.dbConnection);
        return bookRepo.getAllBooks();
    }
    //3. View the details of a book
    /**
     * this if card stored in db
     * 3. View the details of a card information
     * @param
     * @return
     */
    //create card
    CartRepo cartRepo = new CartRepo();
    @PostMapping("/createCard")
    public int createCard(@PathVariable String username) {
        cartRepo.setConnection(BookStoreApplication.dbConnection);
        return cartRepo.createCard(username);
    }
    //3. Add books to a shopping cart
    @PostMapping("/addToCart")
    public boolean addToCart(@RequestBody Book book, @PathVariable int cardID) {
        cartRepo.setConnection(BookStoreApplication.dbConnection);
        return cartRepo.addBookToItemsAndCart(book.getBook_ISBN(), cardID, book.getAmount(), book.getPrice());
    }
    //4. Remove books from a shopping cart
    @PutMapping("/removeFromCart")
    public boolean removeFromCart(@RequestBody Book book, @PathVariable int cardID) {
        cartRepo.setConnection(BookStoreApplication.dbConnection);

        return cartRepo.removeBookFromItemsAndCart(book.getBook_ISBN(), cardID, book.getAmount(), book.getPrice());
    }
    //4. Manage his shopping cart. This includes the following.
    //* • View the items in the cart
    @GetMapping("/viewCart ")
    public shopping_cart viewCart(@PathVariable int cardID) {
        cartRepo.setConnection(BookStoreApplication.dbConnection);
        return cartRepo.getCart(cardID);
    }


    //5. Checkout a shopping cart
    //* • The customer is then required to provide a credit card number and its expiry date.
    //* This transaction is completed successfully if the credit card information is appropriate.
    @PostMapping("/checkout")
    public boolean checkout(@PathVariable int cardID) {
        cartRepo.setConnection(BookStoreApplication.dbConnection);

            return cartRepo.checkout_cart(cardID);
    }

    //6. Logout of the system
    @GetMapping("/logout/{username}")
    public boolean logout( @PathVariable String username) {
        customerRepo.setConnection(BookStoreApplication.dbConnection);
        return customerRepo.customer_log_out(username);
    }

}
