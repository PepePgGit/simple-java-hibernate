import Repository.BookRepository;
import Repository.BookShelfRepository;
import Entity.Book;
import Entity.BookShelf;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;
import java.util.UUID;

public class Launcher
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab4");

        BookShelf shelf1 = new BookShelf();
        Book book1 = new Book("Castle", "Paweł", shelf1);
        Book book2 = new Book("King", "Mieszko", shelf1);
        Book book3 = new Book("Queen", "Stefan", shelf1);

        BookShelf shelf2 = new BookShelf();
        Book book4 = new Book("Something", "Andrzej", shelf2);
        Book book5 = new Book("Anything", "Piotr", shelf2);
        Book book6 = new Book("Skateboard", "Jan", shelf2);

        shelf1.getBooks().add(book1);
        shelf1.getBooks().add(book2);
        shelf1.getBooks().add(book3);

        shelf2.getBooks().add(book4);
        shelf2.getBooks().add(book5);
        shelf2.getBooks().add(book6);

        BookShelfRepository shelfRepo = new BookShelfRepository(emf);
        BookRepository bookRepo = new BookRepository(emf);

        shelfRepo.add(shelf1);
        bookRepo.add(book1);
        bookRepo.add(book2);
        bookRepo.add(book3);

        shelfRepo.add(shelf2);
        bookRepo.add(book4);
        bookRepo.add(book5);
        bookRepo.add(book6);

        System.out.println("Shelves" + "\n" + shelfRepo.findAll() + "\n");

        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            System.out.println("0 - if you want to quit" + "\n" +
                               "1 - if you want to delete shelf" + "\n" +
                               "2 - if you want to delete book" + "\n" +
                               "3 - if you want to add shelf" + "\n" +
                               "4 - if you want to add book" + "\n"
            );

            String input = scanner.nextLine();
            int inputInt = 0;
            try
            {
                inputInt = Integer.parseInt(input);
            } catch (NumberFormatException e)
            {
                System.out.println("Not an option" + "\n");
                e.printStackTrace();
            }
            if(inputInt <= 4 && inputInt >= 1)
            {
                switch (input)
                {
                    case "1":
                        System.out.println("Type shelf id that you want to delete: ");
                        input = scanner.nextLine();

                        shelfRepo.delete(shelfRepo.find(UUID.fromString(input)));
                        System.out.println("shelf has been deleted!");
                        break;
                    case "2":
                        System.out.println("Type book id that you want to delete: ");
                        input = scanner.nextLine();

                        bookRepo.delete(bookRepo.find(UUID.fromString(input)));
                        System.out.println("book has been deleted!");
                        break;
                    case "3":
                        BookShelf shelf = new BookShelf();
                        shelfRepo.add(shelf);
                        System.out.println("New shelf has been added!");
                        break;
                    case "4":
                        System.out.println("Type title of the book that you want to add: ");
                        String title = scanner.nextLine();
                        System.out.println("Type author of the book that you want to add: ");
                        String author = scanner.nextLine();
                        System.out.println("Type id of the shelf that you want to put it on: ");
                        String id = scanner.nextLine();

                        Book book = new Book(title, author, shelfRepo.find(UUID.fromString(id)));
                        bookRepo.add(book);
                        System.out.println("New book has been added!");
                        break;
                }
                System.out.println("Shelves after" + "\n" + shelfRepo.findAll() + "\n");
            }
            else if(inputInt == 0)
            {
                break;
            }
            else
            {
                System.out.println("Not an option" + "\n");
            }
        }
        emf.close();
    }
}
