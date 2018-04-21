package cd.bosses;

import java.util.ArrayList;
import java.util.List;

import cd.Backdrop;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class TootBoss2 extends Boss{
	
	private List<Image> _balls = new ArrayList<Image>();
	private int _text1 = 1;
	private Backdrop _flash = new Backdrop(new Image("ultimoboss/flash.png"), 0, 0, 900, 600);
	private int _rate2 = 1;
	private boolean _intsongstarted;
	
	public TootBoss2() {
		super(900, 600, "tootboss2");
		_width = 100;
		_height = 100;
		_staticimage = new Image("tootboss2/1.png");
		
		if(TheGame._beatultimo.equals("t")) {
			_text1 = 16;
			TheGame.setText(new Image("tootboss2/text/intro15.png"));
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		if(!_balls.isEmpty()) {
			Image bd = _balls.get(_spriteindex);
			if(_counter % _rate2  == 0) {
				if(_spriteindex < _balls.size() -1) {
					_spriteindex++;
				} else {
					_spriteindex = 0;
				}
			}
			TheGame._gc.drawImage(bd, _x - 112, _y-112, 375, 375);
		}
		super.render(gc);
		if(_spawning) {
			executeSpawn();
		}
	}
	
	@Override
	public void spawn() {
		_spawning = true;
		_counter2 = 0;
		_y = 125;
		_xvelocity = -5;
		if(TheGame._beatultimo.equals("t")) {
			_staticimage = null;
			_sprites.add(new Image("tootboss2/spin/1.png"));
			_sprites.add(new Image("tootboss2/spin/2.png"));
			_sprites.add(new Image("tootboss2/spin/3.png"));
			_sprites.add(new Image("tootboss2/spin/4.png"));
			_sprites.add(new Image("tootboss2/spin/5.png"));
			_sprites.add(new Image("tootboss2/spin/6.png"));
			_sprites.add(new Image("tootboss2/spin/7.png"));
			_sprites.add(new Image("tootboss2/spin/8.png"));
			_sprites.add(new Image("tootboss2/spin/9.png"));
			_sprites.add(new Image("tootboss2/spin/10.png"));
			_sprites.add(new Image("tootboss2/spin/11.png"));
			_sprites.add(new Image("tootboss2/spin/12.png"));
			_sprites.add(new Image("tootboss2/spin/13.png"));
			_sprites.add(new Image("tootboss2/spin/14.png"));
			_sprites.add(new Image("tootboss2/spin/15.png"));
			_sprites.add(new Image("tootboss2/spin/16.png"));
			_sprites.add(new Image("tootboss2/spin/17.png"));
			_sprites.add(new Image("tootboss2/spin/18.png"));
			_sprites.add(new Image("tootboss2/spin/19.png"));
			_sprites.add(new Image("tootboss2/spin/20.png"));
			_rate = 1;
		}
		
		
	}
	
	public void executeSpawn() {
		if(_counter2 == 50) {
			_xvelocity = 0;
			if(TheGame._beatultimo.equals("t") && _rate == 1) {
				_counter2 = 270;
			}
			TheGame._beatultimo = "t";
			TheGame.writeData();
			if(!_intsongstarted && _rate != 1){
			try {
				TheGame._player.stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TheGame.playStageSong("/songs/tootcreepy.mp3");
			_intsongstarted = true;
			}
		}
		if(_counter2 == 240) {
			TheGame.stopText();
		}
		if(_counter2 == 260 && _text1 < 16) {
			TheGame.setText(new Image("tootboss2/text/intro" +  _text1 + ".png"));
			_text1++;
			_counter2 = 1;
		}
		if(_counter2 == 259 && _text1 == 15) {
			_staticimage = null;
			_sprites.add(new Image("tootboss2/spin/1.png"));
			_sprites.add(new Image("tootboss2/spin/2.png"));
			_sprites.add(new Image("tootboss2/spin/3.png"));
			_sprites.add(new Image("tootboss2/spin/4.png"));
			_sprites.add(new Image("tootboss2/spin/5.png"));
			_sprites.add(new Image("tootboss2/spin/6.png"));
			_sprites.add(new Image("tootboss2/spin/7.png"));
			_sprites.add(new Image("tootboss2/spin/8.png"));
			_sprites.add(new Image("tootboss2/spin/9.png"));
			_sprites.add(new Image("tootboss2/spin/10.png"));
			_sprites.add(new Image("tootboss2/spin/11.png"));
			_sprites.add(new Image("tootboss2/spin/12.png"));
			_sprites.add(new Image("tootboss2/spin/13.png"));
			_sprites.add(new Image("tootboss2/spin/14.png"));
			_sprites.add(new Image("tootboss2/spin/15.png"));
			_sprites.add(new Image("tootboss2/spin/16.png"));
			_sprites.add(new Image("tootboss2/spin/17.png"));
			_sprites.add(new Image("tootboss2/spin/18.png"));
			_sprites.add(new Image("tootboss2/spin/19.png"));
			_sprites.add(new Image("tootboss2/spin/20.png"));
			_rate = 1;
		}
		if(_counter2 == 280) {
			TheGame._frontdrops.add(_flash);
		}
		if(_counter2 == 283) {
			_width = 150;
			_height = 150;
			_sprites.clear();
			_staticimage = new Image("tootboss2/1.png");
			_balls.add(new Image("tootboss2/balls/1.png"));
			_balls.add(new Image("tootboss2/balls/2.png"));
			_balls.add(new Image("tootboss2/balls/3.png"));
			_balls.add(new Image("tootboss2/balls/4.png"));
			_balls.add(new Image("tootboss2/balls/5.png"));
			_balls.add(new Image("tootboss2/balls/6.png"));
			_balls.add(new Image("tootboss2/balls/7.png"));
			_balls.add(new Image("tootboss2/balls/8.png"));
			_balls.add(new Image("tootboss2/balls/9.png"));
			_balls.add(new Image("tootboss2/balls/10.png"));
			_balls.add(new Image("tootboss2/balls/11.png"));
			_balls.add(new Image("tootboss2/balls/12.png"));
			_balls.add(new Image("tootboss2/balls/13.png"));
			_balls.add(new Image("tootboss2/balls/14.png"));
			_balls.add(new Image("tootboss2/balls/15.png"));
			_balls.add(new Image("tootboss2/balls/16.png"));
			_balls.add(new Image("tootboss2/balls/17.png"));
			_balls.add(new Image("tootboss2/balls/18.png"));
			_balls.add(new Image("tootboss2/balls/19.png"));
			_balls.add(new Image("tootboss2/balls/20.png"));
		}
		if(_counter2 == 285) {
			TheGame._frontdrops.remove(_flash);
			_health = 1000;
			_spawning = false;
			TheGame._character1.setImmune(false);
			TheGame._character1.setCanAct(true);
		}
	}

}
