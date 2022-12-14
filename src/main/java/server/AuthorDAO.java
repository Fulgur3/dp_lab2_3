package server;

import connection.CustomConnection;
import dto.AuthorDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    public static AuthorDTO findById(long id) {
        try (Connection connection = CustomConnection.getConnection()) {
            String sql =
                    "SELECT * FROM author WHERE id = ?";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            AuthorDTO author = null;
            if (resultSet.next()) {
                author = new AuthorDTO();
                author.setId(resultSet.getLong(1));
                author.setName(resultSet.getString(2));
            }
            preparedStatement.close();
            return author;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static AuthorDTO findByName(String name) {
        try (Connection connection = CustomConnection.getConnection()) {
            String sql = "SELECT * FROM author WHERE name = ?";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            AuthorDTO author = null;
            if (resultSet.next()) {
                author = new AuthorDTO();
                author.setId(resultSet.getLong(1));
                author.setName(resultSet.getString(2));
            }
            preparedStatement.close();
            return author;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean update(AuthorDTO country) {
        try (Connection connection = CustomConnection.getConnection()) {
            String sql = "UPDATE author SET name = ? WHERE id = ?";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, country.getName());
            preparedStatement.setLong(2, country.getId());
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insert(AuthorDTO country) {
        try (Connection connection = CustomConnection.getConnection()) {
            String sql = "INSERT INTO author (name) VALUES (?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, country.getName());
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(AuthorDTO country) {
        try (Connection connection = CustomConnection.getConnection()) {
            String sql = "DELETE FROM author WHERE id = ?";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, country.getId());
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<AuthorDTO> findAll() {
        try (Connection connection = CustomConnection.getConnection()) {
            String sql = "SELECT * FROM author";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<AuthorDTO> list = new ArrayList<>();
            while (resultSet.next()) {
                AuthorDTO author = new AuthorDTO();
                author.setId(resultSet.getLong(1));
                author.setName(resultSet.getString(2));
                list.add(author);
            }
            preparedStatement.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}