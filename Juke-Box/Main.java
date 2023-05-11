//import CAPSTONE_PROJECT.*;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.List;
import java.util.*;



public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner sc=new Scanner(System.in);
        AUdioPlayer a=new AUdioPlayer();
        Jukebox jobj=new Jukebox();
        UserDatabase dobj=new UserDatabase();
        music mobj=new music();
        Playlist playlist=new Playlist();
        System.out.println("************************************************************");
        System.out.println("                 WELCOME TO THE JUKEBOX                       ");
        System.out.println("***************************************************************");
        System.out.println();
        System.out.println("'----------->>>>>>> 'YOU CAN CAGE A SINGER NOT A SONG' >>>>>>>>>----- ");
        boolean log= dobj.usersRecord();
        boolean flag= true;
        String name=null;
        while(flag)
        {
        if(log) {
            System.out.println();
            System.out.println("                 CHOOSE THE BELOW OPTION                 ");
            System.out.println();

            System.out.println("  1 : Display Catelogue of songs\n  2 : Search Songs \n  3 : Create playlist\n  4 : View playlist\n  5 : Deleting song from playlist\n  6 : Delete Playlist\n  7 : Add song to Playlist\n  8 : Logout");
            System.out.println();
            System.out.println();
            int choice=0;
            choice=sc.nextInt();
            ArrayList<music> musicList =null;
            boolean flag1=true;

            switch (choice) {

                case 1:
                    musicList = music.display();
                    dobj.displaySongs(musicList);
                    System.out.println();
                    System.out.println("\nDO you wish to play song ??? ");
                    System.out.println("\nPress 1 for play a song\nPress 0 to exit. ");
                    System.out.println();
//                    System.out.println(musicList);
                    if(sc.nextInt() == 1)
                    {
                        System.out.println("Enter the song Id to play song");
                        int son = sc.nextInt();
                        try {
                            a.playMusic(dobj.getfilePath(son));
                            while (flag1 )
                            {
                            System.out.println("Do you want to  shuffle song?Yes or No");
                            String c = sc.next();

                                if (c.equalsIgnoreCase("yes")) {
                                    RandomNum r = () -> (Math.random() * 12) + 100;
                                    double d = r.calculate();
                                    int idd = (int) Math.ceil(d);
                                    System.out.println(idd);
                                    String filepath=dobj.getfilePath(idd);
                                    //song_id = idd;
                                    a.playMusic(filepath);
                                } else {
                                    flag1 = false;
                                }
                        }
                        }catch(SQLException sq)
                        {
                            System.out.println("You have entered  wrong song id  ");
                        }
                    }

                    break;
                case 2:
                    System.out.println("-----------Search song------------");
                    System.out.println("Enter 1: To search by Genre ");
                    System.out.println("Enter 2: To search by Album Name");
                    System.out.println("Enter 3: To search by Artist Name");
                    int response = sc.nextInt();
                    if (response == 1) {
                        System.out.println("Enter the genre ");
                        String gen = sc.next();
                        ArrayList<music> musicByGenre=dobj.searchSongsByGenr(gen);
                        dobj.displaySongs(musicByGenre);
                        System.out.println("Enter song id to play  ");
                        int songid= sc.nextInt();
                        a.playMusic(dobj.getfilePath(songid));

                    } else if (response == 2) {
                        System.out.println("Enter the Album Name ");
                        String albumname = sc.next();
                        ArrayList<music> musicByAlbum=dobj.searchSongsByAlbum(albumname);
                        dobj.displaySongs(musicByAlbum);
                        System.out.println("Enter song id ");
                        int songid= sc.nextInt();
                        a.playMusic(dobj.getfilePath(songid));
                    } else if (response == 3) {
                        System.out.println("Enter the Artist Name");
                        String artistname = sc.next();
                        ArrayList<music> musicByArtist=dobj.searchSongsByArtistName(artistname);
                        dobj.displaySongs(musicByArtist);
                        System.out.println("Enter song id ");
                        int songid= sc.nextInt();
                        a.playMusic(dobj.getfilePath(songid));
                    }
                    break;
                case 3:
                    playlist.createPlaylist( UserDatabase.user);
                    break;
                case 4:
                    try {

                        playlist.viewPlaylist(UserDatabase.user);

                    }
                    catch (Exception e){
                        System.out.println(e);
                    }

                    break;
                case 5:
                    System.out.println("Enter playlist name ");
                    String pnamed= sc.next();
                    System.out.println("Enter song Id");
                    int snd= sc.nextInt();
                    playlist.deleteSongFromPlaylist(snd,pnamed);
                    break;
                case 6:
                    System.out.println("Enter playlist name ");
                    String playlistid= sc.next();
                    playlist.deletePlaylist(playlistid);
                    break;
                case 7:
                    boolean p= playlist.addSongToPlaylist();
                    if(!p)
                    {
                        System.out.println("------- Song has added successfully -------------");
                    }else
                        System.out.println("----------- Addition of song failed  ---------------");

//                    if(!playlist.addSongToPlaylist()) System.out.println("Song Added");
//                    else System.out.println("unable to addd song");
                    break;

                case 8:

                    log = false;
                    flag = false;
                    System.out.println("---------  Feel free to listen your songs again  ---------");
                    break;

                default:
                    System.out.println("Wrong choice entered!!!!!");


            }
        }
    }

}
}
