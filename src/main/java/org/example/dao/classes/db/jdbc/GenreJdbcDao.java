package org.example.dao.classes.db.jdbc;

import org.example.core.dto.GenreDTO;
import org.example.dao.api.IGenreDao;
import org.example.dao.classes.db.ds.DatabaseConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreJdbcDao implements IGenreDao {
    private final String GET_ARTISTS = "SELECT id, name FROM app.genres;";
    private final String GET_ARTIST_BY_ID = "SELECT id, name FROM app.genres WHERE id = ?;";
    private final String INSERT_ARTIST = "INSERT INTO app.genres(name) VALUES(?);";

    @Override
    public List<GenreDTO> get() {
        ArrayList<GenreDTO> genreDTOS = new ArrayList<>();
        Connection con = null;
        Statement statement = null;
        ResultSet resSet = null;

        try {
            con = DatabaseConnectionFactory.getConnection();
            statement = con.createStatement();
            resSet = statement.executeQuery(GET_ARTISTS);
            while (resSet.next()){
                long id = resSet.getLong("id");
                String name = resSet.getString("name");
                GenreDTO genreDTO = new GenreDTO(id, name);
                genreDTOS.add(genreDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(con != null){
                    con.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(resSet != null){
                    resSet.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return genreDTOS;
    }

    @Override
    public GenreDTO get(long id) {
        GenreDTO genreDTO = null;
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resSet = null;

        try {
            con = DatabaseConnectionFactory.getConnection();
            statement = con.prepareStatement(GET_ARTIST_BY_ID);
            statement.setLong(1, id);
            resSet = statement.executeQuery();
            while (resSet.next()){
                long userId = resSet.getLong("id");
                String name = resSet.getString("name");
                genreDTO = new GenreDTO(userId, name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(con != null){
                    con.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(resSet != null){
                    resSet.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return genreDTO;
    }

    @Override
    public GenreDTO save(GenreDTO item) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = DatabaseConnectionFactory.getConnection();
            statement = con.prepareStatement(INSERT_ARTIST);
            statement.setString(1, item.getName());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(con != null){
                    con.close();
                }
                if(statement != null){
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return item;
    }
}
