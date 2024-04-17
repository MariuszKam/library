package com.library.repository.book;


import com.library.model.Book;


import java.util.ArrayList;
import java.util.List;

public class BookTestUtility {

    private static List<String> title = List.of("Harry Potter and the Philosopher's Stone", "Harry Potter and the Chamber of Secrets"
            , "Harry Potter and the Prisoner of Azkaban", "Harry Potter and the Goblet of Fire"
            , "Harry Potter and the Order of the Phoenix", "Harry Potter and the Half-Blood Prince",
            "Harry Potter and the Deathly Hallows");
    private static List<Integer> publicationYears = List.of(
            1997, 1998, 1999, 2000, 2003, 2005, 2007
    );
    private static List<String> isbns = List.of(
            "9780590353427",
            "9780439064866",
            "9780439136365",
            "9780439139601",
            "9780439358071",
            "9780439785969",
            "9780545010221"
    );

    public static List<Book> getHPBooks() {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < title.size(); i++) {
            String currentTitle = title.get(i);
            Integer currentPublicationYear = publicationYears.get(i);
            String currentIsbn = isbns.get(i);

            Book book = Book.builder()
                    .title(currentTitle)
                    .publicationYear(currentPublicationYear)
                    .isbn(currentIsbn)
                    .build();

            books.add(book);
        }
        return books;
    }

    public static Book getHPBook() {
        return Book.builder()
                .title(title.getFirst())
                .publicationYear(publicationYears.getFirst())
                .isbn(isbns.getFirst())
                .build();
    }
}
