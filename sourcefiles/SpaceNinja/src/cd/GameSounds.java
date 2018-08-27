package cd;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineListener;

import javafx.scene.media.AudioClip;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public  class GameSounds {
  private static TheGame _g;
  private static BasicPlayer _player = new BasicPlayer();
  public static String _song;
  private static LineListener _ll = new ClipListener("1");
  private static LineListener _ll2= new ClipListener("2");
  private static LineListener _ll3= new ClipListener("3");
  private static LineListener _ll4= new ClipListener("4");
  private static LineListener _ll5= new ClipListener("5");
  private static LineListener _ll6= new ClipListener("6");
  private static LineListener _ll7= new ClipListener("7");
  private static LineListener _ll8= new ClipListener("8");
  private static LineListener _ll9= new ClipListener("9");
  private static LineListener _ll0= new ClipListener("0");
  private static int _llc;
  static Map<String, LinkedClip> clips = new HashMap<String, LinkedClip>();
  
  public GameSounds() {
	  
  }
  
  public BasicPlayer getPlayer() {
	  return _player;
  }
  
  public static void playStageSong(String url) {


		try {
			_player.stop();
			//String pathToMp3 = System.getProperty("user.dir") + url;
			
			_player.open((TheGame.class.getResource(url)));
			 _player.play();
			 
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	 
	}
  public static void playSound(final String url) {
	  
	  // The wrapper thread is unnecessary, unless it blocks on the
	  // Clip finishing; see comments.
	    
	
	       
	   //     AudioInputStream inputStream = AudioSystem.getAudioInputStream(
	    //      TheGame.class.getResource(url));
	     //     Clip clip = AudioSystem.getClip();
	      //    
	    //	   		   
	    //	   clip.open(inputStream);
	    //	   clip.start(); 
	  try{  	  
	  LinkedClip clip = clips.get(url);
	  AudioInputStream inputStream = AudioSystem.getAudioInputStream(
	            TheGame.class.getResource(url));
	    if(clip == null) {
	    	
	        
	        clip = new LinkedClip(AudioSystem.getClip());
	        clip.open(inputStream);
	        clips.put(url, clip);
	    	
	    }  else if(clip.isRunning()){
	        if(clip.getNext() == null) {
	        	clip.setNext(new LinkedClip(AudioSystem.getClip()));
	        	clip = clip.getNext();
	        	clip.open(inputStream);
	        } else if(clip.getNext().isRunning()) {
	        	if(clip.getNext().getNext() == null) {
		        	clip.getNext().setNext(new LinkedClip(AudioSystem.getClip()));
		        	clip = clip.getNext().getNext();
		        	clip.open(inputStream);
		        } else if(clip.getNext().getNext().isRunning()) {
		        	if(clip.getNext().getNext().getNext() == null) {
		        		clip.getNext().getNext().setNext(new LinkedClip(AudioSystem.getClip()));
		        		clip = clip.getNext().getNext().getNext();
		        		clip.open(inputStream);
		        	} else if(clip.getNext().getNext().getNext().isRunning()){
		        		if(clip.getNext().getNext().getNext().getNext() == null) {
		        		clip.getNext().getNext().getNext().setNext(new LinkedClip(AudioSystem.getClip()));
		        		clip = clip.getNext().getNext().getNext().getNext();
		        		clip.open(inputStream);
		        		} else {
		        			clip = clip.getNext().getNext().getNext().getNext();
		        		}
		        	
		        	} else {
		        		clip = clip.getNext().getNext().getNext();
		        	}
		        	
		        } else {
		        	clip = clip.getNext().getNext();
		        }
	        } else {
	        	clip = clip.getNext();
	        	
	        }
	    } else {
	    }
	    
	    	clip.stop();
	    	clip.setFramePosition(0);
	    	clip.start(); 
	    
	  	} catch (Exception e) {
  		e.printStackTrace();
	  	}
	   
	    	   
//	    	   LineListener l;
//	    	   if(_llc == 0) {
//	    		  l = _ll;
//	    		  _llc = 1;
//	    	   } else if(_llc == 1) {
//	    		   l = _ll2;
//	    		   _llc = 2;
//	    	   } else if(_llc == 2) {
//	    		   l = _ll3;
//	    		   _llc = 3;
//	    	   } else if(_llc == 3){
//	    		   l = _ll4;
//	    		   _llc = 4;
//	    	   } else if(_llc == 4) {
//	    		   l = _ll5;
//	    		   _llc = 5;
//	    	   } else if(_llc == 5) {
//	    		   l = _ll6;
//	    		   _llc = 6;
//	    	   } else if(_llc == 6) {
//	    		   l = _ll7;
//	    		   _llc = 7;
//	    	   } else if(_llc == 7) {
//	    		   l = _ll8;
//	    		   _llc = 8;
//	    	   } else if(_llc == 8) {
//	    		   l = _ll9;
//	    		   _llc = 9;
//	    	   } else {
//	    		   l = _ll0;
//	    		   _llc = 0;
//	    	   }
	    	   
	    	   
	    	//   clip.addLineListener(l);
	    	   
	    	   //_clips.add(clip);
	 //   	   clip.addLineListener(new LineListener() {
	 //   		   public void update(LineEvent myLineEvent) {
	 //   			      if (myLineEvent.getType() == LineEvent.Type.STOP)
	 //   			        clip.close();
	 //   			    }
	 //   	   });
	      
	    
	    
	  
	}
  
  	public static void stopPlayer() {
  		try {
			_player.stop();
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	}
  	public static int getPlayerState() {
  		 return _player.getStatus();
  	}
  	public static void pausePlayer() {
  		try {
			_player.pause();
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	}
	public static void resumePlayer() {
  		try {
			_player.resume();
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	}
}
