package Repository;

import Entity.Book;

import javax.persistence.EntityManagerFactory;
import java.util.UUID;

public class BookRepository extends JpaRepository<Book, UUID>
{
    public BookRepository(EntityManagerFactory emf)
    {
        super(emf, Book.class);
    }
}

