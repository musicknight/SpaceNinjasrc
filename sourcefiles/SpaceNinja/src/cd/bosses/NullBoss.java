package cd.bosses;

import cd.Backdrop;
import cd.Hitbox;
import cd.HitboxImpl;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import cd.GameSounds;
public class NullBoss extends Boss {

	private Button _reset = new Button("reset");
	private Backdrop _1 = new Backdrop(new Image("text/credits/1.png"), 900, 60, 900, 382);
	private Backdrop _2 = new Backdrop(new Image("text/credits/2.png"), 900, 60, 900, 382);
	private Backdrop _3 = new Backdrop(new Image("text/credits/3.png"), 900, 60, 900, 382);
	private Backdrop _4 = new Backdrop(new Image("text/credits/4.png"), 900, 60, 900, 382);
	private Backdrop _5 = new Backdrop(new Image("text/credits/5.png"), 900, 60, 900, 382);
	private Backdrop _bd;
	
	public NullBoss() {
		super(900, 600, "null");
		TheGame._character1.setImmune(true);
	}
	
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(_bd != null) {
			if(!_bd.equals(_5) || _bd.getX()!= 0)
			_bd.setX(_bd.getX()-6);
		}
		if(_counter4 == 200) {
			TheGame.setText(new Image("end/1.png"));
		}
		if(_counter4 == 440) {
			TheGame.setText(new Image("end/2.png"));
			TheGame._character1.setImmune(true);
		}
		
		if(_counter4 == 680) {
			TheGame.stopText();
			GameSounds.playStageSong("/songs/theme2.mp3");
			TheGame._backdrops.add(_1);
			_bd = _1;
					
		}
		if(_counter4 == 1000) {
			TheGame._backdrops.clear();
			TheGame._backdrops.add(_2);
			_bd = _2;
		}
		if(_counter4 == 1320) {
			TheGame._backdrops.clear();
			TheGame._backdrops.add(_3);
			_bd = _3;
		}
		if(_counter4 == 1640) {
			TheGame._backdrops.clear();
			TheGame._backdrops.add(_4);
			_bd = _4;
		}
		if(_counter4 == 1960) {
			TheGame._backdrops.clear();
			TheGame._backdrops.add(_5);
			_bd = _5;
		}
		if(_counter4 == 2110) {
			TheGame.setText(new Image("end/4.png"));
			
		}
		if(_counter4 == 2350) {
			TheGame.stopText();
		}
		if(_counter4 == 2370) {
			TheGame.setText(new Image("end/3.png"));
			TheGame.addReset();
			
		}
		
	}

	@Override
	public void spawn() {
		
		
	}

}
