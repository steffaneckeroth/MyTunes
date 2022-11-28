package src.DAL.db;

import src.BE.Song;
import src.DAL.ISongDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO_DB implements ISongDataAccess {

    public class BaseDao {
        public static Connection getCon(){
            Connection con = null;
            try {
                con = DriverManager.getConnection("jdbc:ucanaccess:Movie.accdb");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return con;
        }

        public static void closeCon(Connection con){
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private MyDatabaseConnector databaseConnector;

    public SongDAO_DB() {
        databaseConnector = new MyDatabaseConnector();
    }

    public List<Song> getAllSongs() throws Exception {

        ArrayList<Song> allsongs = new ArrayList<>();

        try(Connection conn = databaseConnector.getConnection()){
            String sql = "SELECT * FROM Song;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // Loop through rows from the database result set

            while (rs.next()){
                // Map DB row to Movie object
                int id = rs.getInt("Id");
                String title = rs.getString("title");
                int year = rs.getInt("year");

                Song movie = new Song(id, year, title);
                allsongs.add(movie);
            }
            return allsongs;
        } catch(SQLException ex){
            ex.printStackTrace();
            throw new Exception("could not get all movies from data base", ex);


        }

    }

    public Song createSong(String title, int year) throws Exception {

        String sql = "INSERT INTO Song(Title, Year) VALUES (?,?);";

        try(Connection conn = databaseConnector.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // bind parameters does not drive 0 based operations, so you start from 1
            stmt.setString(1, title);
            stmt.setInt(2, year);

            // run the specified sql statement
            stmt.executeUpdate();

            //Get the genrated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;
            if (rs.next()){
                id = rs.getInt(1);
            }
            //Create movie object and send up the layers to gui to show in the list
            Song song = new Song(id, year, title);
            return song;
        } catch(SQLException ex) {
            ex.printStackTrace();
            throw new Exception("could not create movie", ex);
        }

    }

    public void updateSong(Song song) throws Exception {

        try(Connection conn = databaseConnector.getConnection()){

            String sql =  "UPDATE Song SET Title = ?, Year = ? WHERE Id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            // Bind parameters
            stmt.setString(1, song.getTitle());
            stmt.setInt(2, song.getYear());
            stmt.setInt(3, song.getId());

            stmt.executeUpdate();


        }
        catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("could not update song", ex);

        }
        //UPDATE Movie SET Title = '2. dog', Year = 2019
        //WHERE Id = 1


    }

    public Song deleteMovie(Song movie) throws Exception {
        String sql = "DELETE FROM Song  WHERE Id=?";

        try(Connection conn = databaseConnector.getConnection();){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, song.getId());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }
        } catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("could not delete movie", ex);
        }
        return song;
    }

    public List<Song> searchSongs(String query) throws Exception {
        throw new UnsupportedOperationException();
    }

}