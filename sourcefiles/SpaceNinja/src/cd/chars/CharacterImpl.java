package cd.chars;

import java.util.ArrayList;
import java.util.List;

import cd.EntityImpl;
import cd.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import kf.Hitbox;
import cd.TheGame;

public abstract class CharacterImpl extends EntityImpl implements Character {

	protected Image _image;
	protected boolean _dodged;
	protected Character _otherchar;
	protected double _counter = 0;
	protected double _spawnimmunecounter = 0;
	protected boolean _onplatform = false;
	protected boolean _canact = true;
	protected boolean _canjump1 = true;
	protected boolean _canjump2 = true;
	protected String _facing;
	protected double _damage = 1;
	protected boolean _xtumbling;
	protected boolean _ytumbling;
	protected boolean _canmovex = true;
	protected boolean _canmovey = true;
	protected boolean _pressingr;
	protected boolean _pressingl;
	protected int _width;
	protected int _height;
	protected boolean _immune;
	protected int _cd;
	protected double _damagefactor;
	protected double _speedfactor;
	protected double _ultcharge;
	protected boolean _attacku;
	protected boolean _gravity = true;
	//means this char should be rendered on top
	protected boolean _haspriority = false;
	//true = getX() is left x coordinate false = getX() is right x coordinate
	protected boolean _getxleft = true;
	//if the char can go into the platform;
	protected boolean _cangothroughplatform;
	//for phantoms ult
	protected boolean _dontmove = false;
	private int _lives = 3;
	protected Image _clear = new Image("clear.png");
	protected int _restingwidth;
	protected int _restingheight;
	protected boolean _startedrespawning = false;
	// stores the fact that someone is trying to move as they are being knocked back
	protected double _storedxv;
	protected double _storedyv;
	// if the char can store an xv
	protected boolean _canstore = true;
	
	
	
	protected boolean _intro = false;
	protected List<Image> _sprites = new ArrayList<Image>();
	// cycles through the sprites
			protected int _spritecounter = 0;
			// the index of the current sprite
			protected int _spriteindex = 0;
			protected int _spritecount = 4;
	protected boolean _animate = true;
	protected boolean _canjump3 = true;

	public CharacterImpl(String ID) {
		super(0, 300, ID);
		_ID = ID;
		if (ID.equals("one")) {
			_x = 225;

			_facing = "right";
		}
		if (ID == "two") {
			_x = 625;

			_facing = "left";

		}
		_affectedbygravity = true;
		

	}

	@Override
	public void render(GraphicsContext gc) {
		if(_animate){
		if(_spritecounter % _spritecount == 0) {
			_image = _sprites.get(_spriteindex);
			if(_spriteindex < _spritecount-1) {
				_spriteindex++;
			} else {
				_spriteindex = 0;
			}
		}
		}
		gc.drawImage(_image, _x, _y, _width, _height);
		
		
		
		
			if(_canmovex && !_dontmove) {
				_x += (_xvelocity*TheGame._timescale);
			}
			if(_canmovey) {
				_y+= (_yvelocity*TheGame._timescale);
			}
		
		
		if(_canact && _storedxv != 0) {
			_xvelocity = _storedxv;
			_storedxv = 0;
		}
		


	}

	public void setXVelocity(double x) {
		_xvelocity = x;
	}

	public void setYVelocity(double y) {
		_yvelocity = y;
	}

	public void move() {

		
		if(_pressingr && !_pressingl && _canact) {
			_xvelocity = 5.5 * _speedfactor;
		}
		if(_pressingl && !_pressingr && _canact) {
			_xvelocity = -5.5 * _speedfactor;
		}
		
		for(Platform p : TheGame.getPlatforms()){
			if ((_x + _width + _xvelocity < 930 && _x - 3 + _xvelocity > -30)) {
				if ((_y > p.getY() - _height && (_x + _xvelocity > p.getX() - _width && _x + _xvelocity < (p.getX() + p.getWidth())))) {
					_canmovex = false;
				} else {
					if(TheGame.getPlatforms().indexOf(p) ==0) {
					_canmovex = true;
					}
				}
			} else {
				if(TheGame.getPlatforms().indexOf(p) ==0) {
					_canmovex = false;
					}
			}
			
			if ((_x > p.getX() - _width && _x < (p.getX() + p.getWidth())&& _y > p.getY() - _height)) {
			
					if(_yvelocity < 0) {
					if(_canmovey == false){
						if(TheGame.getPlatforms().indexOf(p)==0){
						_canmovey = true;
						}
					}
					} else if(_y <= p.getY()) {
						_canmovey = false;
						_y = p.getY() - _height;
					}

	
			} else if ((_x > p.getX() - _width && _x < (p.getX() + p.getWidth()) && _y + _yvelocity > p.getY() - _height)) {
				_onplatform = true;
				_yvelocity = 0;
				_canjump1 = true;
				_canjump2 = true;
				_canjump3  = true;
				_canmovey = false;
			} else {
				if(TheGame.getPlatforms().indexOf(p) ==0) {
					_onplatform = false;
				}
				if(TheGame.getPlatforms().indexOf(p) ==0) {
					_canmovey = true;
					
				}
			}
		}
		
		
		
	
	}

	public void jump1() {
		if (_canjump1) {
			_yvelocity = -13;
		}
		_canjump1 = false;
	}

	public void jump2() {
		if (_canjump2) {
			_yvelocity = -13;
		}
		_canjump2 = false;
	}

	public boolean isOnPlatform() {
		return _onplatform;
	}

	@Override
	public boolean isCanJump1() {
		// TODO Auto-generated method stub
		return _canjump1;
	}

	@Override
	public boolean isCanJump2() {
		// TODO Auto-generated method stub
		return _canjump2;
	}

	abstract public void attack1();

	abstract public void attack2();

	abstract public void attack3();

	abstract public void attackU();

	public void hit(Hitbox h) {
		// if(h.getOrientation.equals(horizontal) {
		if (!_immune) {
			_xtumbling = true;
			_ytumbling = true;
			_canact = false;
			_canjump2 = true;
			_xvelocity = 0;
			int xdirection = 0;
			int ydirection = 0;
			if(h.isFreezeY()){
				_gravity = false;
				_yvelocity = 0;
				
			}
			// if (h.getX() < _x) {
			xdirection = 1;
			// }
			// if (h.getX() > _x) {
			xdirection = -1;
			// }

			// if (h.getClass().toString().equals("class kf.MeleeHitbox")) {
			
			 if(h.isForceRight()) {
				xdirection = 1;
				System.out.println("right");
			} else if(h.isForceLeft()){
				xdirection = -1;
				System.out.println("left");
			
			
			
		} else if (!h.getHOrientation()) {
				if (h.getCharacter().getX() < _x) {
					xdirection = 1;
				}
				if (h.getCharacter().getX() > _x) {
					xdirection = -1;
				
				}
			
			
			
			} else {
				if (h.getX() < _x) {
					xdirection = 1;
				}
				if (h.getX() > _x) {
					xdirection = -1;
				}
			}
			// }

			_damage += h.getDamage();
			if (!_attacku && !h.getCharacter().isAttackU()) {
				_ultcharge += h.getDamage() / 3;
				h.getCharacter().addUltCharge(h.getDamage() / 6);
			}
			if(!h.isSetKnockback()) {
				_xvelocity = _damagefactor * xdirection * h.getKnockback() * (0.006 * (_damage));
				
			} else {
				_xvelocity = xdirection * h.getKnockback();
				System.out.println(xdirection);
			}
		} else {
			_dodged = true;
		}
		// _damage+= h.getDamage();
	}

	@Override
	public boolean isXTumbling() {
		return _xtumbling;
	}

	@Override
	public boolean isYTumbling() {
		return _ytumbling;
	}

	public boolean isCanAct() {
		return _canact;
	}
	
	public void setCanAct(boolean b) {
		_canact = b;
	}

	public double getDamage() {
		return _damage;
	}

	public void incrementCounter() {
		_counter++;
		_spawnimmunecounter++;
		_spritecounter++;

	}

	public void setFacing(String s) {
		_facing = s;
	}

	public boolean isGone() {
		return _y > 600;
	}

	public void setX(int x) {
		_x = x;

	}

	public void setY(int y) {
		_y = y;
	}

	public void setDamage(double x) {
		_damage = x;
	}

	public void die() {
		_lives--;
	}

	public int getLives() {
		return _lives;
	}

	public void respawn() {
		
		_damage = 1;
		_xvelocity = 0;
		_yvelocity = 0;
		_y = 300;
		if (_ID.equals("one")) {
			_x = 225;
		}
		if (_ID.equals("two")) {
			_x = 625;
		}
		_spawnimmunecounter = 0;
		_immune = true;
		//_ultcharge = 0;
		_canact = true;

	}

	@Override
	public boolean isCharging1() {
		// TODO Auto-generated method stub
		return false;
	}

	abstract public Color getColor();

	public int getWidth() {
		return _width;
	}

	public int getHeight() {
		return _height;
	}

	public double getSpeedFactor() {
		return _speedfactor;
	}

	public void addUltCharge(double d) {
		_ultcharge += d;
	}

	public double getUltCharge() {
		return _ultcharge;
	}

	public boolean isAttackU() {
		return _attacku;
	}

	public void setOtherChar(Character c) {
		_otherchar = c;
	}

	public boolean isImmune() {
		return _immune;
	}

	public boolean isGravity() {
		return _gravity;
	}

	public void pressUp() {

	}

	public void pressDown() {

	}

	public void pressRight() {
		if(!_canact && _canstore) {
			_storedxv = 5.5 * _speedfactor;
		} else {
		_facing = "right";
		_pressingr = true;
		if(_pressingl && _canact) {
			_xvelocity = 5.5 * _speedfactor;
		}
		}
	}

	public void pressLeft() {
		if(!_canact && _canstore) {
			_storedxv = -5.5 * _speedfactor;
		} else {
		_pressingl = true;
		_facing = "left";
		if(_pressingr && _canact) {
			_xvelocity = -5.5 * _speedfactor;
		}
		}
	}
	
	public void releaseRight(){
		if(_canact && !_pressingl) {
			_xvelocity = 0;
			
		}
		_pressingr = false;
		_storedxv = 0;
	}
	
	public void releaseLeft(){
		if(_canact && !_pressingr) {
			_xvelocity = 0;
			
		}
		_pressingl = false;
		_storedxv = 0;
	}

	public void pressJump() {
		if(_canact) {
		if (_canjump1) {
			jump1();
		} else {
			jump2();
		}
		}
	}

	public void pressAttack1() {
		if(_canact) {
		attack1();
		}
	}

	public void pressAttack2() {
		if(_canact) {
		attack2();
		}
	}

	public void pressAttack3() {
		if(_canact) {
		attack3();
		}
	}

	public void pressAttackU() {
		if(_canact) {
		attackU();
		}
	}

	public void releaseUp() {

	}

	public void releaseDown() {

	}

	public void releaseJump() {

	}
	
	public boolean isHasPriority() {
		return _haspriority;
	}
	
	@Override
	public int getX() {
		if(_getxleft) {
			return _x;
		} else {
			return _x+_width;
		}
	}
	public int getRealX() {
		return _x;
	}
	public int getRealY() {
		return _y;
	}
	
	public void setCanStore(boolean b) {
		_canstore = b;
	}
	
	public void setGravity(boolean b) {
		_gravity = b;
	}
	
	public void intro() {
		_intro = true;
		_counter = 0;
		_canact = false;
	}
	
	public void doIntro() {
		
	}
	
	public void stopIntro() {
		_intro = false;
		_canact = true;
		
	}
	
	public void setCounter(double i){
		_counter = i;
	}
	
	public void setXTumbling(boolean b) {
		_xtumbling = b;
	}
	
	public void setImage(Image i) {
		_image = i;
	}
	
	abstract public String getSkin();
	
	
	
}