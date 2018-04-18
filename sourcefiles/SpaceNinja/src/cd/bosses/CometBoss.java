package cd.bosses;

import java.util.Random;

import cd.CharLinkedHitbox;
import cd.Hitbox;
import cd.HitboxImpl;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class CometBoss extends Boss {

	
	private Hitbox _body = new CharLinkedHitbox("cometbody", this, 0, 1);
	private int _repeat1;
	private int _repeat2;
	private int _repeat3;
	private int _repeat4;
	private int _drawx;
	private int _repeatno1;
	private int _repeatno2;
	private int _repeatno3;
	private int _repeatno4;
	private Random _random = new Random();
	private int _attack1var;
	
	public CometBoss() {
		super(900, 600, "cometboss");
		_width = (int)(300*1.2);
		_height = (int)(91*1.2);
		_health = 1200;
		_body = new CharLinkedHitbox("cometbody", this, 0, 1);
		_body.setCircle(true);
		_staticimage = new Image("cometboss/1.png");
	}
	
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(_spawning) {
			executeSpawn();
		}
		if(_attack1) {
			executeAttack1();
		}
		
		if(!_attack1 && !_attack2 && !_attack3 && !_attack4 && !_attack5 && !_dead) {
			if(_counter1 < 40) {
				_yvelocity = 0;
				if(_counter % 3 == 0){
				if(_counter1 < 20) {
					_y -=2;
				} else {
					_y += 2;
				}
				}
			} else {
				_counter1 = 0;
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
				i = 0;
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

	@Override
	public void spawn() {
		_y = 150;
		_x = 900;
		_xvelocity = -8;
		_spawning = true;
		_counter2 = 0;
	}
	
	public void executeSpawn() {
		if(_counter2 == 35) {
			_spawning = false;
			_xvelocity = 0;
		}
	}
	
	public void attack1() {
		_counter4 = 0;
		_attack1 = true;
		if(_attack1var == 0){
			_drawx = -300;
		} else {
			_drawx = 900; 
		}
	}
	
	public void executeAttack1() {
		if(_attack1var == 0){
		if(_counter4 == 1) {
			Hitbox a = new HitboxImpl("ice", this, false, _drawx, 47, 400, 400, 6, 0, 0, 1, new Image("cometboss/shot/pre1.png"));
			a.setCircle(true);
			a.setDissappearOnHit(false);
			a.setHarmless(true);
			a.setAutogone(false);
			TheGame._attacks.add(a);
			a = new HitboxImpl("ice", this, false, _drawx-400, 47, 400, 400, 6, 0, 0, 1, new Image("cometboss/shot/pre1.png"));
			a.setCircle(true);
			a.setDissappearOnHit(false);
			a.setHarmless(true);
			a.setAutogone(false);
			TheGame._attacks.add(a);
			a = new HitboxImpl("ice", this, false, _drawx-800, 47, 400, 400, 6, 0, 0, 1, new Image("cometboss/shot/pre1.png"));
			a.setCircle(true);
			a.setDissappearOnHit(false);
			a.setHarmless(true);
			a.setAutogone(false);
			TheGame._attacks.add(a);
			a = new HitboxImpl("ice", this, false, _drawx-1200, 47, 400, 400, 6, 0, 0, 1, new Image("cometboss/shot/pre1.png"));
			a.setCircle(true);
			a.setDissappearOnHit(false);
			a.setHarmless(true);
			a.setAutogone(false);
			TheGame._attacks.add(a);
		}
		if(_counter4 == 200) {
			for(Hitbox a : TheGame._attacks) {
				if(a.getID().equals("ice")) {
					a.setImage(new Image("cometboss/shot/1.png"));
					a.setFreeze(true);
				}
			}
		}
		if(_counter4 == 220) {
			TheGame.clearHitboxes("ice", this);
		}
		if(_counter4 == 230) {
			Hitbox a = new HitboxImpl("ice", this, false, -140, 442 - 46, 150, 46, 35, 0, 0, 1, new Image("cometboss/shot/3.png"));
			a.setCircle(true);
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			_counter3 = 0;
			_attack1 = false;
		}
		} else {
			if(_counter4 == 1) {
				Hitbox a = new HitboxImpl("ice", this, false, _drawx, 47, 400, 400, -7, 0, 0, 1, new Image("cometboss/shot/pre1.png"));
				a.setCircle(true);
				a.setDissappearOnHit(false);
				a.setHarmless(true);
				a.setAutogone(false);
				TheGame._attacks.add(a);
				a = new HitboxImpl("ice", this, false, _drawx+400, 47, 400, 400, -7, 0, 0, 1, new Image("cometboss/shot/pre1.png"));
				a.setCircle(true);
				a.setDissappearOnHit(false);
				a.setHarmless(true);
				a.setAutogone(false);
				TheGame._attacks.add(a);
				a = new HitboxImpl("ice", this, false, _drawx+800, 47, 400, 400, -7, 0, 0, 1, new Image("cometboss/shot/pre1.png"));
				a.setCircle(true);
				a.setDissappearOnHit(false);
				a.setHarmless(true);
				a.setAutogone(false);
				TheGame._attacks.add(a);
				a = new HitboxImpl("ice", this, false, _drawx+1200, 47, 400, 400, -7, 0, 0, 1, new Image("cometboss/shot/pre1.png"));
				a.setCircle(true);
				a.setDissappearOnHit(false);
				a.setHarmless(true);
				a.setAutogone(false);
				TheGame._attacks.add(a);
			}
			if(_counter4 == 200) {
				for(Hitbox a : TheGame._attacks) {
					if(a.getID().equals("ice")) {
						a.setImage(new Image("cometboss/shot/1.png"));
						a.setFreeze(true);
					}
				}
			}
			if(_counter4 == 220) {
				TheGame.clearHitboxes("ice", this);
			}
			if(_counter4 == 230) {
				Hitbox a = new HitboxImpl("ice", this, false, 890, 442 - 46, 150, 46, -35, 0, 0, 1, new Image("cometboss/shot/4.png"));
				a.setCircle(true);
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
				_counter3 = 0;
				_attack1 = false;
			}
		}
		
	}

}
