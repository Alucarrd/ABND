package com.petina.android.audioplayer;

import android.util.Log;

import java.util.ArrayList;

public class DataStore {
    private ArrayList<Album> mAlbums = new ArrayList<Album>();
    private ArrayList<Song> mSongs = new ArrayList<Song>();

    /**
     * constructor of the object will load hardcoded data for now..
     */
    public DataStore() {
        //Hardcoding all the album and songs
        mAlbums.add(new Album(1, "The Greatest Showman: Original Motion Picture Soundtrack"
                , "the_greatest_show_man.jpg"
                , "Various Artists"
                , "Atlantic"));
        mAlbums.add(new Album(2, "Game of Thrones: Season 7 (soundtrack)"
                , "game_of_throne_season_7.jpg"
                , "Ramin Djawadi"
                , "WaterTower Music"));

        mAlbums.add(new Album(3, "Beauty and the Beast: Original Motion Picture Soundtrack"
                , "beauty_and_the_beast.jpg"
                , "Various Artists"
                , "Walt Disney"));
        mAlbums.add(new Album(4, "Wonder Woman: Original Motion Picture Soundtrack"
                , "wonder_woman.jpg"
                , "Various Artists"
                , "WaterTower Music"));

        //Song(int Id, int albumId, String title, String artist, int orderId, String length )
        mSongs.add(new Song(1, 1, "The Greatest Show"
                , "Hugh Jackman, Keala Settle, Zac Efron, Zendaya"
                , 1, "5:02"));
        mSongs.add(new Song(2, 1, "A Million Dreams"
                , "Ziv Zaifman, Jackman, Michelle Williams"
                , 2, "4:29"));

        mSongs.add(new Song(3, 1, "A Million Dreams (Reprise)"
                , "Austyn Johnson, Cameron Seely, Jackman"
                , 3, "1:00"));

        mSongs.add(new Song(4, 1, "Come Alive"
                , "Jackman, Settle, Daniel Everidge, Zendaya"
                , 4, "3:45"));

        mSongs.add(new Song(5, 1, "The Other Side"
                , "Jackman & Efron"
                , 5, "3:34"));

        mSongs.add(new Song(6, 1, ">Never Enough"
                , "Loren Allred"
                , 6, "3:27"));
        mSongs.add(new Song(7, 1, "This Is Me"
                , "Settle"
                , 7, "3:54"));

        mSongs.add(new Song(8, 1, "Rewrite the Stars"
                , "Efron & Zendaya"
                , 8, "3:37"));

        mSongs.add(new Song(9, 1, "Tightrope"
                , "Michelle Williams"
                , 9, "3:54"));

        mSongs.add(new Song(10, 1, "Never Enough (Reprise)"
                , "Loren Allred"
                , 10, "1:20"));

        mSongs.add(new Song(11, 1, "From Now On"
                , "Hugh Jackman"
                , 11, "5:49"));

        mSongs.add(new Song(12, 2, "Main Title"
                , "Ramin Djawadi"
                , 12, "1:53"));

        mSongs.add(new Song(13, 2, "Dragonstone"
                , "Ramin Djawadi"
                , 2, "5:06"));

        mSongs.add(new Song(14, 2, "Shall We Begin?"
                , "Ramin Djawadi"
                , 3, "1:24"));

        mSongs.add(new Song(15, 2, "The Queen's Justice"
                , "Ramin Djawadi"
                , 4, "1:21"));

        mSongs.add(new Song(16, 2, "A Game I Like to Play"
                , "Ramin Djawadi"
                , 5, "1:45"));

        mSongs.add(new Song(17, 2, "I Am the Storm"
                , "Ramin Djawadi"
                , 6, "6:53"));

        mSongs.add(new Song(18, 2, "The Gift"
                , "Ramin Djawadi"
                , 7, "2:03"));

        mSongs.add(new Song(19, 2, "Dragonglass"
                , "Ramin Djawadi"
                , 8, "4:19"));

        mSongs.add(new Song(20, 2, "Spoils of War (Pt. 1)"
                , "Ramin Djawadi"
                , 9, "3:53"));

        mSongs.add(new Song(21, 2, "Spoils of War (Pt. 2)"
                , "Ramin Djawadi"
                , 10, "4:03"));

        mSongs.add(new Song(22, 2, "The Dagger"
                , "Ramin Djawadi"
                , 11, "2:34"));

        mSongs.add(new Song(23, 2, "Home"
                , "Ramin Djawadi"
                , 12, "2:30"));

        mSongs.add(new Song(24, 2, "Gorgeous Beasts"
                , "Ramin Djawadi"
                , 13, "2:11"));

        mSongs.add(new Song(25, 2, "The Long Farewell"
                , "Ramin Djawadi"
                , 14, "2:46"));

        mSongs.add(new Song(26, 2, "Against All Odds"
                , "Ramin Djawadi"
                , 15, "7:48"));

        mSongs.add(new Song(27, 2, "See You for What You Are"
                , "Ramin Djawadi"
                , 16, "2:09"));

        mSongs.add(new Song(28, 2, "Casterly Rock"
                , "Ramin Djawadi"
                , 17, "2:23"));

        mSongs.add(new Song(29, 2, "A Lion's Legacy"
                , "Ramin Djawadi"
                , 18, "1:34"));

        mSongs.add(new Song(30, 2, "Message for Cersei"
                , "Ramin Djawadi"
                , 19, "1:43"));

        mSongs.add(new Song(31, 2, "Ironborn"
                , "Ramin Djawadi"
                , 20, "2:19"));

        mSongs.add(new Song(32, 2, "No One Walks Away from Me"
                , "Ramin Djawadi"
                , 21, "2:11"));

        mSongs.add(new Song(33, 2, "Truth"
                , "Ramin Djawadi"
                , 22, "3:31"));

        mSongs.add(new Song(34, 2, "The Army of the Dead"
                , "Ramin Djawadi"
                , 23, "5:26"));

        mSongs.add(new Song(35, 2, "Winter Is Here"
                , "Ramin Djawadi"
                , 24, "2:54"));

        mSongs.add(new Song(36, 3, "Overture"
                , "Various Artists"
                , 1, "3:05"));

        mSongs.add(new Song(37, 3, "Main Title: Prologue Pt. 1"
                , "Various Artists"
                , 2, "0:42"));

        mSongs.add(new Song(38, 3, "Aria"
                , "Various Artists"
                , 3, "1:02"));

        mSongs.add(new Song(39, 3, "Main Title: Prologue Pt. 2"
                , "Various Artists"
                , 4, "2:21"));

        mSongs.add(new Song(40, 3, "Belle"
                , "Emma Watson, Luke Evans, Ensemble"
                , 5, "5:33"));

        mSongs.add(new Song(41, 3, "How Does a Moment Last Forever (Music Box)"
                , "Kevin Kline"
                , 6, "1:03"));

        mSongs.add(new Song(42, 3, "Belle (Reprise)"
                , "Emma Watson"
                , 7, "1:15"));

        mSongs.add(new Song(43, 3, "Gaston"
                , "Luke Evans, Josh Gad, Ensemble"
                , 8, "4:25"));

        mSongs.add(new Song(44, 3, "Be Our Guest"
                , "Ewan McGregor, Emma Thompson, Gugu Mbatha-Raw and Ian McKellen"
                , 9, "4:48"));

        mSongs.add(new Song(45, 3, "Days in the Sun"
                , "Adam Mitchell, Stanley Tucci, Ewan McGregor, Gugu Mbatha-Raw, Emma Thompson, Audra McDonald, Emma Watson, Ian McKellen, Clive Rowe"
                , 10, "2:40"));

        mSongs.add(new Song(46, 3, "Something There"
                , "Emma Watson, Dan Stevens, Emma Thompson, Nathan Mack, Ian McKellen, Ewan McGregor, Gugu Mbatha-Raw"
                , 11, "2:54"));

        mSongs.add(new Song(47, 3, "How Does a Moment Last Forever (Montmartre)"
                , "Emma Watson"
                , 12, "1:55"));

        mSongs.add(new Song(48, 3, "Beauty and the Beast"
                , "Emma Thompson"
                , 13, "3:19"));

        mSongs.add(new Song(49, 3, "Evermore"
                , "Dan Stevens"
                , 14, "3:14"));

        mSongs.add(new Song(50, 3, "The Mob Song"
                , "Luke Evans, Josh Gad, Ensemble, Emma Watson, Ian McKellen, Stanley Tucci, Nathan Mack, Gugu Mbatha-Raw, Ewan McGregor, Gerard Horan, Haydn Gwynne"
                , 15, "2:28"));

        mSongs.add(new Song(51, 3, "Beauty and the Beast (Finale)"
                , "Audra McDonald, Emma Thompson, Ensemble"
                , 16, "2:14"));

        mSongs.add(new Song(52, 3, "How Does a Moment Last Forever"
                , "Celine Dion"
                , 17, "3:37"));

        mSongs.add(new Song(53, 3, "Beauty and the Beast"
                , "Ariana Grande & John Legend"
                , 18, "3:47"));

        mSongs.add(new Song(54, 3, "Evermore"
                , "Josh Groban"
                , 19, "3:09"));

        mSongs.add(new Song(55, 4, "Amazons of Themyscira"
                , "Rupert Gregson-Williams"
                , 1, "6:47"));

        mSongs.add(new Song(56, 4, "History Lesson"
                , "Rupert Gregson-Williams"
                , 2, "5:16"));

        mSongs.add(new Song(57, 4, "Angel on the Wing"
                , "Rupert Gregson-Williams"
                , 3, "3:45"));

        mSongs.add(new Song(58, 4, "Ludendorff, Enough!"
                , "Rupert Gregson-Williams"
                , 4, "7:37"));

        mSongs.add(new Song(59, 4, "Pain, Loss & Love"
                , "Rupert Gregson-Williams"
                , 5, "5:27"));

        mSongs.add(new Song(60, 4, "No Man's Land"
                , "Rupert Gregson-Williams"
                , 6, "8:52"));

        mSongs.add(new Song(61, 4, "Fausta"
                , "Rupert Gregson-Williams"
                , 7, "3:20"));

        mSongs.add(new Song(62, 4, "Wonder Woman's Wrath"
                , "Rupert Gregson-Williams"
                , 8, "4:06"));

        mSongs.add(new Song(63, 4, "The God of War"
                , "Rupert Gregson-Williams"
                , 9, "8:02"));

        mSongs.add(new Song(64, 4, "We Are All to Blame"
                , "Rupert Gregson-Williams"
                , 10, "3:11"));

        mSongs.add(new Song(65, 4, "Hell Hath No Fury"
                , "Rupert Gregson-Williams"
                , 11, "3:58"));

        mSongs.add(new Song(66, 4, "Lightning Strikes"
                , "Rupert Gregson-Williams"
                , 12, "3:35"));

        mSongs.add(new Song(67, 4, "Trafalgar Celebration"
                , "Rupert Gregson-Williams"
                , 13, "4:50"));

        mSongs.add(new Song(68, 4, "Action Reaction"
                , "Rupert Gregson-Williams"
                , 14, "5:54"));
        mSongs.add(new Song(69, 4, "To Be Human"
                , "Rupert Gregson-Williams"
                , 15, "4:00"));


    }

    /**
     * provides the list of album objects
     *
     * @return array list of album
     */
    public ArrayList<Album> getAlbums() {
        return mAlbums;
    }

    /**
     * Fetch of the songs of the given album Id
     *
     * @param albumId the Id of the album
     * @return array list of songs
     */
    public ArrayList<Song> getSongsByAlbumId(int albumId) {
        ArrayList<Song> songResult = new ArrayList<Song>();
        for (Song mySong : mSongs) {
            if (mySong.getAlbumId() == albumId) {
                songResult.add(mySong);
            }
        }
        return songResult;
    }

    /**
     * Fetch for songs that contains the search phrase in the title
     *
     * @param phrase the string(s) user are searching on
     * @return array list of songs
     */
    public ArrayList<Song> searchSongByPhrase(String phrase) {
        ArrayList<Song> songResult = new ArrayList<Song>();
        for (int i = 0; i < mSongs.size(); i++) {
            if (mSongs.get(i).getSongTitle().toLowerCase().contains(phrase.toLowerCase())) {
                songResult.add(mSongs.get(i));
            }

        }
        return songResult;
    }

    /**
     * Fetch for the album that has the passed in Id
     *
     * @param albumId album Id
     * @return album object
     */
    public Album getAlbumById(int albumId) {
        for (int i = 0; i < mAlbums.size(); i++) {
            if (albumId == mAlbums.get(i).getId())
                return mAlbums.get(i);
        }
        return null;
    }

    /**
     * Fetch for the song that has the passed in Id
     *
     * @param songId song Id
     * @return song object
     */
    public Song getSongById(int songId) {
        for (int i = 0; i < mSongs.size(); i++) {
            if (songId == mSongs.get(i).getSongId())
                return mSongs.get(i);
        }
        return null;
    }

    /**
     * find the max order id (index) in an album.  This is used for navigating songs in the song activitiy
     *
     * @param albumId album Id
     * @return the biggest order id (index) of songs in an album
     */
    public int getMaxOrderId(int albumId) {
        ArrayList<Song> mySongs = getSongsByAlbumId(albumId);
        int orderId = 1;
        for (int i = 0; i < mySongs.size(); i++) {
            if (orderId < mySongs.get(i).getOrderId())
                orderId = mySongs.get(i).getOrderId();
        }
        return orderId;
    }

    /**
     * Fetch for the song with the passed in order id and album id
     *
     * @param orderId order Id
     * @param albumId album Id
     * @return song object
     */
    public Song getSongByOrderAndAlbumId(int orderId, int albumId) {
        ArrayList<Song> mySongs = getSongsByAlbumId(albumId);
        for (int i = 0; i < mySongs.size(); i++) {
            if (orderId == mySongs.get(i).getOrderId())
                return mySongs.get(i);
        }
        return null;
    }


}
