package Repository;

import Entity.BookShelf;

import javax.persistence.EntityManagerFactory;
import java.util.UUID;

public class BookShelfRepository extends JpaRepository<BookShelf, UUID>
{
    public BookShelfRepository(EntityManagerFactory emf)
    {
        super(emf, BookShelf.class);
    }
}
