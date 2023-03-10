package org.example.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.example.lib.Dao;
import org.example.model.LiteraryFormat;
import org.example.util.ConnectionUtil;

@Dao
public class LiteraryFormatDaoImpl implements LiteraryFormatDao {
    @Override
    public List<LiteraryFormat> getAll() {
        List<LiteraryFormat> allFormats = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             Statement getAllFormatsStatement = connection.createStatement()) {
            ResultSet resultSet = getAllFormatsStatement
                    .executeQuery("SELECT * FROM literary_formats WHERE is_deleted = false");
            while (resultSet.next()) {
                String format = resultSet.getString("format");
                Long id = resultSet.getObject("id", Long.class);
                LiteraryFormat literaryFormat = new LiteraryFormat();
                literaryFormat.setId(id);
                literaryFormat.setFormat(format);
                allFormats.add(literaryFormat);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't get all formats from DB", e);
        }
        return allFormats;
    }

    @Override
    public LiteraryFormat create(LiteraryFormat format) {
        String insertFormatRequest = "INSERT INTO literary_formats(format) values(?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement createFormatStatement
                     = connection.prepareStatement(insertFormatRequest, Statement.RETURN_GENERATED_KEYS)) {
            createFormatStatement.setString(1, format.getFormat());
            createFormatStatement.executeUpdate();
            ResultSet generatedKeys = createFormatStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long id = generatedKeys.getObject(1, Long.class);
                format.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't insert format to DB", e);
        }
        return format;
    }

    @Override
    public LiteraryFormat get(Long id) {
        String query = "SELECT format FROM literary_format WHERE is_deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            LiteraryFormat literaryFormat = null;
            if (resultSet.next()) {
                literaryFormat = getFormat(resultSet);
            }
            return Optional.ofNullable(manufacturer);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't found manufacturer in DB", e);
        }
    }


    @Override
    public LiteraryFormat update(LiteraryFormat format) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        String deleteRequest = "UPDATE literary_formats SET is_deleted = true WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement createFormatStatement
                     = connection.prepareStatement(deleteRequest, Statement.RETURN_GENERATED_KEYS)) {
            createFormatStatement.setLong(1, id);
            return createFormatStatement.executeUpdate() >= 1;

        } catch (SQLException e) {
            throw new RuntimeException("Can't insert format to DB", e);
        }
    }
}
