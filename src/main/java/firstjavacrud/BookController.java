
package firstjavacrud;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    // READ - Show all books
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("books", repository.findAll());
        return "index";
    }

    // CREATE - Add a new book
    @PostMapping("/add")
    public String addBook(
            @RequestParam String title,
            @RequestParam String author) {

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);

        repository.save(book);

        return "redirect:/";
    }

    // UPDATE - Edit an existing book
    @PostMapping("/update/{id}")
    public String updateBook(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String author) {

        Book book = repository.findById(id).orElseThrow();

        book.setTitle(title);
        book.setAuthor(author);

        repository.save(book);

        return "redirect:/";
    }

    // DELETE - Remove a book
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {

        repository.deleteById(id);

        return "redirect:/";
    }
}


