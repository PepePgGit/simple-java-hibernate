package Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode
@Entity
public class BookShelf
{
    @Getter
    @Id
    @GeneratedValue
    private UUID id;

    @Getter
    @OneToMany(mappedBy = "bookShelf", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Book> books;

    public BookShelf()
    {
        books = new ArrayList<>();
    }

    @Override
    public String toString()
    {
        return  "\n" +"BookShelf{" +
                "id=" + id +
                ", books=" + "\n" +
                books + '}'+ "\n";
    }
}
