package org.example;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.example.dao.BookDao;
import org.example.dao.BookDaoImpl;
import org.example.dao.LiteraryFormatDao;
import org.example.dao.LiteraryFormatDaoImpl;
import org.example.model.Book;
import org.example.util.ConnectionUtil;

public class Main {

    public static void main(String[] args) {
//        LiteraryFormatDao literaryFormatDao = new LiteraryFormatDaoImpl();
//        literaryFormatDao.getAll().forEach(System.out::println);
//
//        BookDao bookDao = new BookDaoImpl();
//        Book book = new Book();
//        book.setTitle("kobzar");
//        book.setPrice(BigDecimal.valueOf(100));
//        book.setFormat(literaryFormatDao.get(3L));
//        bookDao.create(book);

        Connection connection = ConnectionUtil.getConnection();
        Statement getFormatStatement = null;
        try {
            getFormatStatement = connection.createStatement();
            getFormatStatement.executeQuery("SELECT format FROM literary_formats");
        } catch (SQLException e) {
            throw new RuntimeException("Can`t get format from db", e);

        }

    }
}