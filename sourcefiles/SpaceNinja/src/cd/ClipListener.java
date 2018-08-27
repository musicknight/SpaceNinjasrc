package cd;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class ClipListener implements LineListener{

	String _id;
	
	public ClipListener(String s) {
		_id = s;
	}
	
	@Override
	public void update(LineEvent event) {
		 if (event.getType() == LineEvent.Type.STOP){
			       Clip c = (Clip) event.getSource();
			       c.close();
			       c.removeLineListener(this);
		 }
	}
	
}
