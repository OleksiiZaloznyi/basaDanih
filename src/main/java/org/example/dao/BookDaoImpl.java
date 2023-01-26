package org.example.dao;

import java.sql.*;
import org.example.model.Book;
import org.example.util.ConnectionUtil;

public class BookDaoImpl implements BookDao {
    @Override
    public Book create(Book book) {
        String insertRequest = "INSERT INTO books (title, price, literary_format_id) VALUES (?, ?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement createBookStatement
                     = connection.prepareStatement(insertRequest, Statement.RETURN_GENERATED_KEYS)) {

            createBookStatement.setString(1, book.getTitle());
            createBookStatement.setBigDecimal(2, book.getPrice());
            createBookStatement.setLong(3, book.getFormat().getId());
            createBookStatement.executeUpdate();
            ResultSet generatedKeys = createBookStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long id = generatedKeys.getObject(1, Long.class);
                book.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't insert book to DB", e);
        }
        return book;
    }
}
