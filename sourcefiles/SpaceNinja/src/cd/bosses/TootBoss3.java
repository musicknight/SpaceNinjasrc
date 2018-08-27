package cd.bosses;

import java.util.ArrayList;
import java.util.List;

import cd.AnimatedHitbox;
import cd.GameSounds;
import cd.Hitbox;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class TootBoss3 extends Boss {

	private int _text = 1;
	private List<Image> _spin = new ArrayList<Image>();
	private int _phase;
	private boolean _ending;
	
	public TootBoss3() {
		super(740, 75, "tootboss3");
		_width = 150;
		_height = 150;
		_health = -1000;
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
	}

	
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(_spawning) {
			executeSpawn();
		}
		if(_attack1) {
			executeAttack1();
		}
		if(_ending) {
			executeEnd();
		}
		if(_counter2 < 280 && TheGame._character1.isCanAct()) {
			TheGame._character1.setCanAct(false);
		}
		
	}
	
	
	@Override
	public void spawn() {
		_spawning = true;
		TheGame.setText(new Image("tootboss3/text/intro/" +  _text + ".png"));
		_text++;
		_counter2 = 0;
		TheGame._character1.setXVelocity(0);
		TheGame._character1.setCanAct(false);
		TheGame._character1.setX(5);
		
	}
	
	public void executeSpawn() {
		if(_counter2 == 240) {
			TheGame.stopText();
		}
		if(_counter2 == 260 && _text < 23) {
			TheGame.setText(new Image("tootboss3/text/intro/" +  _text + ".png"));
			_text++;
			_counter2 = 1;
		}
		if(_counter2 == 270) {
			attack1();
		}
		if(_counter2 == 280) {
			
			_spawning = false;
			TheGame._character1.setCanAct(true);
		}
	}
	public void attack1() {
		_attack1 = true;
		_counter4 = 0;
	}
	
	public void executeAttack1() {
		if(_counter4 > 0 && _counter4 % 5 == 0) {
			if(_phase == 0){
			Hitbox a = new AnimatedHitbox("smile", this, false, 90, 250, 75, 75, -20, 0, 0, 1, _spin, 2);
			a.setCircle(true);
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			Hitbox b = new AnimatedHitbox("smile", this, false, 90, 0, 75, 75, 0, 20, 0, 1, _spin, 2);
			b.setCircle(true);
			b.setDissappearOnHit(false);
			TheGame._attacks.add(b);
			} else if(_phase == 1) {
				Hitbox a = new AnimatedHitbox("smile", this, false, 899, 250, 75, 75, -20, 0, 0, 1, _spin, 2);
				a.setCircle(true);
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
				Hitbox b = new AnimatedHitbox("smile", this, false, 90, 0, 75, 75, 0, 20, 0, 1, _spin, 2);
				b.setCircle(true);
				b.setDissappearOnHit(false);
				TheGame._attacks.add(b);
			} else if(_phase == 2 || _phase == 3) {
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
		if(_health < -1100 && _phase == 0) {
			_counter4 = -50;
			_phase++;
		}
		if(_health < -1200 && _phase == 1) {
			_counter4 = 0;
			_counter3 = 0;
			_phase++;
		}
		if(_phase == 2){
		if( _counter3 == 0) {
			_xvelocity = -30;
		}
		if(_counter3 == 25) {
			_xvelocity = 0;
			_yvelocity = 30;
		}
		if(_counter3 == 32) {
			_yvelocity = 0;
		}
		}
		if(_health < -1300 && _phase == 2) {
			_counter3 = 0;
			_phase++;
		}
		if(_phase == 3) {
		if(_counter3 == 0) {
			_yvelocity = -30;
		}
		if(_counter3 == 7) {
			_yvelocity = 0;
			end();
		}
		}
	}
	@Override
	public void hit(Hitbox h) {
		
		if(!_spawning){
			_health -= h.getDamage();
		}
		
	}
	
	
	public void end() {
		_ending = true;
		TheGame.setText(new Image("tootboss3/text/1.png"));
		_counter2 = 0;
	}
	
	public void executeEnd() {
		if(_counter2 == 240) {
			TheGame.stopText();
		}
		if(_counter2 == 260) {
			TheGame.setText(new Image("tootboss3/text/2.png"));
		}
		if(_counter2 == 500) {
			TheGame.stopText();
		}
		if(_counter2 == 520) {
			TheGame.setText(new Image("tootboss3/text/3.png"));
		}
		if(_counter2 == 760) {
			TheGame._beattoot4 = "t";
			
				
				TheGame.writeData();
				TheGame._closed = true;
				GameSounds.stopPlayer();
				
				TheGame._stage.close();
			
		}
	}

}
