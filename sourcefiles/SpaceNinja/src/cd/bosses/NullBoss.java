package cd.bosses;

import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class NullBoss extends Boss {

	private Button _reset = new Button("reset");
	
	public NullBoss() {
		super(900, 600, "null");
		
	}
	
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(_counter4 == 200) {
			TheGame.setText(new Image("end/1.png"));
		}
		if(_counter4 == 440) {
			TheGame.setText(new Image("end/2.png"));
		}
		if(_counter4 == 680) {
			TheGame.setText(new Image("end/3.png"));
			TheGame.addReset();
		}
	}

	@Override
	public void spawn() {
		// TODO Auto-generated method stub
		
	}

}
