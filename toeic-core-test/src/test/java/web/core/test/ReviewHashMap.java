package web.core.test;

import org.testng.annotations.Test;
import web.model.Book;

import java.util.HashMap;
import java.util.Map;

public class ReviewHashMap {
    @Test
    public void checkHashMap() {
        Book b1 = returnBookModel(101, "Let us C", "Yashwant Kanetkar", "BPB", 8);
        Book b2 = returnBookModel(102, "Data Communications & Networking", "Forouzan", "Mc Graw Hill", 4);
        Book b3 = returnBookModel(103, "Operating System", "Galvin", "Wiley", 6);
        Book b4 = returnBookModel(104, "Java Web", "Galvin", "Wiley", 10);

        Map<Integer, Book> map = new HashMap<Integer, Book>();
        map.put(1, b1);
        map.put(2, b2);
        map.put(3, b3);
        map.put(3, b4);

        for(Map.Entry<Integer, Book> item: map.entrySet()){
            System.out.println(item.getKey()+" detail:");
            Book book1 =item.getValue();
            System.out.println(book1.getId() +", "+ book1.getName() + ", "+ book1.getAuthor()+ ", " + book1.getPublisher());
        }
    }


    private Book returnBookModel(int id, String name, String author, String publisher, int quantity) {
        Book book1 = new Book();
        book1.setId(id);
        book1.setName(name);
        book1.setAuthor(author);
        book1.setPublisher(publisher);
        book1.setQuantity(quantity);
        return book1;
    }
}
