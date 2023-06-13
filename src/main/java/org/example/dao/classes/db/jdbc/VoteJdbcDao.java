package org.example.dao.classes.db.jdbc;

import org.example.core.dto.VoteDTO;
import org.example.dao.api.IVoteDao;
import org.example.dao.classes.db.ds.DatabaseConnectionFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class VoteJdbcDao implements IVoteDao {
    private final String GET_VOTES = """
            SELECT v.id, va.id_artist, vg.id_genre, v.about, v.date\s
            FROM app.votes v\s
            JOIN app.votes_artists va ON v.id = va.id_vote
            JOIN app.votes_genres vg ON v.id = vg.id_vote;""";
    private final String GET_VOTE_BY_ID = """
            SELECT v.id, va.id_artist, vg.id_genre, v.about, v.date\s
            FROM app.votes v\s
            JOIN app.votes_artists va ON v.id = va.id_vote
            JOIN app.votes_genres vg ON v.id = vg.id_vote
            WHERE v.id = ?;""";
    private final String INSERT_VOTE = "INSERT INTO app.votes(about, date) VALUES(?, ?) RETURNING id;";
    private final String INSERT_VOTED_ARTIST = "INSERT INTO app.votes_artists(id_vote, id_artist) VALUES(?, ?);";
    private final String INSERT_VOTED_GENRES = "INSERT INTO app.votes_genres(id_vote, id_genre) VALUES(?, ?);";
    @Override
    public List<VoteDTO> get() {
        ArrayList<VoteDTO> votesList = new ArrayList<>();
        try(Connection connection = DatabaseConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_VOTES);
            ResultSet resSet = statement.executeQuery()){

            while (resSet.next()){
                long id = resSet.getLong("id");
                long artistId = resSet.getLong("id_artist");
                long genreId = resSet.getLong("id_genre");
                String about = resSet.getString("about");
                Timestamp timestamp = resSet.getTimestamp("date");
                LocalDateTime date = timestamp.toLocalDateTime();

                VoteDTO voteDTO = votesList.stream().filter(v -> v.getId() == id).findFirst().orElseGet(() -> {
                    VoteDTO newVoteDTO = new VoteDTO(id, artistId,Set.of(genreId), about, date);
                    votesList.add(newVoteDTO);
                    return newVoteDTO;
                });

                Set<Long> genresList = voteDTO.getGenres() != null ? new HashSet<>(voteDTO.getGenres()) : new HashSet<>();
                genresList.add(genreId);
                voteDTO.setGenres(genresList);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error with get votesList " + e.getMessage(),e);
        }
        return votesList;
    }

    @Override
    public VoteDTO get(long id) {
        VoteDTO voteDTO = null;
        try(Connection connection = DatabaseConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_VOTE_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resSet = statement.executeQuery()) {
                while (resSet.next()){
                    long voteId = resSet.getLong("id");
                    long artistId = resSet.getLong("id_artist");
                    long genreId = resSet.getLong("id_genre");
                    String about = resSet.getString("about");
                    Timestamp timestamp = resSet.getTimestamp("date");
                    LocalDateTime date = timestamp.toLocalDateTime();

                    if(voteDTO == null){
                        voteDTO = new VoteDTO(voteId, artistId, Set.of(genreId), about, date);
                    }

                    Set<Long> genresList = voteDTO.getGenres() != null ? new HashSet<>(voteDTO.getGenres()) : new HashSet<>();
                    genresList.add(genreId);
                    voteDTO.setGenres(genresList);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error with get voteDTO: " + e.getMessage(), e);
        }
        return voteDTO;
    }


    @Override
    public VoteDTO save(VoteDTO item) {
        try(Connection connection = DatabaseConnectionFactory.getConnection()) {
            connection.setAutoCommit(false);
            try(PreparedStatement voteStatement = connection.prepareStatement(INSERT_VOTE);
                PreparedStatement genreStatement = connection.prepareStatement(INSERT_VOTED_GENRES);
                PreparedStatement artistStatement = connection.prepareStatement(INSERT_VOTED_ARTIST)) {
                voteStatement.setString(1, item.getAbout());
                voteStatement.setTimestamp(2, Timestamp.valueOf(item.getDate()));
                long voteId;
                try (ResultSet resultSet = voteStatement.executeQuery()) {
                    if (resultSet.next()) {
                        voteId = resultSet.getLong(1);
                        item.setId(voteId);
                    } else {
                        throw new SQLException("Creating vote failed, no ID obtained.");
                    }
                }
                for (Long genreId : item.getGenres()) {
                    genreStatement.setLong(1, voteId);
                    genreStatement.setLong(2, genreId);
                    genreStatement.addBatch();
                }
                genreStatement.executeBatch();

                artistStatement.setLong(1,voteId);
                artistStatement.setLong(2,item.getArtist());
                artistStatement.executeUpdate();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException("Error while saving vote: " + e.getMessage(), e);
            }
            finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error: " + e.getMessage(), e);
        }
        return item;
    }
}
