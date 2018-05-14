package cd.bosses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cd.AnimatedHitbox;
import cd.Backdrop;
import cd.CharLinkedHitbox;
import cd.Hitbox;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.WindowEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class TootBoss2 extends Boss{
	
	private List<Image> _balls = new ArrayList<Image>();
	private int _text1 = 1;
	private Backdrop _flash = new Backdrop(new Image("ultimoboss/flash.png"), 0, 0, 900, 600);
	private List<Image> _spin = new ArrayList<Image>();
	private Hitbox _body = new CharLinkedHitbox("tootbody", this, 0, 1);
	private int _rate2 = 1;
	private boolean _intsongstarted;
	private int _repeat1;
	private int _repeat2;
	private int _repeat3;
	private int _repeat4;
	private int _repeatno1;
	private int _repeatno2;
	private int _repeatno3;
	private int _repeatno4;
	private int _atk1speed = -20;
	private int _atk3count;
	private Hitbox _big1;
	private Hitbox _big2;
	private int _attack4var;
	private Random _random = new Random();
	private boolean _negative;
	private boolean _end;
	private boolean _change2;
	
	public TootBoss2() {
		super(900, 600, "tootboss2");
		_width = 100;
		_height = 100;
		_staticimage = new Image("tootboss2/1.png");
		_body = new CharLinkedHitbox("tootbody", this, 0, 1);
		if(TheGame._beatultimo.equals("t")) {
			_text1 = 16;
			TheGame.setText(new Image("tootboss2/text/intro15.png"));
		}
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
		if(!TheGame._attacks.contains(_body)) {
			TheGame._attacks.add(_body);
		}
		
		if(_counter2 == 0 && !_dead) {
			if(_end) {
				TheGame._beattoot2 = "t";
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
			 if(!_dead && !_spawning) {
				Random r = new Random();
				int i = r.nextInt(8) + 1;
				
				TheGame.setText(new Image("tootboss2/text/fight/" + i + ".png"));
			} 
			
		}
		if(_counter2 == 150 && !_spawning) {
			TheGame.stopText();
			_counter2 = -500;
		}
		if(_health < 0 && !_negative) {
			_negative = true;
			_counter2 = 1;
			_staticimage = new Image("tootboss2/2.png");
			TheGame.setScroll(new Image("scroll/distspace.png"));
			TheGame.stopText();
			TheGame.setText(new Image("tootboss2/text/fight/neg.png"));
			
		}
		if(_health < -500 && !_change2) {
			_change2 = true;
			_staticimage = new Image("tootboss2/3.png");
		}
		if(TheGame._character1.getLives() <= 0 && !_won) {
			_won = true;
			
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
		if(_health <= -1000 && !_end) {
			_counter2 = -200;
			_end = true;
			TheGame._character1.setImmune(true);
			TheGame._character1.setXVelocity(0);
			TheGame._character1.setCanAct(false);
			TheGame.setText(new Image("tootboss2/text/fight/end.png"));
			_staticimage = new Image("tootboss2/4.png");
			
			
		}
		
		if(!_attack1 && !_attack2 && !_attack3 && !_attack4 && !_attack5 && !_dead && !_spawning && !_end) {
			if(_won) {
				_staticimage = new Image("tootboss2/wink.png");
				TheGame.setText(new Image("tootboss2/text/fight/won.png"));
			}
			if(_counter3 == 100 && !_won) {
				Random r = new Random();
				int i = r.nextInt(4);
				_counter3 = 0;
				if(_repeat1 == 2) {
					i = 3;
				}
				if(_repeat2 == 2) {
					i = 2;
				}
				if(_repeat3 == 1) {
					i = 0;
				}
				if(_repeat4 == 2) {
					i = 1;
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
					_attack4var = _random.nextInt(2);
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
			System.out.println("here");
		}
		if(_counter2 == 260 && _text1 < 16) {
			TheGame.setText(new Image("tootboss2/text/intro" +  _text1 + ".png"));
			_text1++;
			_counter2 = 1;
			System.out.println("there");
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
			try {
				TheGame._player.stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TheGame.playStageSong("/songs/toot2.mp3");
		}
		if(_counter2 == 280) {
			TheGame._frontdrops.add(_flash);
			TheGame.playSound("/ultimoboss/sounds/flash.wav");
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
			
			_counter3 = 0;
			_counter2 = 0;
			TheGame._character1.setImmune(false);
			TheGame._character1.setCanAct(true);
		}
	}
	
	public void attack1() {
		_xvelocity = 15;
		_attack1 = true;
		_counter4 = 0;
	}
	
	public void executeAttack1() {
		if(_counter4 == 25) {
			_xvelocity = 0;
		}
		if(_counter4 >= 25 && _counter4 % 4 == 0 && _counter4 < 250) {
			Hitbox a = new AnimatedHitbox("smile", this, true, 900, 60, 75, 75, _atk1speed, 20, 0, 1, _spin, 2);
			a.setBounces(true);
			a.setCircle(true);
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			TheGame.playSound("/ultimoboss/sounds/shot.wav");
		}
		if(_counter4 >= 80 && _counter4 < 141 && _counter4 % 2 == 0) {
			_atk1speed -=1;
		}
		if(_counter4 >= 141 && _counter4 < 220 && _counter4 % 2 == 0){
			_atk1speed +=1;
		}
		if(_counter4 == 240) {
			_xvelocity = -15;
		}
		if(_counter4 == 265) {
			_xvelocity = 0;
			_attack1 = false;
			_counter3 = 0;
			_atk1speed = -20;
		}
	}
	
	public void attack2() {
		_yvelocity = 12;
		_attack2 = true;
		_counter4 = 0;
		
	}
	
	public void executeAttack2() {
		if(_counter4 == 14) {
			_yvelocity = 0;
		}
		if(_counter4 == 30) {
			_xvelocity = 4;
			TheGame.playSound("/spikeboss/sounds/dash.wav");
		}
		if(_counter4 == 45) {
			_xvelocity = -90;
		}
		if(_counter4 > 45 && _x + _width < 0) {
			_x = 900;
		}
		if(_counter4 == 79) {
		    _xvelocity = -8;
		}
		if(_counter4 > 79 && _x <= 645) {
			_x = 645;
			_xvelocity = 0;
			_yvelocity = -12;
		}
		if(_counter4 > 79 && _y <= 125) {
			_y = 125;
			_yvelocity = 0;
			_attack2 = false;
			_counter3 = 0;
		}
		
	}
	
	public void attack3() {
		_yvelocity = 20;
		_counter4 = 0;
		_attack3 = true;
	}
	
	public void executeAttack3() {
		if(_counter4 == 9) {
			_yvelocity = 0;
			Hitbox a = new AnimatedHitbox("smile", this, false, _x, _y+((_width-60)/2), 75, 75, -20, 0, 0, 1, _spin, 2);
			a.setCircle(true);
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			TheGame.playSound("/ultimoboss/sounds/shot.wav");
		}
		if(_counter4 == 24) {
			_yvelocity = -20;
		}
		if(_counter4 == 29) {
			_yvelocity = 0;
			Hitbox a = new AnimatedHitbox("smile", this, false, _x, _y+((_width-60)/2), 75, 75, -20, 0, 0, 1, _spin, 2);
			a.setCircle(true);
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			TheGame.playSound("/ultimoboss/sounds/shot.wav");
		}
		if(_counter4 == 44 && _atk3count < 4) {
			_yvelocity = 20;
			_counter4 = 4;
			_atk3count++;
		}
		if(_counter4 == 46) {
			_yvelocity = -20;
		}
		if(_counter4 >= 46 && _y <=125) {
			_yvelocity = 0;
			_y = 125;
			_atk3count = 0;
			_attack3 = false;
			_counter3 = 0;
		}
		
	}
	
	public void attack4() {
		_yvelocity = -20;
		_counter4 = 0;
		_attack4 = true;
	}
	
	public void executeAttack4() {
		 if(_counter4 == 15) {
			_yvelocity = 0;
		 }
		 if(_counter4 == 25) {
			 if(_attack4var == 0){
			 _big1 = new AnimatedHitbox("bsmile", this, false, 0, -840, 900, 900, 0, 5, 0, 1, _spin, 2);
				_big1.setAutogone(false);
			 	_big1.setCircle(true);
				_big1.setDissappearOnHit(false);
				TheGame._attacks.add(_big1);
			 } else {
				    _big1 = new AnimatedHitbox("bsmile", this, false, 0, -340, 400, 400, 0, 5, 0, 1, _spin, 2);
					_big1.setAutogone(false);
				 	_big1.setCircle(true);
					_big1.setDissappearOnHit(false);
					TheGame._attacks.add(_big1);
					_big2 = new AnimatedHitbox("bsmile", this, false, 450, -340, 400, 400, 0, 5, 0, 1, _spin, 2);
					_big2.setAutogone(false);
				 	_big2.setCircle(true);
					_big2.setDissappearOnHit(false);
					TheGame._attacks.add(_big2);
			 }
		 }
		 if(_counter4 == 50) {
			 _big1.setYVelocity(18);
			 if(_attack4var == 1) {
				 _big2.setYVelocity(18); 
			 }
		 }
		 if(_counter4 == 65) {
			 _big1.setYVelocity(0);
			 if(_attack4var == 1) {
				 _big2.setYVelocity(0); 
			 }
			 TheGame.playSound("/rockboss/sounds/slam.wav");
		 }
		 if(_counter4 == 85) {
			 _big1.setYVelocity(-25);
			 if(_attack4var == 1) {
				 _big2.setYVelocity(-25); 
			 }
		 }
		 if(_counter4 == 110) {
			 TheGame.clearHitboxes("bsmile", this);
			 _yvelocity = 20;
		 }
		 if(_counter4 == 125) {
			 _yvelocity = 0;
			 _counter3 = 0;
			 _attack4 = false;
		 }
	}
	
	@Override
	public void hit(Hitbox h) {
		
		if(!_spawning){
			_health -= h.getDamage();
		}
		
	}

}
