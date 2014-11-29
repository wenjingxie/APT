import junit.framework.*;
import java.util.ArrayList;
import static org.easymock.EasyMock.*;

public class TestMp3PlayerEasyMock extends TestCase {

  protected Mp3Player mp3;
  protected ArrayList list = new ArrayList();
  protected Mp3Player mp3_control;

  public void setUp() {
    // Create a control handle to the Mock object
    mp3_control = createMock(Mp3Player.class);

    // And create the mock object itself
    mp3 = mp3_control;

    list = new ArrayList();
    list.add("Bill Chase -- Open Up Wide");
    list.add("Jethro Tull -- Locomotive Breath");
    list.add("The Boomtown Rats -- Monday");
    list.add("Carl Orff -- O Fortuna");
  }

  public void testPlay() {
 
   
    mp3.loadSongs(list);
    expectLastCall();
    expect(mp3.isPlaying()).andReturn(false);
    mp3.play();
    expectLastCall();
    expect(mp3.isPlaying()).andReturn(true);
    expect(mp3.currentPosition()).andReturn(1.0).times(2);
    mp3.pause();
    expectLastCall();
    mp3.stop();
    expectLastCall();
    expect(mp3.currentPosition()).andReturn(0.0);
    replay(mp3_control);

    mp3.loadSongs(list);
    assertFalse(mp3.isPlaying());
    mp3.play();
    assertTrue(mp3.isPlaying());
    assertTrue(mp3.currentPosition() != 0.0);
    mp3.pause();
    assertTrue(mp3.currentPosition() != 0.0);
    mp3.stop();
    assertEquals(mp3.currentPosition(), 0.0, 0.1);
  }

  public void testPlayNoList() {

  	expect(mp3.isPlaying()).andReturn(false).times(4);
    mp3.play();
    expectLastCall();
    expect(mp3.currentPosition()).andReturn(0.0).times(3);
    mp3.pause();
    expectLastCall();
    mp3.stop();
    expectLastCall();
    replay(mp3_control);

    // Don't set the list up
    assertFalse(mp3.isPlaying());
    mp3.play();
    assertFalse(mp3.isPlaying());
    assertEquals(mp3.currentPosition(), 0.0, 0.1);
    mp3.pause();
    assertEquals(mp3.currentPosition(), 0.0, 0.1);
    assertFalse(mp3.isPlaying());
    mp3.stop();
    assertEquals(mp3.currentPosition(), 0.0, 0.1);
    assertFalse(mp3.isPlaying());
  }

  public void testAdvance() {
    mp3.loadSongs(list);
    expectLastCall();
    mp3.play();
    expectLastCall();
    mp3.prev();
    expectLastCall().times(2);
    mp3.next();
    expectLastCall().times(5);
    expect(mp3.isPlaying()).andReturn(true).times(3);
    expect(mp3.currentSong()).andReturn("Bill Chase -- Open Up Wide");
    expect(mp3.currentSong()).andReturn("Jethro Tull -- Locomotive Breath");
    expect(mp3.currentSong()).andReturn("The Boomtown Rats -- Monday");
    expect(mp3.currentSong()).andReturn("Jethro Tull -- Locomotive Breath");
    expect(mp3.currentSong()).andReturn("The Boomtown Rats -- Monday");
    expect(mp3.currentSong()).andReturn("Carl Orff -- O Fortuna").times(2);    
    replay(mp3_control);






    mp3.loadSongs(list);

    mp3.play();

    assertTrue(mp3.isPlaying());

    mp3.prev();
    assertEquals(mp3.currentSong(), list.get(0));
    assertTrue(mp3.isPlaying());

    mp3.next();
    assertEquals(mp3.currentSong(), list.get(1));
    mp3.next();
    assertEquals(mp3.currentSong(), list.get(2));
    mp3.prev();

    assertEquals(mp3.currentSong(), list.get(1));
    mp3.next();
    assertEquals(mp3.currentSong(), list.get(2));
    mp3.next();
    assertEquals(mp3.currentSong(), list.get(3));
    mp3.next();
    assertEquals(mp3.currentSong(), list.get(3));
    assertTrue(mp3.isPlaying());
  }

}
