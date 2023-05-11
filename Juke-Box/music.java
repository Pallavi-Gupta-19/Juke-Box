import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
public class music {
    Connection con;
    Statement stm;
    private String SongName;
    private int SongId;
    private String filePath;
    private float duration;
    private String genre;
    private int albumId;
    private String ArtistName;
    private String AlbumName;

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public int getSongId() {
        return SongId;
    }

    public void setSongId(int songId) {
        SongId = songId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getArtistName() {
        return ArtistName;
    }

    public void setArtistName(String artistName) {
        ArtistName = artistName;
    }

    public String getAlbumName() {
        return AlbumName;
    }

    public void setAlbumName(String albumName) {
        AlbumName = albumName;
    }

    public void musics(String songName, int songId, String filePath, float duration, String genre, int albumId, String artistName, String albumName) {
        SongName = songName;
        SongId = songId;
        this.filePath = filePath;
        this.duration = duration;
        this.genre = genre;
        this.albumId = albumId;
        ArtistName = artistName;
        AlbumName = albumName;
    }

    public static ArrayList<music> display() {
        ArrayList<music> songlist = new ArrayList<>();
        Connection con;
        Statement stm;
        try {
            con = UserDatabase.createConnection();
            stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery("select * from songs ");
            while (resultSet.next()) {
                music mobj = new music();

                mobj.setSongName(resultSet.getString(1));
                mobj.setSongId(resultSet.getInt(2));
                mobj.setFilePath(resultSet.getString(3));
                mobj.setDuration(resultSet.getFloat(4));
                mobj.setGenre(resultSet.getString(5));
                mobj.setAlbumId(resultSet.getInt(6));
                mobj.setAlbumName(resultSet.getString(7));
                mobj.setArtistName(resultSet.getString(8));
                songlist.add(mobj);
            }
        } catch (SQLException sq) {
            sq.printStackTrace();
        }

        return songlist;
    }

}

