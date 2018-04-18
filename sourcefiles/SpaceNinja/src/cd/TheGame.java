package cd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import cd.bosses.Boss;
import cd.bosses.BotBoss;
import cd.bosses.CometBoss;
import cd.bosses.DemonBoss;
import cd.bosses.DragonBoss;
import cd.bosses.GhostBoss;
import cd.bosses.RockBoss;
import cd.bosses.RockBoss2;
import cd.bosses.SkullBoss;
import cd.bosses.SpikeBoss;
import cd.bosses.SpikeBoss2;
import cd.bosses.TootBoss;
import cd.bosses.TwinsBoss;
import cd.bosses.UltimoBoss;
import cd.chars.Ninja1Char;
import cd.chars.CDCharacter;
import cd.chars.Character;
import cd.chars.CharacterImpl;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import kf.characters.BuuChar;
import kf.characters.CloudChar;
import kf.characters.EnchantChar;
import kf.characters.FefnirChar;
import kf.characters.FrankChar;
import kf.characters.GengarChar;
import kf.characters.GokuChar;
import kf.characters.JadenChar;
import kf.characters.LeviChar;
import kf.characters.MermaidChar;
import kf.characters.MetaChar;
import kf.characters.NarutoChar;
import kf.characters.NinjaChar;
import kf.characters.PhantomChar;
import kf.characters.SansChar;
import kf.characters.ShovelChar;
import kf.characters.SpecterChar;
import kf.characters.ZeroChar;
import kf.stages.CampStage;
import kf.stages.DarkMindStage;
import kf.stages.DrawciaStage;
import kf.stages.EmeraldStage;
import kf.stages.FDStage;
import kf.stages.FountainStage;
import kf.stages.GiygasStage;
import kf.stages.MoonStage;
import kf.stages.Place;
import kf.stages.PokemonStage;
import kf.stages.SansStage;
import kf.stages.TowerStage;
import kf.stages.WilyStage;

public class TheGame extends Application {
	public static void main(String[] args) {
		new JFXPanel();
		launch(args);
	}

	public static CDCharacter _character1;
	private String _skin;
	public static Boss _boss;
	private boolean _bossspawned;
	public static Place _place;
	private static Canvas _canvas = new Canvas(900, 600);
	
	private boolean _songplaying;
	private boolean _vsongplaying;
	public static GraphicsContext _gc = _canvas.getGraphicsContext2D();
	public static GraphicsContext _gc2 = _canvas.getGraphicsContext2D();
	private Stage _stage;
	private Scene _scene;
	private AnimationTimer _animationTimer;
	public static Set<Hitbox> _attacks = new HashSet<Hitbox>();
	public static List<Platform> _platforms = new ArrayList<Platform>();
	public static List<Backdrop> _backdrops = new ArrayList<Backdrop>();
	public static List<Backdrop> _frontdrops = new ArrayList<Backdrop>();
	private Image _scroll = new Image("scroll/space.png");
	private int _scrollc = 0;
	private boolean _charpicked = false;
	private boolean _bosspicked = false;
	private static boolean _closed = false;
	
	//game start button
	private Button _start = new Button("start");
	
	private Button _return = new Button("back to main menu");
	
	private Button _continue = new Button("continue");
	//next/prev page button
	private Group _root1 = new Group();
	public static boolean _rendering = true;
	private int _page = 1;
	private boolean _menusongstarted= false;
	private boolean _titlesongstarted = false;
	private boolean _gamestarted = false;
	
	private Button _white = new Button("select");
	private Button _red= new Button("select");
	private Button _green= new Button("select");
	private Button _yellow= new Button("select");
	private Button _rock= new Button("select");
	private Button _black = new Button("select");
	private Button _spike = new Button("select");
	private Button _lasers = new Button("select");
	private Button _rock2 = new Button("select");
	private Button _dragon = new Button("select");
	private Button _skull = new Button("select");
	private Button _twins = new Button("select");
	private Button _spike2 = new Button("select");
	private Button _demon = new Button("select");
	
	private Button _toot = new Button("fight!");
	private Button _crush = new Button("fight!");
	private Button _spiball = new Button("fight!");
	private Button _swurli = new Button("fight!");
	private Button _laser = new Button("fight!");
	private Button _crunch = new Button("fight!");
	private Button _droth = new Button("fight!");
	private Button _cranius = new Button("fight!");
	private Button _candm = new Button("fight");
	private Button _spiball2 = new Button("fight");
	private Button _nero = new Button("fight");
	
	
	private Button _ultimo = new Button("fight");
	
	private String _beattoot;
	private String _beatswurli;
	private String _beatcrush;
	private String _beatspiball;
	private String _beatlaser;
	private String _beatcrunch;
	private String _beatdroth;
	private String _beatcranius;
	private String _beatcandm;
	private String _beatspiball2;
	private String _beatnero;
	public static  String _1stultimo;
	public static String _beatultimo;
	
	private static Image _text;
	
	private Button _replaybutton = new Button();
	private boolean _gotpower;
	private String _power;
	private String _newskin;

	public static double _gravity = 0.8;
	
	public static BasicPlayer _player = new BasicPlayer();

	public void start(Stage stage1) {
		FXMLLoader loader = new
		 FXMLLoader(this.getClass().getResource("watchout.fxml"));
		 loader.setController(this);
		
		stage1.setTitle("SpaceNinja");
		_stage = stage1;
		TheGame m = this;
		//_stage.setOnCloseRequest(m::closeWindow);
		_root1 = new Group();
		_scene = new Scene(_root1);
		_scene.setFill(Color.BLACK);
		stage1.setScene(_scene);
		_stage.setOnCloseRequest(m::closeWindow);
		_root1.getChildren().add(_canvas);
		Platform p = new PlatformImpl(0, 442, 900, 158);
		p.setImage(new Image("clear.png"));
		_platforms.add(p);
		String file ="data.txt";
		FileReader f = null;
		try {
			f = new FileReader(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int i = 1;
	     
	     BufferedReader reader = new BufferedReader(f);
		try {
			_beattoot = read(reader);
			_beatswurli = read(reader);
			_beatcrush = read(reader);
			_beatspiball = read(reader);
			_beatlaser = read(reader);
			_beatcrunch = read(reader);
			_beatdroth = read(reader);
			_beatcranius = read(reader);
			_beatcandm = read(reader);
			_beatspiball2 = read(reader);
			_beatnero = read(reader);
			_1stultimo = read(reader);
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(_beattoot == null) {
			_beattoot = "f";
		}
		if(_beatswurli == null) {
			_beatswurli = "f";
		}
		if(_beatcrush == null) {
			_beatcrush = "f";
		}
		if(_beatspiball == null) {
			_beatspiball = "f";
		}
		if(_beatlaser == null) {
			_beatlaser = "f";
		}
		if(_beatcrunch == null) {
			_beatcrunch = "f";
		}
		if(_beatdroth == null) {
			_beatdroth = "f";
		}
		if(_beatcranius == null) {
			_beatcranius = "f";
		}
		if(_beatcandm == null) {
			_beatcandm = "f";
		}
		if(_beatspiball2 == null) {
			_beatspiball2 = "f";
		}
		if(_beatnero == null) {
			_beatnero = "f";
		}
		
		// character selection screen

		// _player1picked = true;

		// _player1picked = true;
		// Group root = new Group();
		// _scene = new Scene(root);
		// _scene.setFill(Color.BLACK);
		// stage1.setScene(_scene);

		// root.getChildren().add(_canvas);

		final long startNanoTime = System.nanoTime();

		_animationTimer = new AnimationTimer() {

			private long _last = 0;

			public void handle(long currentNanoTime) {
				long t = (currentNanoTime - _last);
				if (t > 50 ) {
					_last = currentNanoTime;
				
				if(_gotpower) {
					_gc.drawImage(new Image("text/augtext/base.png"), 0, 0);
					_gc.drawImage(new Image("text/augtext/" + _power + ".png"), 100, 344);
					_gc.drawImage(new Image("ninja/" + _newskin + "/run1.png"), 334, 116, 200, 200);
					if (!_root1.getChildren().contains(_continue)) {
						_continue.setMinWidth(200);
						_continue.setMinHeight(50);
						_continue.setLayoutX(334);
						_continue.setLayoutY(563);
						_root1.getChildren().add(_continue);

						_continue.setOnMousePressed(m::handleButtonPress);
					}
				} else if(!_charpicked) {
					if(!_songplaying){
					playStageSong("/songs/theme.mp3");
					_songplaying = true;
					}
					_gc.drawImage(new Image("text/charscreen.png"), 0, 0);
					int x = 900;
					int y = 77;
					int x1 = 0;
					int y1 = 600;
if(_beatnero.equals("f")){
	y1 = 185;
if(_beatspiball2.equals("f")){
	x = 786;
if(_beatcandm.equals("f")){
	 x = 717;
	if(_beatcranius.equals("f")){
		x = 650;
		if(_beatdroth.equals("f")) {	
			x = 590;
			if(_beatcrunch.equals("f")){
				x = 520;
				if(_beatlaser.equals("f")){
					x = 464;
					if(_beatspiball.equals("f")){
						x = 400;
						if(_beatcrush.equals("f")) {
							x = 345;
							if(_beatswurli.equals("f")) {
								x = 280;
								if(_beattoot.equals("f")) {
									x = 220;
								}
							}
						}
					}
				}
			}
		}
	}
}
}
}
					_gc.drawImage(new Image("text/black.png"), x, y);
					_gc.drawImage(new Image("text/black.png"), x1, y1);
					if (!_root1.getChildren().contains(_white)) {
						_white.setMinWidth(45);
						_white.setMinHeight(12);
						_white.setLayoutX(46);
						_white.setLayoutY(151);
						_root1.getChildren().add(_white);

						_white.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_red)) {
						_red.setMinWidth(45);
						_red.setMinHeight(12);
						_red.setLayoutX(108);
						_red.setLayoutY(151);
						_root1.getChildren().add(_red);

						_red.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_green)) {
						_green.setMinWidth(45);
						_green.setMinHeight(12);
						_green.setLayoutX(168);
						_green.setLayoutY(151);
						_root1.getChildren().add(_green);

						_green.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_yellow) && _beattoot.equals("t")) {
						_yellow.setMinWidth(45);
						_yellow.setMinHeight(12);
						_yellow.setLayoutX(231);
						_yellow.setLayoutY(151);
						_root1.getChildren().add(_yellow);

						_yellow.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_rock) && _beatcrush.equals("t")) {
						_rock.setMinWidth(45);
						_rock.setMinHeight(12);
						_rock.setLayoutX(354);
						_rock.setLayoutY(151);
						_root1.getChildren().add(_rock);

						_rock.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_black) && _beatswurli.equals("t")) {
						_black.setMinWidth(45);
						_black.setMinHeight(12);
						_black.setLayoutX(292);
						_black.setLayoutY(151);
						_root1.getChildren().add(_black);

						_black.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_spike) && _beatspiball.equals("t")) {
						_spike.setMinWidth(45);
						_spike.setMinHeight(12);
						_spike.setLayoutX(414);
						_spike.setLayoutY(151);
						_root1.getChildren().add(_spike);

						_spike.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_lasers) && _beatlaser.equals("t")) {
						_lasers.setMinWidth(45);
						_lasers.setMinHeight(12);
						_lasers.setLayoutX(474);
						_lasers.setLayoutY(151);
						_root1.getChildren().add(_lasers);

						_lasers.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_rock2) && _beatcrunch.equals("t")) {
						_rock2.setMinWidth(45);
						_rock2.setMinHeight(12);
						_rock2.setLayoutX(537);
						_rock2.setLayoutY(151);
						_root1.getChildren().add(_rock2);

						_rock2.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_dragon) && _beatdroth.equals("t")) {
						_dragon.setMinWidth(45);
						_dragon.setMinHeight(12);
						_dragon.setLayoutX(600);
						_dragon.setLayoutY(151);
						_root1.getChildren().add(_dragon);

						_dragon.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_skull) && _beatcranius.equals("t")) {
						_skull.setMinWidth(45);
						_skull.setMinHeight(12);
						_skull.setLayoutX(664);
						_skull.setLayoutY(151);
						_root1.getChildren().add(_skull);

						_skull.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_twins) && _beatcandm.equals("t")) {
						_twins.setMinWidth(45);
						_twins.setMinHeight(12);
						_twins.setLayoutX(735);
						_twins.setLayoutY(151);
						_root1.getChildren().add(_twins);

						_twins.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_spike2) && _beatspiball2.equals("t")) {
						_spike2.setMinWidth(45);
						_spike2.setMinHeight(12);
						_spike2.setLayoutX(795);
						_spike2.setLayoutY(151);
						_root1.getChildren().add(_spike2);

						_spike2.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_demon) && _beatnero.equals("t")) {
						_demon.setMinWidth(45);
						_demon.setMinHeight(12);
						_demon.setLayoutX(46);
						_demon.setLayoutY(235);
						_root1.getChildren().add(_demon);

						_demon.setOnMousePressed(m::handleButtonPress);
					}
					
				} else if(!_bosspicked) {
					_gc.drawImage(new Image("text/bossscreen.png"), 0, 0);
					List<Node> remove = new ArrayList<Node>();
					for(Node b : _root1.getChildren()) {
						if(b.getClass().toString().equals("class javafx.scene.control.Button")) {
							remove.add(b);
						}
					}
					for(Node b : remove) {
						_root1.getChildren().remove(b);
					}
					int x = 900;
					int y = 77;
					int x1 = 900;
					int y1 = 600;

if(!_beatnero.equals("t")){
	x1 = 475;
	y1 = 183;
if(!_beatspiball2.equals("t")){
	x1 = 374;
	y1 = 183;
if(!_beatcandm.equals("t")){
		x1 = 256;
		y1 = 183;
	if(!_beatcranius.equals("t")){
			x1 = 147;
			y1 = 183;
		if(!_beatdroth.equals("t")) {
			    x1 = 0;
			    y1 = 183;
			if(!_beatcrunch.equals("t")){
				x = 739;
				y = 100;
				if(!_beatlaser.equals("t")){
					x = 610;
					if(!_beatspiball.equals("t")){
						x = 470;
						if(!_beatcrush.equals("t")) {
							x = 360;
							if(!_beatswurli.equals("t")) {
								x = 250;
								if(!_beattoot.equals("t")) {
									x = 130;
								}
							}
						}
					}
				}
			}
		}
	}
	}
}
}
					_gc.drawImage(new Image("text/black.png"), x, y);
					_gc.drawImage(new Image("text/black.png"), x1, y1);
					if (!_root1.getChildren().contains(_toot)) {
						_toot.setMinWidth(60);
						_toot.setMinHeight(25);
						_toot.setLayoutX(58);
						_toot.setLayoutY(129);
						_root1.getChildren().add(_toot);

						_toot.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_swurli) && _beattoot.equals("t")) {
						_swurli.setMinWidth(60);
						_swurli.setMinHeight(25);
						_swurli.setLayoutX(166);
						_swurli.setLayoutY(129);
						_root1.getChildren().add(_swurli);

						_swurli.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_crush) && _beatswurli.equals("t")) {
						_crush.setMinWidth(60);
						_crush.setMinHeight(25);
						_crush.setLayoutX(285);
						_crush.setLayoutY(129);
						_root1.getChildren().add(_crush);

						_crush.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_spiball) && _beatcrush.equals("t")) {
						_spiball.setMinWidth(60);
						_spiball.setMinHeight(25);
						_spiball.setLayoutX(395);
						_spiball.setLayoutY(129);
						_root1.getChildren().add(_spiball);

						_spiball.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_laser) && _beatspiball.equals("t")) {
						_laser.setMinWidth(60);
						_laser.setMinHeight(25);
						_laser.setLayoutX(515);
						_laser.setLayoutY(129);
						_root1.getChildren().add(_laser);

						_laser.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_crunch) && _beatlaser.equals("t")) {
						_crunch.setMinWidth(60);
						_crunch.setMinHeight(25);
						_crunch.setLayoutX(643);
						_crunch.setLayoutY(129);
						_root1.getChildren().add(_crunch);

						_crunch.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_droth) && _beatcrunch.equals("t")) {
						_droth.setMinWidth(60);
						_droth.setMinHeight(25);
						_droth.setLayoutX(773);
						_droth.setLayoutY(129);
						_root1.getChildren().add(_droth);

						_droth.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_cranius) && _beatdroth.equals("t")) {
						_cranius.setMinWidth(60);
						_cranius.setMinHeight(25);
						_cranius.setLayoutX(58);
						_cranius.setLayoutY(219);
						_root1.getChildren().add(_cranius);

						_cranius.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_candm) && _beatcranius.equals("t")) {
						_candm.setMinWidth(60);
						_candm.setMinHeight(25);
						_candm.setLayoutX(168);
						_candm.setLayoutY(219);
						_root1.getChildren().add(_candm);

						_candm.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_spiball2) && _beatcandm.equals("t")) {
						_spiball2.setMinWidth(60);
						_spiball2.setMinHeight(25);
						_spiball2.setLayoutX(278);
						_spiball2.setLayoutY(219);
						_root1.getChildren().add(_spiball2);

						_spiball2.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_nero) && _beatspiball2.equals("t")) {
						_nero.setMinWidth(60);
						_nero.setMinHeight(25);
						_nero.setLayoutX(388);
						_nero.setLayoutY(219);
						_root1.getChildren().add(_nero);

						_nero.setOnMousePressed(m::handleButtonPress);
					}
					if (!_root1.getChildren().contains(_ultimo) && _beatnero.equals("t")) {
						_ultimo.setMinWidth(60);
						_ultimo.setMinHeight(25);
						_ultimo.setLayoutX(774);
						_ultimo.setLayoutY(219);
						_root1.getChildren().add(_ultimo);

						_ultimo.setOnMousePressed(m::handleButtonPress);
					}
					
				} else {
					List<Node> remove = new ArrayList<Node>();
					for(Node b : _root1.getChildren()) {
						if(b.getClass().toString().equals("class javafx.scene.control.Button")) {
							remove.add(b);
						}
					}
					for(Node b : remove) {
						_root1.getChildren().remove(b);
					}
				if(_character1 == null) {
					_character1 = new Ninja1Char("one", _skin);
				}
				
				
				
				_gc.drawImage(_scroll, _scrollc, 60);
				if(_scrollc > -1800) {
				_scrollc = _scrollc - 10;
				} else {
					_scrollc = 0;
				}
				if(!_bossspawned) {
					_boss.spawn();
					_bossspawned = true;
				}
				for(Backdrop b : _backdrops) {
					_gc.drawImage(b.getImage(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
				}
				_boss.render(_gc);
				_boss.incrementCounter();
				_character1.render(_gc);
				_character1.move();
				_character1.incrementCounter();
				if (!(_character1.isOnPlatform()) && _character1.isGravity()) {
					_character1.setYVelocity(_character1.getYVelocity() + _gravity);
				}
				_scene.setOnKeyPressed(m::handleKeyPress);
				_scene.setOnKeyReleased(m::handleKeyRelease);
				List<Hitbox> attackstoremove = new ArrayList<Hitbox>();
				for (Hitbox a : _attacks) {
					a.render(_gc);
					if (a.isAffectedByGravity()) {
						a.setYVelocity(a.getYVelocity() + _gravity);
					}
					if (a.checkCollide() == true) {
						
						if (a.getCharacter().getID().equals("one")) {
							_boss.hit(a);
						} 
						else{
							
							_character1.hit(a);
						}
						
					}
					if (a.isGone()) {
						attackstoremove.add(a);
					}
				}
				
			
				for (Hitbox a : attackstoremove) {
					_attacks.remove(a);
				}
				
				
				_gc.drawImage(new Image("stage.png"), 0, 0);
				if(_text!= null) {
					_gc.drawImage(_text, 0, 482);
				}
				
				_gc.setFont(Font.font("Arial", 20));
				_gc.setFill(Color.WHITE);
				
				if(_character1.getLives() > 0) {
					_gc.drawImage(new Image("heart.png"), 74, 13, 30, 30);
					if(_character1.getLives() > 1) {
						_gc.drawImage(new Image("heart.png"), 114, 13, 30, 30);
						if(_character1.getLives() > 2) {
							_gc.drawImage(new Image("heart.png"), 154, 13, 30, 30);
							if(_character1.getLives() > 3) {
								_gc.drawImage(new Image("heart.png"), 194, 13, 30, 30);
							}
						}
					}
				} else {
					_gc.drawImage(new Image("text/lose.png"), 297, 215);
				}
				if(_boss.getHealth() == 0) {
					_gc.drawImage(new Image("text/win.png"), 284, 215);
				}
				String bosshealth = ("" + _boss.getHealth());
				_gc.fillText(bosshealth, 620, 35);
				if(_character1.getLives() <= 0 && !_vsongplaying) {
						playStageSong("/songs/lose.mp3");
						_vsongplaying = true;
				}
				for(Backdrop b : _frontdrops) {
					TheGame._gc.drawImage(b.getImage(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
				}
				if(_boss.isDead() || _boss.isWon()){
					_character1.setImmune(true);
					if(_boss.isDead() && !_vsongplaying) {
						playStageSong("/songs/victory.mp3");
						_vsongplaying = true;
					}
					
					if (!_root1.getChildren().contains(_return)) {
						
						_return.setMinWidth(100);
						_return.setMinHeight(50);
						_return.setLayoutX(388);
						_return.setLayoutY(290);
						_root1.getChildren().add(_return);

						_return.setOnMousePressed(m::handleButtonPress);
					}
				}
				}
				
				}
			}
		};
		_animationTimer.start();
		stage1.show();
}
	
	public void handleKeyPress(KeyEvent event) {
		if (event.getCode().toString().equals("COMMA")) {
			_character1.pressAttack1();
		}
		if (event.getCode().toString().equals("PERIOD")) {
			_character1.pressAttack2();
		}
		if (event.getCode().toString().equals("R")) {
			_character1.pressAttackU();
		}

		if (event.getCode().toString().equals("SPACE")) {
			_character1.pressJump();

		}
		if (event.getCode().toString().equals("D")) {
				_character1.pressRight();
			

		}
		if (event.getCode().toString().equals("A")) {
				_character1.pressLeft();
			
		}
		if (event.getCode().toString().equals("W")) {
			_character1.pressUp();

		}
		if (event.getCode().toString().equals("S")) {
			_character1.pressDown();

		}

		if (event.getCode().toString().equals("F")) {
			_character1.pressAttack3();
	
		}

}

public void handleKeyRelease(KeyEvent event) {

	
	if (event.getCode().toString().equals("D")) {
		_character1.releaseRight();
		// System.out.println("moving right");
	}
	if (event.getCode().toString().equals("A")) {
		_character1.releaseLeft();
		
		// System.out.println("moving left");
	}
	if (event.getCode().toString().equals("W")) {
		if (!_character1.isXTumbling()) {
			_character1.releaseUp();
		}
		// System.out.println("moving left");
	}
	if (event.getCode().toString().equals("S")) {
		if (!_character1.isXTumbling()) {
			_character1.releaseDown();
		}
		// System.out.println("moving left");
	}
	
	if (event.getCode().toString().equals("SPACE")) {
		if (!_character1.isXTumbling()) {
			_character1.releaseJump();
		}
		// System.out.println("moving left");
	}
	if (event.getCode().toString().equals("ESCAPE")) {
		if(_bosspicked) {
		try {
			_player.stop();
		} catch (BasicPlayerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		_backdrops.clear();
		_songplaying = false;
		_vsongplaying = false;
		_charpicked = false;
		_bosspicked = false;
		_character1 = null;
		if(_boss.isDead()){
		if(_boss.getID().equals("tootboss")){
			if(_beattoot.equals("f")){
				_gotpower = true;
				_power = "toot";
				_newskin = "yellow";
			}
			_beattoot = "t";
		}
		if(_boss.getID().equals("ghostboss")){
			if(_beatswurli.equals("f")){
				_gotpower = true;
				_power = "swurli";
				_newskin = "black";
			}
			_beatswurli = "t";
		}
		if(_boss.getID().equals("rockboss")){
			if(_beatcrush.equals("f")){
				_gotpower = true;
				_power = "crush";
				_newskin = "rock";
			}
			_beatcrush = "t";
		}
		if(_boss.getID().equals("spikeboss")){
			if(_beatspiball.equals("f")){
				_gotpower = true;
				_power = "spiball";
				_newskin = "spike";
			}
			_beatspiball = "t";
		}
		if(_boss.getID().equals("botboss")){
			if(_beatlaser.equals("f")){
				_gotpower = true;
				_power = "laser";
				_newskin = "laser";
			}
			_beatlaser = "t";
		}
		if(_boss.getID().equals("rockboss2")){
			if(_beatcrunch.equals("f")){
				_gotpower = true;
				_power = "crunch";
				_newskin = "rock2";
			}
			_beatcrunch = "t";
		}
		if(_boss.getID().equals("dragonboss")){
			if(_beatdroth.equals("f")){
				_gotpower = true;
				_power = "droth";
				_newskin = "dragon";
			}
			_beatdroth = "t";
		}
		if(_boss.getID().equals("skullboss")){
			if(_beatcranius.equals("f")){
				_gotpower = true;
				_power = "cranium";
				_newskin = "skull";
			}
			_beatcranius = "t";
		}
		if(_boss.getID().equals("twinsboss")){
			if(_beatcandm.equals("f")){
				_gotpower = true;
				_power = "candm";
				_newskin = "twins";
			}
			_beatcandm = "t";
		}
		if(_boss.getID().equals("spikeboss2")){
			if(_beatspiball2.equals("f")){
				_gotpower = true;
				_power = "spiball2";
				_newskin = "spike2";
			}
			_beatspiball2 = "t";
		}
		if(_boss.getID().equals("demonboss")){
			if(_beatnero.equals("f")){
				_gotpower = true;
				_power = "nero";
				_newskin = "demon";
			}
			_beatnero = "t";
		}
		if(_gotpower) {
			try {
				_player.stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			playStageSong("/songs/power.mp3");
		}
		}
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("data.txt"));
			write(writer, _beattoot);
			write(writer, _beatswurli);
			write(writer, _beatcrush);
			write(writer, _beatspiball);
			write(writer, _beatlaser);
			write(writer, _beatcrunch);
			write(writer, _beatdroth);
			write(writer, _beatcranius);
			write(writer, _beatcandm);
			write(writer, _beatspiball2);
			write(writer, _beatnero);
			write(writer, _1stultimo);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_boss = null;
		_attacks.clear();
		_bossspawned = false;
		stopText();
		_root1.getChildren().remove(_return);
		}
	}
	
	
	

}

public void closeWindow(WindowEvent w) {
	_closed = true;
	try {
		_player.stop();
		
	} catch (BasicPlayerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	_stage.close();
}

public static List<Platform> getPlatforms() {
	return _platforms;
}


public static void playStageSong(String url) {

 new Thread(new Runnable() {
	 public void run() {
	try {
		String pathToMp3 = System.getProperty("user.dir") + url;
		
		_player.open((TheGame.class.getResource(url)));
		 _player.play();
		 
	} catch (BasicPlayerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 }
 }).start();
 
}
public static int getPlayerState() {
 return _player.getStatus();
}
public static boolean getClosed() {
 return _closed;
}
public static synchronized void playSound(final String url) {
  new Thread(new Runnable() {
  // The wrapper thread is unnecessary, unless it blocks on the
  // Clip finishing; see comments.
    public void run() {
      try {
        Clip clip = AudioSystem.getClip();
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
          TheGame.class.getResource(url));
        clip.open(inputStream);
        clip.start(); 
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }
  }).start();
}

public void handleButtonPress(MouseEvent click) {
	if(click.getSource().equals(_white)) {
		_skin = "sprites";
		_charpicked = true;
	}
	if(click.getSource().equals(_red)) {
		_skin = "red";
		_charpicked = true;
	}
	if(click.getSource().equals(_green)) {
		_skin = "green";
		_charpicked = true;
	}
	if(click.getSource().equals(_yellow)) {
		_skin = "yellow";
		_charpicked = true;
	}
	if(click.getSource().equals(_rock)) {
		_skin = "rock";
		_charpicked = true;
	}
	if(click.getSource().equals(_black)) {
		_skin = "black";
		_charpicked = true;
	}
	if(click.getSource().equals(_spike)) {
		_skin = "spike";
		_charpicked = true;
	}
	if(click.getSource().equals(_lasers)) {
		_skin = "laser";
		_charpicked = true;
	}
	if(click.getSource().equals(_rock2)) {
		_skin = "rock2";
		_charpicked = true;
	}
	if(click.getSource().equals(_dragon)) {
		_skin = "dragon";
		_charpicked = true;
	}
	if(click.getSource().equals(_skull)) {
		_skin = "skull";
		_charpicked = true;
	}
	if(click.getSource().equals(_twins)) {
		_skin = "twins";
		_charpicked = true;
	}
	if(click.getSource().equals(_spike2)) {
		_skin = "spike2";
		_charpicked = true;
	}
	if(click.getSource().equals(_demon)) {
		_skin = "demon";
		_charpicked = true;
	}
	
	
	
	
	// bosses
	if(_charpicked && _bosspicked) {
		try {
			_player.stop();
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(click.getSource().equals(_toot)) {
		_boss = new TootBoss();
		_bosspicked = true;
		playStageSong("/songs/toot.mp3");
		
	}
	if(click.getSource().equals(_swurli)) {
		_boss = new GhostBoss();
		_bosspicked = true;
		playStageSong("/songs/swurli.mp3");
	}
	if(click.getSource().equals(_crush)) {
		_boss = new RockBoss();
		_bosspicked = true;
		playStageSong("/songs/crush.mp3");
	}
	if(click.getSource().equals(_spiball)) {
		_boss = new SpikeBoss();
		_bosspicked = true;
		playStageSong("/songs/spiball.mp3");
	}
	if(click.getSource().equals(_laser)) {
		_boss = new BotBoss();
		_bosspicked = true;
		playStageSong("/songs/laser.mp3");
	}
	if(click.getSource().equals(_crunch)) {
		_boss = new RockBoss2();
		_bosspicked = true;
		playStageSong("/songs/crunch.mp3");
	}
	if(click.getSource().equals(_droth)) {
		_boss = new DragonBoss();
		_bosspicked = true;
		playStageSong("/songs/droth.mp3");
	}
	if(click.getSource().equals(_cranius)) {
		_boss = new SkullBoss();
		_bosspicked = true;
		playStageSong("/songs/cranius.mp3");
	}
	if(click.getSource().equals(_candm)) {
		_boss = new TwinsBoss();
		_bosspicked = true;
		playStageSong("/songs/candm.mp3");
	}
	if(click.getSource().equals(_spiball2)) {
		_boss = new SpikeBoss2();
		_bosspicked = true;
		playStageSong("/songs/spiball2.mp3");
	}
	if(click.getSource().equals(_nero)) {
		_boss = new DemonBoss();
		_bosspicked = true;
		playStageSong("/songs/nero.mp3");
	}
	if(click.getSource().equals(_ultimo)) {
		_boss = new UltimoBoss(_1stultimo);
		_bosspicked = true;
		if(_1stultimo.equals("f")){
		playStageSong("/songs/ultimointro.mp3");
		} else {
		playStageSong("/songs/ultimo.mp3");
		}
	}
	
	
	
	
	
	//after aug screen
	if(click.getSource().equals(_continue)) {
		_gotpower = false;
		_root1.getChildren().remove(_continue);
	}
	
	
	//return
	if(click.getSource().equals(_return)) {
		_backdrops.clear();
		_charpicked = false;
		_bosspicked = false;
		_character1 = null;
		try {
			_player.stop();
		} catch (BasicPlayerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		_songplaying = false;
		_vsongplaying = false;
		
		if(_boss.isDead()){
			if(_boss.getID().equals("tootboss")){
				if(_beattoot.equals("f")){
					_gotpower = true;
					_power = "toot";
					_newskin = "yellow";
				}
				_beattoot = "t";
			}
			if(_boss.getID().equals("ghostboss")){
				if(_beatswurli.equals("f")){
					_gotpower = true;
					_power = "swurli";
					_newskin = "black";
				}
				_beatswurli = "t";
			}
			if(_boss.getID().equals("rockboss")){
				if(_beatcrush.equals("f")){
					_gotpower = true;
					_power = "crush";
					_newskin = "rock";
				}
				_beatcrush = "t";
			}
			if(_boss.getID().equals("spikeboss")){
				if(_beatspiball.equals("f")){
					_gotpower = true;
					_power = "spiball";
					_newskin = "spike";
				}
				_beatspiball = "t";
			}
			if(_boss.getID().equals("botboss")){
				if(_beatlaser.equals("f")){
					_gotpower = true;
					_power = "laser";
					_newskin = "laser";
				}
				_beatlaser = "t";
			}
			if(_boss.getID().equals("rockboss2")){
				if(_beatlaser.equals("f")){
					_gotpower = true;
					_power = "crunch";
					_newskin = "rock2";
				}
				_beatcrunch = "t";
			}
			if(_boss.getID().equals("dragonboss")){
				if(_beatdroth.equals("f")){
					_gotpower = true;
					_power = "droth";
					_newskin = "dragon";
				}
				_beatdroth = "t";
			}
			if(_boss.getID().equals("skullboss")){
				if(_beatcranius.equals("f")){
					_gotpower = true;
					_power = "cranium";
					_newskin = "skull";
				}
				_beatcranius = "t";
			}
			if(_boss.getID().equals("twinsboss")){
				if(_beatcandm.equals("f")){
					_gotpower = true;
					_power = "candm";
					_newskin = "twins";
				}
				_beatcandm = "t";
			}
			if(_boss.getID().equals("spikeboss2")){
				if(_beatspiball2.equals("f")){
					_gotpower = true;
					_power = "spiball2";
					_newskin = "spike2";
				}
				_beatspiball2 = "t";
			}
			if(_boss.getID().equals("demonboss")){
				if(_beatnero.equals("f")){
					_gotpower = true;
					_power = "nero";
					_newskin = "demon";
				}
				_beatnero = "t";
			}
			if(_gotpower) {
				try {
					_player.stop();
				} catch (BasicPlayerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				playStageSong("/songs/power.mp3");
			}
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("data.txt"));
			write(writer, _beattoot);
			write(writer, _beatswurli);
			write(writer, _beatcrush);
			write(writer, _beatspiball);
			write(writer, _beatlaser);
			write(writer, _beatcrunch);
			write(writer, _beatdroth);
			write(writer, _beatcranius);
			write(writer, _beatcandm);
			write(writer, _beatspiball2);
			write(writer, _beatnero);
			write(writer, _1stultimo);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
		_boss = null;
		_attacks.clear();
		_bossspawned = false;
		stopText();
		_root1.getChildren().remove(_return);
	}
}

static public void setText(Image img) {
	_text = img;
}

static public void stopText() {
	_text = null;
}
public static void clearHitboxes(String ID, Entity c) {
	List<Hitbox> remove = new ArrayList<Hitbox>();
	for (Hitbox a : TheGame._attacks) {
		if (a.getID().equals(ID) && a.getCharacter().equals(c)) {
			remove.add(a);
		}
	}
	for (Hitbox a : remove) {
		TheGame._attacks.remove(a);
	}
}

public void write(BufferedWriter writer, String s) 
		  throws IOException {
		    
		    writer.write(s);
		    writer.newLine();
		    
}
public String read(BufferedReader reader)throws IOException {
		  
		     
		     
		     
		     String currentLine = reader.readLine();
		     
		     
		     
		     return currentLine;
		
}

public static void endUDialogue() {
	_1stultimo = "t";
}

}


	


