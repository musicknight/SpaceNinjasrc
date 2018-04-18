package cd.bosses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cd.Backdrop;
import cd.CharLinkedHitbox;
import cd.Hitbox;
import cd.HitboxImpl;
import cd.MeleeHitbox;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class UltimoBoss extends Boss {

	private List<Image> _balls = new ArrayList<Image>();
	private int _spriteindex;
	private int _rate2 = 3;
	private Random _random = new Random();
	private int _form = 0;
	private int _repeat1;
	private int _atkcount = 0;
	private Backdrop _flash = new Backdrop(new Image("ultimoboss/flash.png"), 0, 0, 900, 600);
	private Hitbox _body = new CharLinkedHitbox("ultimobody", this, 0, 1);
	private boolean _changeform1;
	private boolean _changeform2;
	private boolean _changeform3;
	private boolean _changeform4;
	private boolean _changeform0;
	private int _atk1count;
	private boolean _gattack1;
	private int _gatk1count;
	private boolean _gattack2;
	private Hitbox _gdrop;
	private boolean _stunned;
	private boolean _rattack1;
	private int _charx;
	private int _chary;
	private boolean _rattack2;
	private int _ratk2count;
	private boolean _lasershot;
	private boolean _yattack1;
	private boolean _yattack2;
	private boolean _1sttime;
	private int _text = 1;
	
	public UltimoBoss(String b) {
		super(900, 600, "ultimoboss");
		if(b.equals("t")) {
			_1sttime = false;
		} else {
			_1sttime = true;
		}
		_health = 2000;
		_width = 150;
		_height = 150;
		_staticimage = new Image("ultimoboss/1.png");
		_balls.add(new Image("ultimoboss/balls1/1.png"));
		_balls.add(new Image("ultimoboss/balls1/2.png"));
		_balls.add(new Image("ultimoboss/balls1/3.png"));
		_balls.add(new Image("ultimoboss/balls1/4.png"));
		_balls.add(new Image("ultimoboss/balls1/5.png"));
		_balls.add(new Image("ultimoboss/balls1/6.png"));
		_balls.add(new Image("ultimoboss/balls1/7.png"));
		_balls.add(new Image("ultimoboss/balls1/8.png"));
		_balls.add(new Image("ultimoboss/balls1/9.png"));
		_balls.add(new Image("ultimoboss/balls1/10.png"));
		_balls.add(new Image("ultimoboss/balls1/11.png"));
		_balls.add(new Image("ultimoboss/balls1/12.png"));
		_balls.add(new Image("ultimoboss/balls1/13.png"));
		_balls.add(new Image("ultimoboss/balls1/14.png"));
		_balls.add(new Image("ultimoboss/balls1/15.png"));
		_balls.add(new Image("ultimoboss/balls1/16.png"));
		_balls.add(new Image("ultimoboss/balls1/17.png"));
		_balls.add(new Image("ultimoboss/balls1/18.png"));
		_balls.add(new Image("ultimoboss/balls1/19.png"));
		_balls.add(new Image("ultimoboss/balls1/20.png"));
		_body = new CharLinkedHitbox("ultimobody", this, 0, 1);
		_body.setCircle(true);
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(TheGame._character1.getLives()<=0) {
			_won = true;
		}
		if(_1sttime) {
			TheGame._character1.setCanAct(false);
			if(_counter2 == 0) {
				if(_text == 11) {
					_counter3 = 0;
					_1sttime = false;
					try {
						TheGame._player.stop();
					} catch (BasicPlayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					TheGame.playStageSong("/songs/ultimo.mp3");
					TheGame._character1.setCanAct(true);
					TheGame.endUDialogue();
				} else {
					TheGame.setText(new Image("ultimoboss/text/s" + _text + ".png"));
				}
			
				
			}
		}
		if(_counter2 == 240 && _text != 11) {
			TheGame.stopText();
			_text++;
			if(_1sttime){
			_counter2 = -20;
			}
		}
		if(_counter2 == 0 && !_dead && !_1sttime) {
			if(_spawning) {
				TheGame.setText(new Image("ultimoboss/text/s10.png"));
			} else if(!_dead) {
				int i = _random.nextInt(9) + 1;
				
				TheGame.setText(new Image("ultimoboss/text/" + i + ".png"));
			} 
			
		}
		if(_counter2 == 150 && !_1sttime) {
			TheGame.stopText();
			_counter2 = -500;
		}
		if(!TheGame._attacks.contains(_body)) {
			TheGame._attacks.add(_body);
		}
		Image bd = _balls.get(_spriteindex);
		if(_counter % _rate2 == 0) {
			if(_spriteindex < _balls.size() -1) {
				_spriteindex++;
			} else {
				_spriteindex = 0;
			}
		}
		TheGame._gc.drawImage(bd, _x - 112, _y-112, 375, 375);
		if(_spawning) {
			executeSpawn();
		}
		if(_changeform0) {
			changeform0();
		}
		if(_changeform1) {
			changeform1();
		}
		if(_changeform2) {
			changeform2();
		}
		if(_changeform3) {
			changeform3();
		}
		
		
		
		if(_attack1) {
			executeAttack1();
		}
		if(_attack2) {
			executeAttack2();
		}
		if(_gattack1) {
			executeGAttack1();
		}
		if(_gattack2) {
			executeGAttack2();
		}
		if(_rattack1) {
			executeRAttack1();
		}
		if(_rattack2) {
			executeRAttack2();
		}
		if(_yattack1) {
			executeYAttack1();
		}
		if(_yattack2) {
			executeYAttack2();
		}
		
		if(!_attack1 && !_attack2 && !_attack3 && !_attack4 && !_attack5 && !_gattack1 && !_gattack2 && !_rattack1 && !_rattack2 &&
				!_yattack1 && !_yattack2 && !_dead && !_changeform0 && !_changeform1 && !_changeform2 && !_changeform3 && !_changeform4) {
		if(_counter1 < 29) {
			_yvelocity = 0;
			if(_counter % 3 == 0){
			if(_counter1 < 15) {
				_y -=3;
			} else {
				_y += 3;
			}
			}
		} else {
			_counter1 = 0;
		}
		if(_won) {
			TheGame.setText(new Image("ultimoboss/text/won.png"));
		}
		if(_counter3 == 90 && !_won && !_1sttime) {
			Random r = new Random();
			int i = r.nextInt(3);
			_counter3 = 0;
			if(_atkcount == 3) {
				_counter4 = 0;
				if(_form == 0) {
					_changeform1 = true;
				} else if(_form == 1) {
					_changeform2 = true;
				} else if(_form == 2) {
					_changeform3 = true;
				} else if(_form == 3) {
					_changeform0 = true;
				}
				_atkcount = 0;
			} else {
			i = _random.nextInt(2);
			if(i == 0) {
				if(_form == 0) {
				attack1();
				} else if (_form == 1) {
				gattack1();
				} else if(_form == 2) {
				rattack1();
				} else if(_form == 3) {
				yattack1();
				}
			}
			if(i == 1) {
				if(_form == 0){
				attack2();
				} else if(_form == 1) {
				gattack2();
				} else if(_form == 2) {
				rattack2();
				} else if(_form == 3) {
				yattack2();	
				}
			}
			if(i == 2) {
				if(_form == 0){
				attack3();
				}
			}
			_atkcount++;
			}
		}
		}
	}

	
	public void attack1() {
		_counter4 = 0;
		_attack1 = true;
		_rate2 = 1;
	}
	
	private void executeAttack1() {
		if(_counter4 == 15) {
			_yvelocity = 13;
		}
		if(_counter4 == 30) {
			_yvelocity = 0;
		}
		if(((_counter4 >= 40&& _counter4 < 80) || (_counter4 >= 110&& _counter4 < 150))  && _counter4 % 3 == 0 ) {
			String s;
			if(_atk1count == 0) {
				s = "r";
				_atk1count = 1;
			} else if(_atk1count == 1) {
				s = "g";
				_atk1count = 2;
			} else {
				s = "y";
				_atk1count = 0;
			}
			Image i = new Image("ultimoboss/shots/" + s + ".png");
			Hitbox a = new HitboxImpl("uball", this, false, _x-50, _y+25, 75, 75, -15, 0, 0, 1, i);
			a.setDissappearOnHit(false);
			a.setCircle(true);
			TheGame._attacks.add(a);
		}
		if(_counter4 == 150) {
			_yvelocity = -13;
		}
		if(_counter4 == 165) {
			_yvelocity = 0;
			_counter3 = 0;
			_attack1 = false;
			_rate2 = 3;
		}
	}
	
	public void attack2() {
		_rate2 = 1;
		_counter4 = 0;
		_attack2 = true;
	}
	
	public void executeAttack2() {
		if(_counter4 > 20 && _counter4 % 5 == 0) {
			Hitbox a = new HitboxImpl("uball", this, false, _x-50, _y+112, 35, 35, -15, 15, 0, 1, new Image("ultimoboss/shots/r.png"));
			a.setCircle(true);
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			Hitbox b = new HitboxImpl("uball", this, false, _x-50, _y+50, 35, 35, -21, 0, 0, 1, new Image("ultimoboss/shots/g.png"));
			b.setCircle(true);
			b.setDissappearOnHit(false);
			TheGame._attacks.add(b);
			Hitbox c = new HitboxImpl("uball", this, false, _x-50, _y, 35, 35, -15, -15, 0, 1, new Image("ultimoboss/shots/y.png"));
			c.setCircle(true);
			c.setDissappearOnHit(false);
			TheGame._attacks.add(c);
		}
		if(_counter4 == 70) {
			_xvelocity = -18;
		}
		if(_counter4 == 93) {
			_xvelocity = 0;
		}
		if(_counter4 == 98) {
			_xvelocity = 10;
		}
		if(_counter4 >= 98 && _x >= 645){
			_x = 645;
			_rate2 = 3;
			_xvelocity = 0;
			_attack2 = false;
			_counter3 = 0;
		}
		}
	
	public void gattack1() {
		_gattack1 = true;
		_rate2 = 1;
		_counter4 = 0;
	}
	
	public void executeGAttack1() {
		if(_counter4 == 15) {
			_yvelocity = 18;
		}
		if(_counter4 == 25) {
			_yvelocity = 0;
		}
		if(_counter4 == 35) {
			Hitbox a = new HitboxImpl("uball", this, false, _x, _y, 150, 150, -10, 0, 0, 1, new Image("ultimoboss/shots/g.png"));
			a.setDissappearOnHit(false);
			a.setCircle(true);
			TheGame._attacks.add(a);
		}
		if(_counter4 == 40) {
			_yvelocity = -14;
		}
		if(_counter4 == 50) {
			_yvelocity = 0;
		}
		if(_counter4 == 60) {
			Hitbox a = new HitboxImpl("uball", this, false, _x, _y, 150, 150, -10, 0, 0, 1, new Image("ultimoboss/shots/g.png"));
			a.setDissappearOnHit(false);
			a.setCircle(true);
			TheGame._attacks.add(a);
		}
		if(_counter4 == 65) {
			_yvelocity = 14;
		}
		if(_counter4 == 75) {
			_yvelocity = 0;
		}
		if(_counter4 == 85 && _gatk1count < 3) {
			_counter4 = 34;
			_gatk1count++;
		}
		if(_counter4 == 90) {
			_yvelocity = -18;
		}
		if(_counter4 == 100) {
			_yvelocity = 0;
			_gattack1 = false;
			_gatk1count = 0;
			_counter3 = 0;
			_rate2 = 3;
		}
	}
	
	public void gattack2() {
		_rate2 = 1;
		_counter4 = 0;
		_gattack2 = true;
		_yvelocity = -5;
	}
	
	public void executeGAttack2() {
		if(_counter4 == 15) {
			_yvelocity = 0;
		}
		if(_counter4 == 25) {
			_gdrop = new HitboxImpl("uball", this, true, _x-25, _y-25, 200, 200, 0, 3, 0, 1, new Image("ultimoboss/shots/g.png"));
			_gdrop.setDissappearOnHit(false);
			_gdrop.setCircle(true);
			TheGame._attacks.add(_gdrop);
		}
		if(_counter4 > 25 && _gdrop.getY()+200 >= 442 && _gdrop.getYVelocity() != 0) {
			_gdrop.setY(442-200);
			_gdrop.setYVelocity(0);
			_gdrop.setGravity(false);
			TheGame.playSound("/rockboss/sounds/slam.wav");
			if(TheGame._character1.getY() == 392) {
				TheGame._character1.setCanAct(false);
				TheGame._character1.setXVelocity(0);
				TheGame._character1.setYVelocity(0);
				_stunned = true;
			}
		}
		if(_stunned) {
			TheGame._gc.drawImage(new Image("ninja/stun.png"), TheGame._character1.getX() - 10, TheGame._character1.getY());
			TheGame._gc.drawImage(new Image("ninja/stun.png"), TheGame._character1.getX() + TheGame._character1.getWidth(), TheGame._character1.getY());
		}
		if(_counter4 == 75) {
			_gdrop.setXVelocity(-25);
			_yvelocity = 5;
		}
		if(_counter4 >= 75 && _counter4 % 5 == 0 && _counter4 < 90) {
			Hitbox a = new HitboxImpl("uball", this, false, _x, _y+50, 50, 50, -10, 2, 0, 1, new Image("ultimoboss/shots/g.png"));
			a.setDissappearOnHit(false);
			a.setCircle(true);
			TheGame._attacks.add(a);
		}
		if(_counter4 == 90) {
			_yvelocity = 0;
		}
		if(_counter4 == 100) {
			_stunned = false;
			TheGame._character1.setCanAct(true);	
			_gattack2 = false;
			_counter3 = 0;
			_rate2 = 3;
		}
	}
	
	public void rattack1() {
		_rate2 = 1;
		_yvelocity = -20;
		_counter4 = 0;
		_rattack1 = true;
	}
	
	public void executeRAttack1() {
		if(_counter4 == 20) {
			_yvelocity = 0;
		}
		int x = TheGame._character1.getX();
		int y = TheGame._character1.getY();
		if(_counter4 == 20) {
			Hitbox a = new HitboxImpl("pentagram1", this, false, x-12, 60, 75, 75, 0, 0, 0, 1, new Image("ultimoboss/shots/r2.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 a = new HitboxImpl("pentagram2", this, false, 0, y-12, 75, 75, 0, 0, 0, 1, new Image("ultimoboss/shots/r2.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 a = new HitboxImpl("pentagram3", this, false, 825, y-12, 75, 75, 0, 0, 0, 1, new Image("ultimoboss/shots/r2.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
		}
		if(_counter4 < 70){
		for(Hitbox a : TheGame._attacks) {
			if(a.getID().equals("pentagram1")) {
				a.setX(x-12);
			}
			if(a.getID().equals("pentagram2") || a.getID().equals("pentagram3")	) {
				a.setY(y-12);
			}
		}
		}
		if(_counter4 == 60) {
			for(Hitbox a : TheGame._attacks) {
			if(a.getID().equals("pentagram1") || a.getID().equals("pentagram2") || a.getID().equals("pentagram3")){
				a.setImage(new Image("ultimoboss/shots/r.png"));
			}
			}
		}
		if(_counter4 == 70) {
			for(Hitbox a : TheGame._attacks) {
				if(a.getID().equals("pentagram1")) {
					a.setYVelocity(15);
				}
				if(a.getID().equals("pentagram2")) {
					a.setXVelocity(15);
				}
				if(a.getID().equals("pentagram3")) {
					a.setXVelocity(-15);
				}
			}
		}
		if(_counter4 == 80) {
			_yvelocity = 20;
			
		}
		if(_counter4 == 100) {
			_yvelocity = 0;
			_y = 125;
			_rattack1 = false;
			_counter3 = 0;
			_rate2 = 3;
		}
	}
	
	public void rattack2() {
		_counter4 = 0;
		_yvelocity = -20;
		_rattack2 = true;
	}

	public void executeRAttack2() {
		if(_counter4 == 20) {
			_x = 375;
			_yvelocity = 20;
		}
		if(_counter4 == 40) {
			_yvelocity = 0;
			_rate2 = 1;
		}
		if(_counter4 >= 60 && _counter4 % 6 == 0 && _counter4 < 125) {
			if(_counter4 < 95){
			Hitbox a = new HitboxImpl("uball", this, false, _x+37, _y+37, 75, 75, 15, 0, 0, 1, new Image("ultimoboss/shots/r.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 a = new HitboxImpl("uball", this, false, _x+37, _y+37, 75, 75, -15, 0, 0, 1, new Image("ultimoboss/shots/r.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 a = new HitboxImpl("uball", this, false, _x+37, _y+37, 75, 75, 0, 15, 0, 1, new Image("ultimoboss/shots/r.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 a = new HitboxImpl("uball", this, false, _x+37, _y+37, 75, 75, 0, -15, 0, 1, new Image("ultimoboss/shots/r.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 a = new HitboxImpl("uball", this, false, _x+37, _y+37, 75, 75, -10, 10, 0, 1, new Image("ultimoboss/shots/r.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 a = new HitboxImpl("uball", this, false, _x+37, _y+37, 75, 75, 10, 10, 0, 1, new Image("ultimoboss/shots/r.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 a = new HitboxImpl("uball", this, false, _x+37, _y+37, 75, 75, 10, -10, 0, 1, new Image("ultimoboss/shots/r.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 a = new HitboxImpl("uball", this, false, _x+37, _y+37, 75, 75, -10, -10, 0, 1, new Image("ultimoboss/shots/r.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			} else if(_counter4 >= 100) {
				Hitbox a = new HitboxImpl("uball", this, false, _x+37, _y+37, 75, 75, 10, 6, 0, 1, new Image("ultimoboss/shots/r.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 a = new HitboxImpl("uball", this, false, _x+37, _y+37, 75, 75, -10, -6, 0, 1, new Image("ultimoboss/shots/r.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 a = new HitboxImpl("uball", this, false, _x+37, _y+37, 75, 75, -6, 10, 0, 1, new Image("ultimoboss/shots/r.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 a = new HitboxImpl("uball", this, false, _x+37, _y+37, 75, 75, 6, -15, 0, 1, new Image("ultimoboss/shots/r.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 a = new HitboxImpl("uball", this, false, _x+37, _y+37, 75, 75, -14, 8, 0, 1, new Image("ultimoboss/shots/r.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 a = new HitboxImpl("uball", this, false, _x+37, _y+37, 75, 75, 8, 14, 0, 1, new Image("ultimoboss/shots/r.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 a = new HitboxImpl("uball", this, false, _x+37, _y+37, 75, 75, 8, -14, 0, 1, new Image("ultimoboss/shots/r.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 a = new HitboxImpl("uball", this, false, _x+37, _y+37, 75, 75, -14, -8, 0, 1, new Image("ultimoboss/shots/r.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
			}
		}
		if(_counter4 == 126 && _ratk2count < 2) {
			_counter4 = 45;
			_ratk2count++;
		}
		if(_counter4 == 130) {
			_rate2 = 3;
			_yvelocity = -20;
		}
		if(_counter4 == 150) {
			_x = 645;
			_yvelocity = 20;
		}
		if(_counter4 == 170) {
			_yvelocity = 0;
			_y = 125;
			_counter3 = 0;
			_rattack2 = false;
			_ratk2count = 0;
		}
	}
	
	public void yattack1() {
		_yattack1 = true;
		_counter4 = 0;
		_rate2 = 1;
	}
	
	public void executeYAttack1() {
		if(_counter4 == 5) {
			_yvelocity = 12;
		}
		if(_counter4 == 20) {
			_yvelocity = 0;
			
		}
		if(_counter4 >= 20) {
			makeVLaser(_counter4-20, 0, "laser");
			makeVLaser(_counter4-20, 225, "laser");
			makeVLaser(_counter4-20, 450, "laser");
			makeVLaser(_counter4-20, 675, "laser");
		}
		if(_counter4 == 60) {
			_xvelocity = 7;
		}
		if(_counter4 == 70){
			_xvelocity = -30;
		}
		if(_counter4 == 120) {
			_xvelocity = 20;
		}
		if(_counter4 == 190) {
			_xvelocity = 0;
		}
		if(_counter4 >= 200 && _counter4 % 5 == 0 && _counter4 < 225) {
			Hitbox a = new HitboxImpl("uball", this, false, _x, _y+37, 75, 75, -15, 0, 0, 1, new Image("ultimoboss/shots/y.png"));
			a.setCircle(true);
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
		}
		if(_counter4 == 225) {
			_rate2 = 3;
			_yvelocity = -40;
		}
		if(_counter4 == 240) {
			_x = 645;
			_yvelocity = 20;
		}
		if(_counter4 >= 240 && _y >= 125) {
			_y = 125;
			_yvelocity = 0;
			_yattack1 = false;
			_counter3 = 0;
			TheGame.clearHitboxes("laser", this);
		}
	}
	
	public void yattack2() {
		_yattack2 = true;
		_counter4 = 0;
		_rate2 = 1;
		_yvelocity = 10;
	}
	
	public void executeYAttack2() {
		if(_counter4 == 15) {
			_yvelocity = 0;
		}
		if(_counter4 >= 15 && _counter4 % 50 == 0 && _counter4 < 385) {
			Hitbox a = new HitboxImpl("uball", this, false, _x, _y, 150, 150, -9, 0, 0, 1, new Image("ultimoboss/shots/y.png"));
			a.setCircle(true);
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
		}
		if(_counter4 >= 15) {
			makeHLaser( _counter4 - 15, 420, "laser");
			makeHLaser( _counter4 - 15, 380, "laser");
			makeHLaser( _counter4 - 15, 340, "laser");
		}
		if(_counter4 == 85) {
			TheGame.clearHitboxes("laser", this);
		}
		if(_counter4 >= 115) {
			makeHLaser( _counter4 - 115, 420, "laser");
			makeHLaser( _counter4 - 115, 380, "laser");
			makeHLaser( _counter4 - 115, 340, "laser");
		}
		if(_counter4 == 185) {
			TheGame.clearHitboxes("laser", this);
		}
		if(_counter4 >= 215) {
			makeHLaser( _counter4 - 215, 420, "laser");
			makeHLaser( _counter4 - 215, 380, "laser");
			makeHLaser( _counter4 - 215, 340, "laser");
		}
		if(_counter4 == 285) {
			TheGame.clearHitboxes("laser", this);
		}
		if(_counter4 >= 315) {
			makeHLaser( _counter4 - 315, 420, "laser");
			makeHLaser( _counter4 - 315, 380, "laser");
			makeHLaser( _counter4 - 315, 340, "laser");
		}
		if(_counter4 == 385) {
			TheGame.clearHitboxes("laser", this);
			_yvelocity = -10;
			_rate2 = 3;
		}
		if(_counter4 == 400) {
			_counter3 = 0;
			_yattack2 = false;
		}
	}
	private void changeform0() {
		if(_counter4 == 1) {
			_rate2 = 1;
		}
		if(_counter4 == 20) {
			TheGame._frontdrops.add(_flash);
		}
		if(_counter4 == 22) {
			_balls.clear();
			_balls.add(new Image("ultimoboss/balls1/1.png"));
			_balls.add(new Image("ultimoboss/balls1/2.png"));
			_balls.add(new Image("ultimoboss/balls1/3.png"));
			_balls.add(new Image("ultimoboss/balls1/4.png"));
			_balls.add(new Image("ultimoboss/balls1/5.png"));
			_balls.add(new Image("ultimoboss/balls1/6.png"));
			_balls.add(new Image("ultimoboss/balls1/7.png"));
			_balls.add(new Image("ultimoboss/balls1/8.png"));
			_balls.add(new Image("ultimoboss/balls1/9.png"));
			_balls.add(new Image("ultimoboss/balls1/10.png"));
			_balls.add(new Image("ultimoboss/balls1/11.png"));
			_balls.add(new Image("ultimoboss/balls1/12.png"));
			_balls.add(new Image("ultimoboss/balls1/13.png"));
			_balls.add(new Image("ultimoboss/balls1/14.png"));
			_balls.add(new Image("ultimoboss/balls1/15.png"));
			_balls.add(new Image("ultimoboss/balls1/16.png"));
			_balls.add(new Image("ultimoboss/balls1/17.png"));
			_balls.add(new Image("ultimoboss/balls1/18.png"));
			_balls.add(new Image("ultimoboss/balls1/19.png"));
			_balls.add(new Image("ultimoboss/balls1/20.png"));
		}
		if(_counter4 == 25) {
			TheGame._frontdrops.remove(_flash);
		}
		if(_counter4 == 40) {
			_rate2 = 3;
			_counter3 = 0;
			_changeform0 = false;
			_form = 0;
		}
		
	}


	private void changeform3() {
		if(_counter4 == 1) {
			_rate2 = 1;
		}
		if(_counter4 == 20) {
			TheGame._frontdrops.add(_flash);
		}
		if(_counter4 == 22) {
			_balls.clear();
			_balls.add(new Image("ultimoboss/balls4/1.png"));
			_balls.add(new Image("ultimoboss/balls4/2.png"));
			_balls.add(new Image("ultimoboss/balls4/3.png"));
			_balls.add(new Image("ultimoboss/balls4/4.png"));
			_balls.add(new Image("ultimoboss/balls4/5.png"));
			_balls.add(new Image("ultimoboss/balls4/6.png"));
			_balls.add(new Image("ultimoboss/balls4/7.png"));
			_balls.add(new Image("ultimoboss/balls4/8.png"));
			_balls.add(new Image("ultimoboss/balls4/9.png"));
			_balls.add(new Image("ultimoboss/balls4/10.png"));
			_balls.add(new Image("ultimoboss/balls4/11.png"));
			_balls.add(new Image("ultimoboss/balls4/12.png"));
			_balls.add(new Image("ultimoboss/balls4/13.png"));
			_balls.add(new Image("ultimoboss/balls4/14.png"));
			_balls.add(new Image("ultimoboss/balls4/15.png"));
			_balls.add(new Image("ultimoboss/balls4/16.png"));
			_balls.add(new Image("ultimoboss/balls4/17.png"));
			_balls.add(new Image("ultimoboss/balls4/18.png"));
			_balls.add(new Image("ultimoboss/balls4/19.png"));
			_balls.add(new Image("ultimoboss/balls4/20.png"));
		}
		if(_counter4 == 25) {
			TheGame._frontdrops.remove(_flash);
		}
		if(_counter4 == 40) {
			_rate2 = 3;
			_counter3 = 0;
			_changeform3 = false;
			_form = 3;
		}
		
	}

	private void changeform2() {
		if(_counter4 == 1) {
			_rate2 = 1;
		}
		if(_counter4 == 20) {
			TheGame._frontdrops.add(_flash);
		}
		if(_counter4 == 22) {
			_balls.clear();
			_balls.add(new Image("ultimoboss/balls3/1.png"));
			_balls.add(new Image("ultimoboss/balls3/2.png"));
			_balls.add(new Image("ultimoboss/balls3/3.png"));
			_balls.add(new Image("ultimoboss/balls3/4.png"));
			_balls.add(new Image("ultimoboss/balls3/5.png"));
			_balls.add(new Image("ultimoboss/balls3/6.png"));
			_balls.add(new Image("ultimoboss/balls3/7.png"));
			_balls.add(new Image("ultimoboss/balls3/8.png"));
			_balls.add(new Image("ultimoboss/balls3/9.png"));
			_balls.add(new Image("ultimoboss/balls3/10.png"));
			_balls.add(new Image("ultimoboss/balls3/11.png"));
			_balls.add(new Image("ultimoboss/balls3/12.png"));
			_balls.add(new Image("ultimoboss/balls3/13.png"));
			_balls.add(new Image("ultimoboss/balls3/14.png"));
			_balls.add(new Image("ultimoboss/balls3/15.png"));
			_balls.add(new Image("ultimoboss/balls3/16.png"));
			_balls.add(new Image("ultimoboss/balls3/17.png"));
			_balls.add(new Image("ultimoboss/balls3/18.png"));
			_balls.add(new Image("ultimoboss/balls3/19.png"));
			_balls.add(new Image("ultimoboss/balls3/20.png"));
		}
		if(_counter4 == 25) {
			TheGame._frontdrops.remove(_flash);
		}
		if(_counter4 == 40) {
			_rate2 = 3;
			_counter3 = 0;
			_changeform2 = false;
			_form = 2;
		}
		
	}

	private void changeform1() {
		if(_counter4 == 1) {
			_rate2 = 1;
		}
		if(_counter4 == 20) {
			TheGame._frontdrops.add(_flash);
		}
		if(_counter4 == 22) {
			_balls.clear();
			_balls.add(new Image("ultimoboss/balls2/1.png"));
			_balls.add(new Image("ultimoboss/balls2/2.png"));
			_balls.add(new Image("ultimoboss/balls2/3.png"));
			_balls.add(new Image("ultimoboss/balls2/4.png"));
			_balls.add(new Image("ultimoboss/balls2/5.png"));
			_balls.add(new Image("ultimoboss/balls2/6.png"));
			_balls.add(new Image("ultimoboss/balls2/7.png"));
			_balls.add(new Image("ultimoboss/balls2/8.png"));
			_balls.add(new Image("ultimoboss/balls2/9.png"));
			_balls.add(new Image("ultimoboss/balls2/10.png"));
			_balls.add(new Image("ultimoboss/balls2/11.png"));
			_balls.add(new Image("ultimoboss/balls2/12.png"));
			_balls.add(new Image("ultimoboss/balls2/13.png"));
			_balls.add(new Image("ultimoboss/balls2/14.png"));
			_balls.add(new Image("ultimoboss/balls2/15.png"));
			_balls.add(new Image("ultimoboss/balls2/16.png"));
			_balls.add(new Image("ultimoboss/balls2/17.png"));
			_balls.add(new Image("ultimoboss/balls2/18.png"));
			_balls.add(new Image("ultimoboss/balls2/19.png"));
			_balls.add(new Image("ultimoboss/balls2/20.png"));
		}
		if(_counter4 == 25) {
			TheGame._frontdrops.remove(_flash);
		}
		if(_counter4 == 40) {
			_rate2 = 3;
			_counter3 = 0;
			_changeform1 = false;
			_form = 1;
		}
		
	}

	@Override
	public void spawn() {
		_y = 125;
		_spawning = true;
		_counter2 = 0;
		//resting  is 545
		_xvelocity = -5;
	}
	
	public void executeSpawn() {
		if(_counter2 == 50) {
			_xvelocity = 0;
			_spawning = false;
		}
	}
	
	private void makeHLaser(int c, int y, String s) {
		if(c == 0) {
			TheGame.playSound("/botboss/sounds/charge.wav");
		}
		if(c < 60) {
			TheGame._gc.drawImage(new Image("ultimoboss/lasers/hpre.png"), 0, y, 900, 22);
		}
		if(c == 60 || c == 61) {
			Hitbox a = new MeleeHitbox(s, this, 0, y, 900, 22, 0, 1, new Image("ultimoboss/lasers/h1.png"));
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			TheGame.playSound("/botboss/sounds/shot.wav");
			if(!_lasershot){
				TheGame.playSound("/botboss/sounds/shot.wav");
				_lasershot = true;
				}
		}
		if(c == 62) {
			_lasershot = false;
		}
	}
	private void makeVLaser(int c, int x, String s) {
		
		if(c == 0) {
			TheGame.playSound("/botboss/sounds/charge.wav");
		}
		if(c < 60) {
			TheGame._gc.drawImage(new Image("ultimoboss/lasers/vpre.png"), x, 60, 22, 900);
		}
		if(c == 60 || c == 61 ) {
			Hitbox a = new MeleeHitbox(s, this, x, 60, 22, 900, 0, 1, new Image("ultimoboss/lasers/v1.png"));
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			if(!_lasershot){
			TheGame.playSound("/botboss/sounds/shot.wav");
			_lasershot = true;
			}
		}
		if(c == 62) {
			_lasershot = false;
		}
	}
	
	@Override
	public void hit(Hitbox h) {
		super.hit(h);
		//if(_health <= 0) {
		//	_health = 1;
		//}
	}

}
