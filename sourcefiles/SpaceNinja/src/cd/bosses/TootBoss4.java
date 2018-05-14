package cd.bosses;

import java.util.ArrayList;
import java.util.List;

import cd.AnimatedHitbox;
import cd.Backdrop;
import cd.Hitbox;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class TootBoss4 extends Boss {

	private boolean _hurt;
	private boolean _move;
	private List<Image> _spin = new ArrayList<Image>();
	private boolean _changed;
	private boolean _changed2;
	private boolean _changed3;
	private boolean _changed4;
	private boolean _changed5;
	private boolean _flash;
	
	public TootBoss4() {
		super(-10, 75, "tootboss4");
		_width = 150;
		_height = 150;
		_health = -1300;
		_staticimage = new Image("tootboss2/4.png");
		_spin.add(new Image("tootboss2/spin/1.png"));
		_spin.add(new Image("tootboss2/spin/2.png"));
		_spin.add(new Image("tootboss2/spin/3.png"));
		_spin.add(new Image("tootboss2/spin/4.png"));
		_spin.add(new Image("tootboss2/spin/5.png"));
		_spin.add(new Image("tootboss2/spin/6.png"));
		_spin.add(new Image("tootboss2/spin/7.png"));
		_spin.add(new Image("tootboss2/spin/8.png"));
		_spin.add(new Image("tootboss2/spin/9.png"));
		_spin.add(new Image("tootboss2/spin/10.png"));
		_spin.add(new Image("tootboss2/spin/11.png"));
		_spin.add(new Image("tootboss2/spin/12.png"));
		_spin.add(new Image("tootboss2/spin/13.png"));
		_spin.add(new Image("tootboss2/spin/14.png"));
		_spin.add(new Image("tootboss2/spin/15.png"));
		_spin.add(new Image("tootboss2/spin/16.png"));
		_spin.add(new Image("tootboss2/spin/17.png"));
		_spin.add(new Image("tootboss2/spin/18.png"));
		_spin.add(new Image("tootboss2/spin/19.png"));
		_spin.add(new Image("tootboss2/spin/20.png"));
		if(TheGame._character1.getSkin().equals("ult")) {
			TheGame.setText(new Image("tootboss3/text/2/1.png"));
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(!_hurt) {
			if(_counter3 % 5 == 0) {
				Hitbox a = new AnimatedHitbox("smile", this, false, 899, 250, 75, 75, -20, 0, 0, 1, _spin, 2);
				a.setCircle(true);
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
				Hitbox b = new AnimatedHitbox("smile", this, false, 150, 60, 75, 75, 0, 20, 0, 1, _spin, 2);
				b.setCircle(true);
				b.setDissappearOnHit(false);
				TheGame._attacks.add(b);
			}
		}
		if(_move) {
			executeMove();
		}
		if(_flash) {
			executeFlash();
		}
	}
	
	@Override
	public void spawn() {
		
		
	}
	
	@Override
	public void hit(Hitbox h) {
		if(!_hurt) {
			_hurt = true;
			move();
		}
		if(!_spawning){
			_health -= h.getDamage();
		}
		
	}
	
	public void move() {
		_counter4 = 0;
		_move = true;
	}
	
	public void executeMove() {
		if(_counter4 == 1) {
			_xvelocity = 20;
		}
		if(_counter4 == 20) {
			_xvelocity = 0;
			_yvelocity = 20;
		}
		if(_counter4 == 26) {
			_yvelocity = 0;
			TheGame.setText(new Image("tootboss3/text/2/2.png"));
		}
		if(_health < -7000 && _health >= -10000) {
			TheGame.setText(new Image("tootboss3/text/2/3.png"));
		}
		if(_health < -10000 && _health >= -13000) {
			TheGame.setText(new Image("tootboss3/text/2/4.png"));
			if(!_changed){
			
			flash();
			TheGame._scroll = new Image("scroll/smilespace.png");
			TheGame._vertscroll = true;
			_staticimage = null;
			_changed = true;
			}
		}
		if(_health < -10000) {
			_x = TheGame._character1.getX() + 150;
			_y = TheGame._character1.getY() - 50;
		}
		if(_health < -15000&& _health >= -18000) {
			TheGame.setText(new Image("tootboss3/text/2/5.png"));
			if(!_changed2){
				TheGame._scroll = new Image("scroll/smilespace2.png");
				_changed2 = true;
			}
			
		}
		if(_health < -22000&& _health >= -26000) {
			TheGame.setText(new Image("tootboss3/text/2/6.png"));
			if(!_changed3){
				TheGame._scroll = new Image("scroll/smilespace3.png");
				_changed3 = true;
			}
			
		}
		if(_health < -26000&& _health >= -30000) {
			TheGame.setText(new Image("tootboss3/text/2/7.png"));
			if(!_changed4){
				TheGame._scroll = new Image("scroll/smilespace4.png");
				_changed4 = true;
			}
			
		}
		if(_health < -30000) {
			TheGame.setText(new Image("tootboss3/text/2/8.png"));
			if(!_changed5){
				TheGame._scroll = new Image("scroll/smilespace5.png");
				_changed5 = true;
			}
		}
		if(_health < -34000 && _changed5 && !TheGame._closed) {
			System.out.println("hererere");
			TheGame._beattoot5 = "t";
			TheGame.writeData();
			TheGame._closed = true;
			try {
				TheGame._player.stop();
				
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			TheGame._stage.close();
		}
		
	}
	
	public void flash() {
		_flash = true;
		TheGame._frontdrops.add(new Backdrop(new Image("ultimoboss/flash.png"), 0, 0, 900, 600));
		_counter2 = 0;
		TheGame.playStageSong("/songs/toot4.mp3");
		TheGame.playSound("/ultimoboss/sounds/flash.wav");
	}
	
	public void executeFlash() {
		if(_counter2 == 7) {
			TheGame._frontdrops.clear();
			_flash = false;
		}
	}

}
