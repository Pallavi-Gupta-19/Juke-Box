import com.mysql.cj.protocol.Resultset;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Jukebox {
    UserDatabase userDatabase = new UserDatabase();
    Scanner sc = new Scanner(System.in);

    public static void displaySearchSongs(ResultSet rs) throws SQLException {
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.format("%-20s%-30s%-20s%-30s%-20s", "Song Id", "Song Name", "Genre", "Artist Name ", "Album Name");
        System.out.println("------------------------------------------------------------------------------------------------");

        while (rs.next()) {
            System.out.format("%-20s%-30s%-20s%-30s%-20s", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            System.out.println();
        }


    }

}