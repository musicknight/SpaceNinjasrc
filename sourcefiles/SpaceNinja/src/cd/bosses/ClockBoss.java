package cd.bosses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cd.Backdrop;
import cd.CharLinkedHitbox;
import cd.GameSounds;
import cd.Hitbox;
import cd.HitboxImpl;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ClockBoss extends Boss{

	private Hitbox _body = new CharLinkedHitbox("clockbody", this, 0, 1);
	private int _repeat1;
	private int _repeat2;
	private int _repeat3;
	private int _repeat4;
	private int _repeatno1;
	private int _repeatno2;
	private int _repeatno3;
	private int _repeatno4;
	private int _spinrate;
	private List<Image> _balls = new ArrayList<Image>();
	private int _attack1var;
	private int _attack2var;
	private int _x2;
	private int _y2;
	private Random _r = new Random();
	private boolean _t = false;
	private int _counter5= 0;
	
	public ClockBoss() {
		super(900, 600, "clockboss");
		_health = 1750;
		_maxhealth = 1750;
		_staticimage = new Image("clockboss/1.png");
		_circle = true;
		_spinrate = 4;
		_width = 200;
		_height = 200;
		_body = new CharLinkedHitbox("clockbody", this, 0, 1);
		_body.setCircle(true);
		_balls.add(new Image("clockboss/hand/1.png"));
		_balls.add(new Image("clockboss/hand/2.png"));
		_balls.add(new Image("clockboss/hand/3.png"));
		_balls.add(new Image("clockboss/hand/4.png"));
		_balls.add(new Image("clockboss/hand/5.png"));
		_balls.add(new Image("clockboss/hand/6.png"));
		_balls.add(new Image("clockboss/hand/7.png"));
		_balls.add(new Image("clockboss/hand/8.png"));
		_balls.add(new Image("clockboss/hand/9.png"));
		_balls.add(new Image("clockboss/hand/10.png"));
		_balls.add(new Image("clockboss/hand/11.png"));
		_balls.add(new Image("clockboss/hand/12.png"));
		_balls.add(new Image("clockboss/hand/13.png"));
		_balls.add(new Image("clockboss/hand/14.png"));
		_balls.add(new Image("clockboss/hand/15.png"));
		_balls.add(new Image("clockboss/hand/16.png"));
		_balls.add(new Image("clockboss/hand/17.png"));
		_balls.add(new Image("clockboss/hand/18.png"));
		_balls.add(new Image("clockboss/hand/19.png"));
		_balls.add(new Image("clockboss/hand/20.png")); 
		
	}

	public void spawn() {
		_spawning = true;
		_counter2 = 0;
		_x = 910;
		_y = 110;
		// resting _x is 607
		_xvelocity = -3;
		TheGame.setText(new Image("clockboss/text/spawn.png"));
		_counter2 = 1;
		_counter5 = 1;
		
	}
	
	public void executeSpawn(){
		if(_counter2 == 100) {
			_xvelocity = 0;
			_spawning = false;
		}
	}
	
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(TheGame._character1.getLives()<=0) {
			_won = true;
		}
		_counter5++;
		if(_health == 0) {
			_dead = true;
			_attack1 = false;
			_attack2 = false;
			_yvelocity = 5;
			
		}
		if(_spawning) {
			executeSpawn();
		}
		if(_health == 0 && !_dead) {
			_attack1 = false;
			_attack2 = false;
			_attack3 = false;
			_attack4 = false;
			_counter2 = -1;
			_dead = true;
			_staticimage = new Image("clockboss/dead.png");
		}
		if(_counter5 == 0 && !_won && !_dead) {
			int i = _r.nextInt(8) + 1;
			TheGame.setText(new Image("clockboss/text/" + i + ".png"));
			
		}
		if(_counter5 == 150) {
			TheGame.stopText();
			_counter5 = -500;
		}
		if(_dead) {
			TheGame.setText(new Image("clockboss/text/dead.png"));
			TheGame._timescale = 1;
			TheGame._frontdrops.clear();
			TheGame._scrollr = 10;
			_attack1 = false;
			_attack2 = false;
			_attack3 = false;
			_xvelocity = 0;
			_yvelocity = 3;
		}
		if(!TheGame._attacks.contains(_body)) {
			TheGame._attacks.add(_body);
		}
		Image bd = _balls.get(_spriteindex);
		if(_spinrate != 0){
		
		if(_counter % _spinrate == 0) {
			if(_spriteindex < _balls.size() -1) {
				_spriteindex++;
			} else {
				_spriteindex = 0;
			}
		}
		}
		TheGame._gc.drawImage(bd, _x, _y, 200, 200);
		
		if(_attack1) {
			executeAttack1();
		}
		if(_attack2) {
			executeAttack2();
		}
		if(_attack3) {
			executeAttack3();
		}
		if(_attack4) {
			executeAttack2();
		}
		if(!_dead){
		if(_counter1 < 80) {
			_yvelocity = 0;
			if(_counter % 3 == 0){
			if(_counter1 <= 40) {
				_y -=1;
			} else {
				_y += 1;
			}
			}
		} else {
			_counter1 = 0;
		}
		}
		if(!_attack1 && !_attack2 && !_attack3 && !_attack4 && !_attack5 && !_dead) {
			
			if(_counter3 == 120 && !_won) {
				Random r = new Random();
				int i = r.nextInt(3);
				_counter3 = 0;
				
				if(_repeat1 == 2) {
					i = 2;
				}
				if(_repeat2 == 2) {
					i = 1;
				}
				if(_repeat3 == 2) {
					i = 0;
				}
				if(_repeat4 == 2) {
					i = 2;
				}
				if(_repeatno1 == 5) {
					i = 0;
				}
				if(_repeatno2 == 5) {
					i = 1;
				}
				if(_repeatno3 == 5) {
					i = 2;
				}
				if(_repeatno4 == 5) {
					i = 0;
				}
				
			
				if(i == 0) {
					_attack1var = r.nextInt(2);
					attack1();
					_repeat1++;
					_repeat2 = 0;
					_repeat3 = 0;
					_repeat4 = 0;
					_repeatno1 = 0;
					_repeatno2++;
					_repeatno3++;
					_repeatno4++;
				}
				if(i == 1) {
					_attack2var = r.nextInt(2);
					attack2();
					_repeat2++;
					_repeat1 = 0;
					_repeat3 = 0;
					_repeat4 = 0;
					_repeatno1++;
					_repeatno2=0;
					_repeatno3++;
					_repeatno4++;
				}
				if(i == 2) {
					attack3();
					_repeat3++;
					_repeat1 = 0;
					_repeat2 = 0;
					_repeat4 = 0;
					_repeatno1++;
					_repeatno2++;
					_repeatno3=0;
					_repeatno4++;
				}
				if(i == 3) {
					//attack4();
					_repeat4++;
					_repeat1 = 0;
					_repeat2 = 0;
					_repeat3 = 0;
					_repeatno1++;
					_repeatno2++;
					_repeatno3++;
					_repeatno4=0;
				}
				
			}
		}
	}
	
	public void attack1() {
		_attack1 = true;
		_counter4 = 0;
		GameSounds.playSound("/clockboss/sounds/tick1.wav");
	}
	
	private void executeAttack1() {
		if(_attack1var == 0) {
			//variation 1
		if(_counter4 < 70){
		TheGame._gc.drawImage(new Image("clockboss/g.png"), 0, 32, 900, 40);
		TheGame._gc.drawImage(new Image("clockboss/g.png"), 0, 152, 900, 40);
		TheGame._gc.drawImage(new Image("clockboss/g.png"), 0, 272, 900, 40);
		TheGame._gc.drawImage(new Image("clockboss/g.png"), 0, 392, 900, 40);
		}
		if(_counter4 == 35) {
			GameSounds.playSound("/clockboss/sounds/tick1.wav");
		}
		if(_counter4 < 95 && _counter4 >= 70) {
			
			TheGame._character1.setXVelocity(0);
			TheGame._character1.setYVelocity(0);
		}
		if(_counter4 == 70) {
			GameSounds.playSound("/clockboss/sounds/tick2.wav");
			TheGame._frontdrops.add(new Backdrop(new Image("clockboss/c.png"), 0, 0, 900, 600));
			Hitbox l1 = new HitboxImpl("l1", this, false, 0, 32, 900, 40, 0, 0, 0, 1, new Image("clockboss/g.png"));
			l1.setDissappearOnHit(false);
			Hitbox l2 = new HitboxImpl("l1", this, false, 0, 152, 900, 40, 0, 0, 0, 1, new Image("clockboss/g.png"));
			l2.setDissappearOnHit(false);
			Hitbox l3 = new HitboxImpl("l1", this, false, 0, 272, 900, 40, 0, 0, 0, 1, new Image("clockboss/g.png"));
			l3.setDissappearOnHit(false);
			Hitbox l4 = new HitboxImpl("l1", this, false, 0, 392, 900, 40, 0, 0, 0, 1, new Image("clockboss/g.png"));
			l4.setDissappearOnHit(false);
			TheGame._attacks.add(l1);
			TheGame._attacks.add(l2);
			TheGame._attacks.add(l3);
			TheGame._attacks.add(l4);
			TheGame._character1.setCanAct(false);
			TheGame._scrollr = 0;
			TheGame._frozen = true;
			_spinrate = 0;
			
		}
		if(_counter4 == 95) {
			TheGame._frontdrops.clear();
			TheGame.clearHitboxes("l1", this);
			TheGame._character1.setCanAct(true);
			TheGame._scrollr = 10;
			TheGame._frozen = false;
			_attack1 = false;
			_counter3 = 0;
			_spinrate = 4;
			
		}
		} else if(_attack1var == 1) {
			//variation 2
			if(_counter4 < 70){
				TheGame._gc.drawImage(new Image("clockboss/g.png"), 32, 0, 50, 600);
				TheGame._gc.drawImage(new Image("clockboss/g.png"), 152, 0, 50, 600);
				TheGame._gc.drawImage(new Image("clockboss/g.png"), 272, 0, 50, 600);
				TheGame._gc.drawImage(new Image("clockboss/g.png"), 392, 0, 50, 600);
				TheGame._gc.drawImage(new Image("clockboss/g.png"), 512, 0, 50, 600);
				TheGame._gc.drawImage(new Image("clockboss/g.png"), 632, 0, 50, 600);
				TheGame._gc.drawImage(new Image("clockboss/g.png"), 752, 0, 50, 600);
				TheGame._gc.drawImage(new Image("clockboss/g.png"), 872, 0, 50, 600);
				}
				if(_counter4 == 35) {
					GameSounds.playSound("/clockboss/sounds/tick1.wav");
				}
				if(_counter4 < 95 && _counter4 >= 70) {
					
					TheGame._character1.setXVelocity(0);
					TheGame._character1.setYVelocity(0);
				}
				if(_counter4 == 70) {
					GameSounds.playSound("/clockboss/sounds/tick2.wav");
					TheGame._frontdrops.add(new Backdrop(new Image("clockboss/c.png"), 0, 0, 900, 600));
					Hitbox l1 = new HitboxImpl("l1", this, false, 32, 0, 50, 600, 0, 0, 0, 1, new Image("clockboss/g.png"));
					l1.setDissappearOnHit(false);
					Hitbox l2 = new HitboxImpl("l1", this, false, 152, 0, 50, 600, 0, 0, 0, 1, new Image("clockboss/g.png"));
					l2.setDissappearOnHit(false);
					Hitbox l3 = new HitboxImpl("l1", this, false, 272, 0, 50, 600, 0, 0, 0, 1, new Image("clockboss/g.png"));
					l3.setDissappearOnHit(false);
					Hitbox l4 = new HitboxImpl("l1", this, false, 392, 0, 50, 600, 0, 0, 0, 1, new Image("clockboss/g.png"));
					l4.setDissappearOnHit(false);
					Hitbox l5 = new HitboxImpl("l1", this, false, 512, 0, 50, 600, 0, 0, 0, 1, new Image("clockboss/g.png"));
					l5.setDissappearOnHit(false);
					Hitbox l6 = new HitboxImpl("l1", this, false, 632, 0, 50, 600, 0, 0, 0, 1, new Image("clockboss/g.png"));
					l6.setDissappearOnHit(false);
					Hitbox l7 = new HitboxImpl("l1", this, false, 752, 0, 50, 600, 0, 0, 0, 1, new Image("clockboss/g.png"));
					l7.setDissappearOnHit(false);
					Hitbox l8 = new HitboxImpl("l1", this, false, 872, 0, 50, 600, 0, 0, 0, 1, new Image("clockboss/g.png"));
					l8.setDissappearOnHit(false);
					TheGame._attacks.add(l1);
					TheGame._attacks.add(l2);
					TheGame._attacks.add(l3);
					TheGame._attacks.add(l4);
					TheGame._attacks.add(l5);
					TheGame._attacks.add(l6);
					TheGame._attacks.add(l7);
					TheGame._attacks.add(l8);
					TheGame._scrollr = 0;
					TheGame._frozen = true;
					TheGame._character1.setCanAct(false);

					_spinrate = 0;
				}
				if(_counter4 == 95) {
					TheGame._frontdrops.clear();
					TheGame.clearHitboxes("l1", this);
					TheGame._scrollr = 10;
					TheGame._frozen = false;
					TheGame._character1.setCanAct(true);
					_attack1 = false;
					_counter3 = 0;
					_spinrate = 4;
					
				}
		} else if(_attack1var == 2) {
			//variation 3
			if(_counter4 < 70){
				TheGame._gc.drawImage(new Image("clockboss/block.png"), 0, 60);
				
				}
				if(_counter4 == 35) {
					GameSounds.playSound("/clockboss/sounds/tick1.wav");
				}
				if(_counter4 < 95 && _counter4 >= 70) {
					
					TheGame._character1.setXVelocity(0);
					TheGame._character1.setYVelocity(0);
				}
				if(_counter4 == 70) {
					GameSounds.playSound("/clockboss/sounds/tick2.wav");
					TheGame._frontdrops.add(new Backdrop(new Image("clockboss/c.png"), 0, 0, 900, 600));
					Hitbox l1 = new HitboxImpl("l1", this, false, 0, 60, 900, 131, 0, 0, 0, 1, new Image("clockboss/g.png"));
					l1.setDissappearOnHit(false);
					Hitbox l2 = new HitboxImpl("l1", this, false, 0, 291, 900, 151, 0, 0, 0, 1, new Image("clockboss/g.png"));
					l2.setDissappearOnHit(false);
					Hitbox l3 = new HitboxImpl("l1", this, false, 0, 191, 395, 100, 0, 0, 0, 1, new Image("clockboss/g.png"));
					l3.setDissappearOnHit(false);
					Hitbox l4 = new HitboxImpl("l1", this, false, 495, 191, 405, 100, 0, 0, 0, 1, new Image("clockboss/g.png"));
					l4.setDissappearOnHit(false);
					TheGame._scrollr = 0;
					TheGame._attacks.add(l1);
					TheGame._attacks.add(l2);
					TheGame._attacks.add(l3);
					TheGame._attacks.add(l4);
					TheGame._frozen = true;
					_spinrate = 0;
					
					TheGame._character1.setCanAct(false);
				}
				if(_counter4 == 95) {
					TheGame._frontdrops.clear();
					TheGame.clearHitboxes("l1", this);
					TheGame._scrollr = 10;
					TheGame._frozen = false;
					TheGame._character1.setCanAct(true);
					_attack1 = false;
					_counter3 = 0;
					_spinrate = 4;
					
				}
		}
		
		
	}
	
	public void attack2() {
		_counter4 = 0;
		_attack2 = true;
		TheGame._timescale = 0.5;
		TheGame._scrollr = 5;
		_spinrate = 8;
	}

	private void executeAttack2() {
		if(_attack2var == 0){
			//variation 1
		
		Image i = new Image("clockboss/shot.png");
		if(_counter4 >= 25 && _counter4 % 5 == 0 && _counter4 <= 210) {
			Hitbox a = new HitboxImpl("ice", this, false, 899, 200+_y2, 25, 37, -30, 0, 0, 1, i);
			a.setCircle(true);
			a.setDissappearOnHit(false);
			Hitbox b = new HitboxImpl("ice", this, false, 899, 370+_y2, 25, 37, -30, 0, 0, 1, i);
			b.setCircle(true);
			b.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			TheGame._attacks.add(b);
			GameSounds.playSound("/clockboss/sounds/tick1.wav");
			
		}
		if(_counter4 > 45 && _counter4 < 80) {
			_y2 +=3;
		}
		if(_counter4 > 90 && _counter4 < 170) {
			_y2 -=3;
		}
		if(_counter4 > 185) {
			_y2+=6;
		}
		if(_counter4 == 270) {
			_attack2 = false;
			TheGame._timescale = 1;
			TheGame._scrollr = 10;
			_counter3 = 0;
			_y2 = 0;
			_spinrate = 4;
		}
		} else if(_attack2var == 1) {
			//variation 2
			if(_counter4 > 20 && _counter4 < 51 && _counter4 % 5 == 0) {
				Image i = new Image("clockboss/shot.png");
				Hitbox a = new HitboxImpl("ice", this, false, 899, 60, 120, 191, -30, 0, 0, 1, i);
				a.setCircle(true);
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
				GameSounds.playSound("/clockboss/sounds/tick1.wav");
			}
			if(_counter4 > 70 && _counter4 < 101 && _counter4 % 5 == 0) {
				Image i = new Image("clockboss/shot.png");
				Hitbox a = new HitboxImpl("ice", this, false, 899, 251, 120, 191, -30, 0, 0, 1, i);
				a.setCircle(true);
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
				GameSounds.playSound("/clockboss/sounds/tick1.wav");
			}
			if(_counter4 > 120 && _counter4 < 151 && _counter4 % 5 == 0) {
				Image i = new Image("clockboss/shot.png");
				Hitbox a = new HitboxImpl("ice", this, false, 899, 60, 120, 191, -30, 0, 0, 1, i);
				a.setCircle(true);
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
				GameSounds.playSound("/clockboss/sounds/tick1.wav");
			}
			if(_counter4 >= 170 && _counter4 % 5 == 0 && _counter4 <= 230) {
				Image i = new Image("clockboss/shot.png");
				Hitbox a = new HitboxImpl("ice", this, false, 899, 185+_y2, 25, 37, -30, 0, 0, 1, i);
				a.setCircle(true);
				a.setDissappearOnHit(false);
				Hitbox b = new HitboxImpl("ice", this, false, 899, 345+_y2, 25, 37, -30, 0, 0, 1, i);
				b.setCircle(true);
				b.setDissappearOnHit(false);
				TheGame._attacks.add(a);
				TheGame._attacks.add(b);
				GameSounds.playSound("/clockboss/sounds/tick1.wav");
				
			}
			if(_counter4 == 260) {
				_attack2 = false;
				TheGame._timescale = 1;
				TheGame._scrollr = 10;
				_counter3 = 0;
				_y2 = 0;
				_spinrate = 4;
			}
		}
		
	}
	
	public void attack3() {
		_attack3 = true;
		_counter4 = 0;
	}
	
	private void executeAttack3() {
		
		if(_counter4 % 50 == 0) {
			_x2 = _r.nextInt(600);
			_y2 = _r.nextInt(160);
			_t = _r.nextBoolean();
			if(_t){
			TheGame._frontdrops.add(new Backdrop(new Image("clockboss/t.png"), 73+_x2,225+_y2, 50, 50));
			} else {
				TheGame._frontdrops.add(new Backdrop(new Image("clockboss/x.png"), 73+_x2,225+_y2, 50, 50));
				//353
			}
			_counter2 = 0;
			GameSounds.playSound("/clockboss/sounds/tick1.wav");
		}
		if(_counter2 == 20) {
			TheGame._frontdrops.clear();
			Image i = new Image("clockboss/shot.png");
			if(_t){
			Hitbox a = new HitboxImpl("ice", this, false, 73+_x2-500, 225+_y2, 25, 40, 25, 0, 0, 1, i);
			a.setCircle(true);
			a.setDissappearOnHit(false);
			a.setAutogone(false);
			Hitbox b = new HitboxImpl("ice", this, false, 73+_x2+500, 225+_y2, 25, 40, -25, 0, 0, 1, i);
			b.setCircle(true);
			b.setDissappearOnHit(false);
			b.setAutogone(false);
			Hitbox c = new HitboxImpl("ice", this, false, 73+_x2, 225+_y2-500, 25, 40, 0, 25, 0, 1, i);
			c.setCircle(true);
			c.setDissappearOnHit(false);
			c.setAutogone(false);
			Hitbox d = new HitboxImpl("ice", this, false, 73+_x2, 225+_y2+500, 25, 40, 0, -25, 0, 1, i);
			d.setCircle(true);
			d.setDissappearOnHit(false);
			d.setAutogone(false);
			TheGame._attacks.add(a);
			TheGame._attacks.add(b);
			TheGame._attacks.add(c);
			TheGame._attacks.add(d);
			} else {
				Hitbox a = new HitboxImpl("ice", this, false, 73+_x2-353, 225+_y2-353, 25, 40, 17.6, 17.6, 0, 1, i);
				a.setCircle(true);
				a.setDissappearOnHit(false);
				a.setAutogone(false);
				Hitbox b = new HitboxImpl("ice", this, false, 73+_x2+353, 225+_y2+353, 25, 40, -17.6, -17.6, 0, 1, i);
				b.setCircle(true);
				b.setDissappearOnHit(false);
				b.setAutogone(false);
				Hitbox c = new HitboxImpl("ice", this, false, 73+_x2-353, 225+_y2+353, 25, 40, 17.6, -17.6, 0, 1, i);
				c.setCircle(true);
				c.setDissappearOnHit(false);
				c.setAutogone(false);
				Hitbox d = new HitboxImpl("ice", this, false, 73+_x2+353, 225+_y2-353, 25, 40, -17.6, 17.6, 0, 1, i);
				d.setCircle(true);
				d.setDissappearOnHit(false);
				d.setAutogone(false);
				TheGame._attacks.add(a);
				TheGame._attacks.add(b);
				TheGame._attacks.add(c);
				TheGame._attacks.add(d);
			}
		}
		if(_counter2 == 41) {
			TheGame.clearHitboxes("ice", this);
		}
		if(_counter4 == 343) {
			_x2 = 0;
			_y2 = 0;
			_attack3 = false;
			_counter3 = 0;
		}
	}

	

}
