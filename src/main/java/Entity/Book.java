package Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Book
{
    @Getter
    @Id
    @GeneratedValue
    private UUID id;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String author;

    @Getter
    @Setter
    @ManyToOne
    private BookShelf bookShelf;

    public Book(String title, String author, BookShelf shelf)
    {
        this.title = title;
        this.author = author;
        this.bookShelf = shelf;
    }

    @Override
    public String toString()
    {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", bookShelf=" + bookShelf.getId() +
                '}' + "\n";
    }
}
