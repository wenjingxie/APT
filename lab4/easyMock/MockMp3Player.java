import java.util.ArrayList;

public class MockMp3Player implements Mp3Player {

  private ArrayList LIST = new ArrayList ();
  private boolean IS_PLAY;
  private double POS;
  private int CUR_SONG;

  public MockMp3Player() {
  	CUR_SONG = 0;
  	POS = 0;
  	IS_PLAY = false;
  	LIST = new ArrayList ();
  }

	 /** 
   * Begin playing the filename at the top of the
   * play list, or do nothing if playlist 
   * is empty. 
   */
  public void play() {
  	if (LIST.size() - 1 >= CUR_SONG) {
  		IS_PLAY = true;
  		POS = 1;
  	}
  }

  /** 
   * Pause playing. Play will resume at this spot. 
   */
  public void pause() {
  	IS_PLAY = false;
  }

  /** 
   * Stop playing. The current song remains at the
   * top of the playlist, but rewinds to the 
   * beginning of the song.
   */
  public void stop() {
  	POS = 0;
  }
  
  /** Returns the number of seconds into 
   * the current song.
   */
  public double currentPosition() {
  	return POS;
  }


  /**
   * Returns the currently playing file name.
   */
  public String currentSong() {
  	Object song = LIST.get(CUR_SONG);
  	return song.toString();
  }

  /** 
   * Advance to the next song in the playlist 
   * and begin playing it.
   */
  public void next() {
  	CUR_SONG += 1;
  	if (CUR_SONG == LIST.size()) {
  		CUR_SONG = LIST.size() - 1;
  	}
  	POS = 0;
  	play();
  }

  /**
   * Go back to the previous song in the playlist
   * and begin playing it.
   */
  public void prev() {
  	CUR_SONG -= 1;
  	if (CUR_SONG < 0) {
  		CUR_SONG = 0;
  	}
  	POS = 1;
  	play();
  }

  /** 
   * Returns true if a song is currently 
   * being played.
   */
  public boolean isPlaying() {
  	return IS_PLAY;
  }

  /**
   * Load filenames into the playlist.
   */
  public void loadSongs(ArrayList names) {
  	LIST = names;
  }


}