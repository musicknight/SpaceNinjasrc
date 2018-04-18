package cd.bosses;

import java.util.Random;

import cd.CharLinkedHitbox;
import cd.Hitbox;
import cd.HitboxImpl;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class DemonBoss extends Boss {

	private int _repeat1;
	private int _repeat2;
	private int _repeat3;
	private int _repeat4;
	private int _repeatno1;
	private int _repeatno2;
	private int _repeatno3;
	private int _repeatno4;
	private int _charx;
	private int _chary;
	private int _a3count;
	private Random _random = new Random();
	private int _attack1var;
	private Hitbox _body = new CharLinkedHitbox("demonbody", this, 0, 1);
	private int _attack2var;

	public DemonBoss() {
		super(900, 600, "demonboss");
		_width = (int)(263*(0.8));
		_height = (int)(268*(0.8));
		_health = 1300;
		_staticimage = new Image("demonboss/1.png");
		_circle = true;
		_body = new CharLinkedHitbox("demonbody", this, 0, 1);
		_body.setCircle(true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GraphicsContext gc) {
		if(!_dead){
		TheGame._gc.drawImage(new Image("demonboss/horns.png"), _x, _y-105, (int)(263*.8), 160);
		} else {
			TheGame._gc.drawImage(new Image("demonboss/horns2.png"), _x, _y-105, (int)(263*.8), 160);
		}
		super.render(gc);
		if(_counter2 == 0 && !_won && !_dead) {
			int i = _random.nextInt(6) + 1;
			TheGame.setText(new Image("demonboss/text/" + i + ".png"));
			
		}
		if(_counter2 == 150) {
			TheGame.stopText();
			_counter2 = -500;
		}
		if(!TheGame._attacks.contains(_body)) {
			TheGame._attacks.add(_body);
		}
		if(_spawning) {
			executeSpawn();
		}
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
			executeAttack4();
		}
		if(TheGame._character1.getLives() <= 0) {
			_won = true;
		}
		if(_health == 0 && !_dead) {
			_attack1 = false;
			_attack2 = false;
			_attack3 = false;
			_attack4 = false;
			_counter2 = -1;
			_dead = true;
			_staticimage = new Image("demonboss/dead.png");
		}
		if(_dead) {
			TheGame.setText(new Image("demonboss/text/dead.png"));
			_xvelocity = 0;
			_yvelocity = 3;
		}
		if(!_attack1 && !_attack2 && !_attack3 && !_attack4 && !_attack5 && !_dead && !_spawning) {
		if(_counter1 < 29) {
			_yvelocity = 0;
			if(_counter % 3 == 0){
			if(_counter1 < 15) {
				_y -=1;
			} else {
				_y += 1;
			}
			}
		} else {
			_counter1 = 0;
		}
		if(_won) {
			if(!TheGame._character1.getSkin().equals("black")) {
				TheGame.setText(new Image("demonboss/text/won.png"));
			} else {
				TheGame.setText(new Image("demonboss/text/kill.png"));
			}
			
			_staticimage = new Image("demonboss/won.png");
		}
		if(_counter3 == 120 && !_won) {
			Random r = new Random();
			int i = r.nextInt(4);
			_counter3 = 0;
			
			if(_repeat1 == 2) {
				i = 3;
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
				i = 3;
			}
			if(i == 0) {
				_attack1var = _random.nextInt(2);
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
				_attack2var = _random.nextInt(2);
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
				attack4();
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
	
	
	
	
	@Override
	public void spawn() {
		_spawning = true;
		_x = 348;
		_y = 0;
		if(TheGame._character1.getSkin().equals("black")) {
			TheGame.setText(new Image("demonboss/text/kill.png"));
		} else {
			TheGame.setText(new Image("demonboss/text/spawn.png"));
		}
		// resting y = 108
		_yvelocity = 3;
		_counter2 = 1;
	}
	
	public void executeSpawn() {
		if(_counter2 == 35) {
			if(TheGame._character1.getSkin().equals("black")) {
				TheGame._character1.setLives(1);
				TheGame._character1.hit(_body);
			}
			_yvelocity = 0;
			_spawning = false;
		}
	}
	
	public void attack1() {
		_yvelocity = -15;
		_attack1 = true;
		_counter4 = 0;
	}
	
	public void executeAttack1() {
		 if(_counter4 == 25) {
			 _x = 607;
			 _yvelocity = 15;
		 }
		 if(_counter4 == 50) {
			 _yvelocity = 0;
		 }
		 if(_counter4 == 50) {
			 if(_attack1var == 0) {
				 Hitbox a = new HitboxImpl("pentagram", this, false, 899, 60, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a); a = new HitboxImpl("pentagram", this, false, 899, 145, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 a = new HitboxImpl("pentagram", this, false, 899, 315, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 a = new HitboxImpl("pentagram", this, false, 899, 400, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
			 } else {
				 Hitbox a = new HitboxImpl("pentagram", this, false, 899, 60, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a); a = new HitboxImpl("pentagram", this, false, 899, 315, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 a = new HitboxImpl("pentagram", this, false, 899, 230, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 a = new HitboxImpl("pentagram", this, false, 899, 400, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
			 }
		 }
		 if(_counter4 == 90) {
			 if(_attack1var == 0){
			 Hitbox a = new HitboxImpl("pentagram", this, false, 899, 60, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a); a = new HitboxImpl("pentagram", this, false, 899, 145, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 
			 a = new HitboxImpl("pentagram", this, false, 899, 230, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 } else {
				 Hitbox a = new HitboxImpl("pentagram", this, false, 899, 60, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a); a = new HitboxImpl("pentagram", this, false, 899, 145, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 a = new HitboxImpl("pentagram", this, false, 899, 230, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 a = new HitboxImpl("pentagram", this, false, 899, 400, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a); 
			 }
		 }
		 if(_counter4 == 130) {
			 if(_attack1var == 0){
			 Hitbox a = new HitboxImpl("pentagram", this, false, 899, 60, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a); a = new HitboxImpl("pentagram", this, false, 899, 145, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 a = new HitboxImpl("pentagram", this, false, 899, 230, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 a = new HitboxImpl("pentagram", this, false, 899, 400, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 } else {
				 Hitbox a = new HitboxImpl("pentagram", this, false, 899, 60, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a); a = new HitboxImpl("pentagram", this, false, 899, 145, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 
				 a = new HitboxImpl("pentagram", this, false, 899, 230, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a); 
			 }
		 }
		 if(_counter4 == 170) {
			 if(_attack1var == 0){
			 Hitbox a = new HitboxImpl("pentagram", this, false, 899, 60, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a); a = new HitboxImpl("pentagram", this, false, 899, 315, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 a = new HitboxImpl("pentagram", this, false, 899, 230, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 a = new HitboxImpl("pentagram", this, false, 899, 400, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 } else {
				 Hitbox a = new HitboxImpl("pentagram", this, false, 899, 60, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a); a = new HitboxImpl("pentagram", this, false, 899, 145, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 a = new HitboxImpl("pentagram", this, false, 899, 315, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 a = new HitboxImpl("pentagram", this, false, 899, 400, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
			 }
		 }
		 if(_counter4 == 210) {
			 if(_attack1var == 0) {
				 Hitbox a = new HitboxImpl("pentagram", this, false, 899, 60, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a); 
				 a = new HitboxImpl("pentagram", this, false, 899, 145, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 
				 a = new HitboxImpl("pentagram", this, false, 899, 230, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a); 
			 } else {
				 Hitbox a = new HitboxImpl("pentagram", this, false, 899, 60, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a); 
				 a = new HitboxImpl("pentagram", this, false, 899, 145, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 a = new HitboxImpl("pentagram", this, false, 899, 230, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 a = new HitboxImpl("pentagram", this, false, 899, 400, 85, 85, -10, 0, 0, 1, new Image("demonboss/shot/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
			 }
		 }
		 if(_counter4 == 275) {
			 _yvelocity = -15;
		 }
		 if(_counter4 == 300) {
			 _yvelocity = 15;
			 _x = 348;
			 
		 }
		 if(_counter4 == 325) {
			 _yvelocity = 0;
			 _attack1 = false;
			 _counter3 = 0;
		 }
		 
	}
	public void attack2() {
		
		_counter4 = 0;
		_attack2 = true;
	}
	
	public void executeAttack2() {
		if(_counter4 >= 0 && _counter4 < 30) {
			if(_attack2var == 0) {
				TheGame._gc.drawImage(new Image("demonboss/big/pre.png"), 0, 439);
				TheGame._gc.drawImage(new Image("demonboss/big/pre.png"), 720, 439);
			} else {
				TheGame._gc.drawImage(new Image("demonboss/big/pre.png"), 360, 439);
			}
		}
		if(_counter4 == 30) {
			if(_attack2var == 0){
			Hitbox a = new HitboxImpl("pentagram", this, true, 0, 443, 180, 180, 0, -37, 0, 1, new Image("demonboss/big/1.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 a = new HitboxImpl("pentagram", this, true, 720, 443, 180, 180, 0, -37, 0, 1, new Image("demonboss/big/1.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			} else {
				Hitbox a = new HitboxImpl("pentagram", this, true, 360, 443, 180, 180, 0, -37, 0, 1, new Image("demonboss/big/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
			}
		}
		if(_counter4 == 60) {
			Hitbox a = new HitboxImpl("pentagram", this, true, 180, 443, 180, 180, 0, -37, 0, 1, new Image("demonboss/big/1.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 a = new HitboxImpl("pentagram", this, true, 540, 443, 180, 180, 0, -37, 0, 1, new Image("demonboss/big/1.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			
		}
		if(_counter4 == 90) {
			if(_attack2var == 0){
			Hitbox a = new HitboxImpl("pentagram", this, true, 360, 443, 180, 180, 0, -37, 0, 1, new Image("demonboss/big/1.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			
			} else {
				Hitbox a = new HitboxImpl("pentagram", this, true, 0, 443, 180, 180, 0, -37, 0, 1, new Image("demonboss/big/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
				 a = new HitboxImpl("pentagram", this, true, 720, 443, 180, 180, 0, -37, 0, 1, new Image("demonboss/big/1.png"));
				 a.setCircle(true);
				 a.setDissappearOnHit(false);
				 TheGame._attacks.add(a);
			}
		}
		if(_counter4 == 120) {
			_attack2 = false;
			_counter3 = 0;
		}
		
	}
	public void attack3() {
		_attack3 = true;
		_counter4 = -20;
	}
	
	public void executeAttack3() {
		int x = TheGame._character1.getX();
		int y = TheGame._character1.getY();
		if(_counter4 < 30) {
			TheGame._gc.drawImage(new Image("demonboss/big/pre2.png"), x-65, y-65);
		}
		if(_counter4 == 30) {
			_charx = x;
			_chary = y;
		}
		if(_counter4 >= 30 && _counter4 < 50) {
			TheGame._gc.drawImage(new Image("demonboss/big/pre1.png"), _charx-65, _chary-65);
		}
		if(_counter4 == 50) {
			Hitbox a = new HitboxImpl("pentagram", this, false, _charx-65, _chary-65, 180, 180, 0, 0, 0, 1, new Image("demonboss/big/1.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
		}
		if(_counter4 == 57 && _a3count == 5) {
			TheGame.clearHitboxes("pentagram", this);
		}
		if(_counter4 == 60 && _a3count < 5) {
			_counter4 = 0;
			_a3count++;
		}
		if(_counter4 == 61) {
			_attack3 = false;
			_counter3 = 0;
			_a3count = 0;
		}
	}
	
	public void attack4() {
		_counter4 = 0;
		_attack4 = true;
	}
	
	public void executeAttack4() {
		int x = TheGame._character1.getX();
		int y = TheGame._character1.getY();
		if(_counter4 == 1) {
			Hitbox a = new HitboxImpl("pentagram1", this, false, x-12, 60, 75, 75, 0, 0, 0, 1, new Image("demonboss/shot/1.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
			 a = new HitboxImpl("pentagram2", this, false, 0, y-12, 75, 75, 0, 0, 0, 1, new Image("demonboss/shot/1.png"));
			 a.setCircle(true);
			 a.setDissappearOnHit(false);
			 TheGame._attacks.add(a);
		}
		if(_counter4 < 50){
		for(Hitbox a : TheGame._attacks) {
			if(a.getID().equals("pentagram1")) {
				a.setX(x-12);
			}
			if(a.getID().equals("pentagram2")) {
				a.setY(y-12);
			}
		}
		}
		if(_counter4 == 50) {
			for(Hitbox a : TheGame._attacks) {
				if(a.getID().equals("pentagram1")) {
					a.setYVelocity(20);
				}
				if(a.getID().equals("pentagram2")) {
					a.setXVelocity(20);
				}
			}
		}
		if(_counter4 == 60) {
			_attack4 = false;
			_counter3 = 0;
		}
	}
}
