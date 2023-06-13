package org.example.dao.classes.db.jdbc;

import org.example.core.dto.ArtistDTO;
import org.example.dao.api.IArtistDao;
import org.example.dao.classes.db.ds.DatabaseConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistJdbcDao implements IArtistDao {

    private final String GET_ARTISTS = "SELECT id, name FROM app.artists;";
    private final String GET_ARTIST_BY_ID = "SELECT id, name FROM app.artists WHERE id = ?;";
    private final String INSERT_ARTIST = "INSERT INTO app.artists(name) VALUES(?) RETURNING id;";

    @Override
    public List<ArtistDTO> get() {
        ArrayList<ArtistDTO> artistsList = new ArrayList<>();
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
                ArtistDTO artistDTO = new ArtistDTO(id, name);
                artistsList.add(artistDTO);
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
        return artistsList;
    }

    @Override
    public ArtistDTO get(long id) {
        ArtistDTO artistDTO = null;
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
                artistDTO = new ArtistDTO(userId, name);
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
        return artistDTO;
    }

    @Override
    public ArtistDTO save(ArtistDTO item) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = DatabaseConnectionFactory.getConnection();
            statement = con.prepareStatement(INSERT_ARTIST);
            statement.setString(1, item.getName());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                item.setId(resultSet.getLong("id"));
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
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return item;
    }
}
