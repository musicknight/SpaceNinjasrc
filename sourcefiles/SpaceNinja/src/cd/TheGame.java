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
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import cd.bosses.Boss;
import cd.bosses.BotBoss;
import cd.bosses.BotBoss2;
import cd.bosses.ClockBoss;
import cd.bosses.CometBoss;
import cd.bosses.DemonBoss;
import cd.bosses.DragonBoss;
import cd.bosses.GhostBoss;
import cd.bosses.NullBoss;
import cd.bosses.RockBoss;
import cd.bosses.RockBoss2;
import cd.bosses.SkullBoss;
import cd.bosses.SpikeBoss;
import cd.bosses.SpikeBoss2;
import cd.bosses.TootBoss;
import cd.bosses.TootBoss2;
import cd.bosses.TootBoss3;
import cd.bosses.TootBoss4;
import cd.bosses.TwinsBoss;
import cd.bosses.UltimoBoss;
import cd.chars.Ninja1Char;
import cd.chars.CDCharacter;
import cd.chars.Character;
import cd.chars.CharacterImpl;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.util.Duration;

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
	public static Boss _boss = null;
	public static boolean _bossspawned;
	public static Place _place;
	private static Canvas _canvas = new Canvas(900, 600);
	
	public static double _velocitym;
	
	
	
	private boolean _songplaying;
	private boolean _vsongplaying;
	public static GraphicsContext _gc = _canvas.getGraphicsContext2D();
	public static GraphicsContext _gc2 = _canvas.getGraphicsContext2D();
	public static Stage _stage;
	private Scene _scene;
	private AnimationTimer _animationTimer;
	public static Set<Hitbox> _attacks = new HashSet<Hitbox>();
	public static List<Platform> _platforms = new ArrayList<Platform>();
	public static List<Backdrop> _backdrops = new ArrayList<Backdrop>();
	public static List<Backdrop> _frontdrops = new ArrayList<Backdrop>();
	public static Image _scroll = new Image("scroll/space.png");
	public static int _scrollc;
	public static int _scrollr = 10;
	private boolean _charpicked;
	private boolean _bosspicked = false;
	public static boolean _closed = false;
	private boolean _buttonsremoved;
	
	//game start button
	private Button _start = new Button("start");
	
	private Button _return = new Button("back to main menu");
	private Button _retry = new Button("try again?");
	
	private Button _continue = new Button("continue");
	private static Button _reset = new Button("reset");
	//next/prev page button
	private static Group _root1 = new Group();
	public static boolean _rendering = true;
	private int _page = 1;
	private boolean _menusongstarted= false;
	private boolean _titlesongstarted = false;
	private boolean _gamestarted = false;
	private String _bossclass;
	
	
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
	private Button _clock = new Button("select");
	private Button _laser2 = new Button("select");
	private Button _ult = new Button("select");
	
	private Button _toot = new Button("fight!");
	private Button _crush = new Button("fight!");
	private Button _spiball = new Button("fight!");
	private Button _swurli = new Button("fight!");
	private Button _laser = new Button("fight!");
	private Button _crunch = new Button("fight!");
	private Button _droth = new Button("fight!");
	private Button _cranius = new Button("fight!");
	private Button _candm = new Button("fight!");
	private Button _spiball2 = new Button("fight!");
	private Button _nero = new Button("fight!");
	private Button _cryok = new Button("fight!");
	private Button _cannon = new Button("fight!");
	
	
	private Button _ultimo = new Button("fight");
	
	private long _last = 0;
	private boolean _powersong;
	private long _lasttimeFPS;
	private int _framecnt;
	private int _framecntprnt;
	private long _crntframecnt;
	
	private static String _beattoot;
	private static String _beatswurli;
	private static String _beatcrush;
	private static String _beatspiball;
	private static String _beatlaser;
	private static String _beatcrunch;
	private static String _beatdroth;
	private static String _beatcranius;
	private static String _beatcandm;
	private static String _beatspiball2;
	private static String _beatnero;
	public static  String _1stultimo;
	public static String _beatultimo;
	public static String _beattoot2;
	public static String _beattoot3;
	public static String _beattoot4;
	public static String _beatultimo2;
	public static String _beattoot5;
	public static String _beatcryok;
	public static String _beatcannon;
	
	private static Image _text;
	
	private Button _replaybutton = new Button();
	public static boolean _gotpower;
	private String _power;
	private String _newskin;
	private int _savedx;
	private int _savedy = 400;
	private boolean _paused = false;
	public static boolean _frozen = false;
	public static double _timescale = 1;
	
	public static boolean _vertscroll;

	public static double _gravity = 0.8;
	
	private static  GameSounds _gs = new GameSounds();
	//public static BasicPlayer _player = _gs.getPlayer();
	public static String _song;
	
	
	

	public void start(Stage stage1) {
		FXMLLoader loader = new
		 FXMLLoader(this.getClass().getResource("watchout.fxml"));
		 loader.setController(this);
		
		stage1.setTitle("SpaceNinja");
		
		_stage = stage1;
		TheGame m = this;
		_reset.setOnMousePressed(m::handleButtonPress);
		//_stage.setOnCloseRequest(m::closeWindow);
		_root1 = new Group();
		_scene = new Scene(_root1);
		_scene.setFill(Color.BLACK);
		stage1.setScene(_scene);
		_stage.setOnCloseRequest(m::closeWindow);
		_root1.getChildren().add(_canvas);
		Platform p = new PlatformImpl(-100, 442, 1000, 158);
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
			_beatcryok = read(reader);
			_beatcannon = read(reader);
			_1stultimo = read(reader);
			_beatultimo = read(reader);
			_beattoot2 = read(reader);
			_beattoot3 = read(reader);
			_beattoot4 = read(reader);
			_beatultimo2 = read(reader);
			_beattoot5 = read(reader);
			
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
		if(_beatcryok == null) {
			_beatcryok = "f";
		}
		if(_beatcannon == null) {
			_beatcannon = "f";
		}
		if(_1stultimo == null) {
			_1stultimo = "f";
		}
		if(_beatultimo == null) {
			_beatultimo = "f";
		}
		if(_beattoot2 == null) {
			_beattoot2 = "f";
		}
		if(_beattoot3 == null) {
			_beattoot3 = "f";
		}
		if(_beattoot4 == null) {
			_beattoot4 = "f";
		}
		if(_beatultimo2 == null) {
			_beatultimo2 = "f";
		}
		if(_beattoot5 == null) {
			_beattoot5 = "f";
		}
		
		writeData();
		if(_beattoot3.equals("t") && _beattoot4.equals("f")){
			_stage.setTitle("Toot");
		}
		// character selection screen

		// _player1picked = true;

		// _player1picked = true;
		// Group root = new Group();
		// _scene = new Scene(root);
		// _scene.setFill(Color.BLACK);
		// stage1.setScene(_scene);

		// root.getChildren().add(_canvas);
		
		Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );
        
        
        KeyFrame kf = new KeyFrame(Duration.seconds(0.023),               
                new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                    	if(!_paused){
                        _gc.clearRect(0, 0, 900, 600);
                        if(_gotpower) {
        					if(!_powersong){
        					GameSounds.playStageSong("/songs/power.mp3");
        					_powersong = true;
        					}
        					
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
        					GameSounds.playStageSong("/songs/theme.mp3");
        					_songplaying = true;
        					_powersong = false;
        					}
        					
        					if(_beatultimo2.equals("f") || _beattoot5.equals("t")){
        					_gc.drawImage(new Image("text/charscreen.png"), 0, 0);
        					} else {
        						_gc.drawImage(new Image("text/charscreen2.png"), 0, 0);
        					}
        					int x = 900;
        					int y = 77;
        					int x1 = 0;
        					int y1 = 600;
        if((_beattoot2.equals("t") && _beattoot3.equals("f") || _beattoot4.equals("t")) || _beattoot5.equals("t")){
        	x = 220;
        	y = 77;
        	x1 = 0;
        	y1 = 185;
        }
        if(_beatultimo2.equals("t") && _beattoot5.equals("f")) {
        	x = 900;
        	y = 600;
        	x1 = 900;
        	y1 = 600;
        }
        if(_beatcannon.equals("f")){
        	x1 = 162;
        	y1 = 185;
        if(_beatcryok.equals("f")){
        	x1 = 102;
        	y1 = 185;
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
        }
        }
        					_gc.drawImage(new Image("text/black.png"), x, y);
        					_gc.drawImage(new Image("text/black.png"), x1, y1);
        					if(_beatultimo2.equals("f") || _beattoot5.equals("t")){
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
        					}
        					if((_beattoot2.equals("f") || _beattoot3.equals("t")) && _beattoot4.equals("f")){
        						
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
        					if (!_root1.getChildren().contains(_clock) && _beatcryok.equals("t")) {
        						_clock.setMinWidth(45);
        						_clock.setMinHeight(12);
        						_clock.setLayoutX(106);
        						_clock.setLayoutY(235);
        						_root1.getChildren().add(_clock);

        						_clock.setOnMousePressed(m::handleButtonPress);
        					}
        					if (!_root1.getChildren().contains(_laser2) && _beatcannon.equals("t")) {
        						_laser2.setMinWidth(45);
        						_laser2.setMinHeight(12);
        						_laser2.setLayoutX(166);
        						_laser2.setLayoutY(235);
        						_root1.getChildren().add(_laser2);

        						_laser2.setOnMousePressed(m::handleButtonPress);
        					}
        					}
        					if (!_root1.getChildren().contains(_ult) && _beatultimo2.equals("t") && _beattoot5.equals("f")) {
        						_ult.setMinWidth(295);
        						_ult.setMinHeight(75);
        						_ult.setLayoutX(286);
        						_ult.setLayoutY(443);
        						_root1.getChildren().add(_ult);

        						_ult.setOnMousePressed(m::handleButtonPress);
        					}
        					
        				} else if(!_bosspicked) {
        					if(_beattoot3.equals("f") || _beattoot4.equals("t")){
        					_gc.drawImage(new Image("text/bossscreen.png"), 0, 0);
        					} else {
        						_gc.drawImage(new Image("text/bossscreent.png"), 0, 0);	
        					}
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


        if(_beattoot2.equals("t") && _beattoot3.equals("f") || _beattoot5.equals("t")){
        	x = 130;
        	y = 77;
        	x1 = 0;
        	y1 = 183;
        }
        if(!_beatcannon.equals("t")){
        	x1 = 755;
        	y1 = 183;
        if(!_beatcryok.equals("t")){
        	x1 = 575;
        	y1 = 183;
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
        					
        					if(_beattoot2.equals("f") || _beattoot3.equals("t") && _beattoot5.equals("f")){
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
        					if (!_root1.getChildren().contains(_cryok) && _beatnero.equals("t")) {
        						_cryok.setMinWidth(60);
        						_cryok.setMinHeight(25);
        						_cryok.setLayoutX(512);
        						_cryok.setLayoutY(219);
        						_root1.getChildren().add(_cryok);

        						_cryok.setOnMousePressed(m::handleButtonPress);
        					}
        					if (!_root1.getChildren().contains(_cannon) && _beatcryok.equals("t")) {
        						_cannon.setMinWidth(60);
        						_cannon.setMinHeight(25);
        						_cannon.setLayoutX(642);
        						_cannon.setLayoutY(219);
        						_root1.getChildren().add(_cannon);

        						_cannon.setOnMousePressed(m::handleButtonPress);
        					}
        					if (!_root1.getChildren().contains(_ultimo) && _beatcannon.equals("t")) {
        						_ultimo.setMinWidth(60);
        						_ultimo.setMinHeight(25);
        						_ultimo.setLayoutX(774);
        						_ultimo.setLayoutY(219);
        						_root1.getChildren().add(_ultimo);

        						_ultimo.setOnMousePressed(m::handleButtonPress);
        					}
        					}
        				} else {
        					if(!_buttonsremoved){
        					List<Node> remove = new ArrayList<Node>();
        					for(Node b : _root1.getChildren()) {
        						if(b.getClass().toString().equals("class javafx.scene.control.Button")) {
        							remove.add(b);
        						}
        					}
        					for(Node b : remove) {
        						_root1.getChildren().remove(b);
        					}
        					_buttonsremoved = true;
        					}
        				if(_character1 == null) {
        					
        				}
        				
        				
        				
        				
        				if(!_vertscroll){
        				_gc.drawImage(_scroll, _scrollc, 60);
        				if(_scrollc > -1800) {
        				_scrollc = _scrollc - _scrollr;
        				} else {
        					_scrollc = 0;
        				}
        				} else {
        					_gc.drawImage(_scroll, 0, _scrollc+60);
        					if(_scrollc < 0) {
        						_scrollc = _scrollc + _scrollr;
        						} else if(_scrollc == 0) {
        							_scrollc = -2310;
        						}
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
        					_character1.setYVelocity(_character1.getYVelocity() + _gravity*_timescale);
        				}
        				_scene.setOnKeyPressed(m::handleKeyPress);
        				
        				_scene.setOnKeyReleased(m::handleKeyRelease);
        				List<Hitbox> attackstoremove = new ArrayList<Hitbox>();
        				
        				for (Hitbox a : _attacks) {
        					a.render(_gc);
        					if (a.isAffectedByGravity()) {
        						a.setYVelocity(a.getYVelocity() + _gravity*_timescale);
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
        				
        				_character1.drawLives(_gc);
        				if(_boss.getHealth() == 0 && (!_boss.getID().equals("ultimoboss") || _beattoot4.equals("t")) && !_boss.getID().equals("tootboss2") && !_boss.getID().equals("null")) {
        					_gc.drawImage(new Image("text/win.png"), 284, 215);
        				}
        				String bosshealth = ("" + _boss.getHealth());
        				_gc.fillText(bosshealth, 620, 35);
        				
        				for(Backdrop b : _frontdrops) {
        					TheGame._gc.drawImage(b.getImage(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
        				}
        				if(_boss.isDead() || _boss.isWon()){
        					_character1.setImmune(true);
        					//if(_boss.isDead() && !_vsongplaying && !_boss.getID().equals("tootboss2")) {
        						//GameSounds.playStageSong("/songs/victory.mp3");
        						//_vsongplaying = true;
        					//}
        					
        					if (!_root1.getChildren().contains(_return)) {
        						
        						_return.setMinWidth(100);
        						_return.setMinHeight(50);
        						_return.setLayoutX(388);
        						_return.setLayoutY(290);
        						_root1.getChildren().add(_return);

        						_return.setOnMousePressed(m::handleButtonPress);
        					}
        					if (!_root1.getChildren().contains(_retry) && !_boss.isDead()) {
        						
        						_retry.setMinWidth(116);
        						_retry.setMinHeight(50);
        						_retry.setLayoutX(388);
        						_retry.setLayoutY(365);
        						_root1.getChildren().add(_retry);

        						_retry.setOnMousePressed(m::handleButtonPress);
        					}
        				}
        				long n = System.nanoTime();
        				if(n > _lasttimeFPS + 1000000000L){
        					
        					_framecntprnt = _framecnt;
        					_framecnt = 0;
        					_lasttimeFPS = n;
        					
        				}
        				_gc.fillText("FPS: " + _framecntprnt , 800, 20);
        				_framecnt++;	
        				_crntframecnt = (long) ((double)_framecnt / ((double)(n - _lasttimeFPS) / 1000000000L));
        				_velocitym = 50 / (double)(_crntframecnt);
        				//System.out.println(_velocitym);
        				
        				}
        				
        				
                    	} 
                
                    }
                });
            
            gameLoop.getKeyFrames().add( kf );
            gameLoop.play();
            
            _stage.show();

		
		long last = 0;
		
		
		
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
		if(_bosspicked && !_paused){
		//_animationTimer.stop();
		_paused = true;
		System.out.println("here");
		_return.setMinWidth(100);
		_return.setMinHeight(50);
		_return.setLayoutX(388);
		_return.setLayoutY(290);
		_root1.getChildren().add(_return);
		
		_retry.setMinWidth(116);
		_retry.setMinHeight(50);
		_retry.setLayoutX(388);
		_retry.setLayoutY(365);
		_root1.getChildren().add(_retry);
		_retry.setOnMousePressed(this::handleButtonPress);
		_return.setOnMousePressed(this::handleButtonPress);
		GameSounds.pausePlayer();
		return;
		}
		if(_bosspicked && _paused) {
			//_animationTimer.start();
			_paused = false;
			_root1.getChildren().remove(_return);
			_root1.getChildren().remove(_retry);
			GameSounds.resumePlayer();
			return;
		}
	}
	
	
	

}

public void closeWindow(WindowEvent w) {
	writeData();
	_closed = true;
	GameSounds.stopPlayer();
	
	_stage.close();
}

public static List<Platform> getPlatforms() {
	return _platforms;
}




public static boolean getClosed() {
 return _closed;
}




public void handleButtonPress(MouseEvent click) {
	if(click.getSource().equals(_reset)) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("data.txt"));
			write(writer, "f");
			write(writer, "f");
			write(writer, "f");
			write(writer, "f");
			write(writer, "f");
			write(writer, "f");
			write(writer, "f");
			write(writer, "f");
			write(writer, "f");
			write(writer, "f");
			write(writer, "f");
			write(writer, "f");
			write(writer, "f");
			write(writer, "f");
			write(writer, "f");
			write(writer, "f");
			write(writer, "f");
			write(writer, "f");
			write(writer, "f");
			write(writer, "f");
			write(writer, "f");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GameSounds.stopPlayer();
		_closed = true;
		_stage.close();
	}
	
	if(_beattoot3.equals("t") && _charpicked && _beattoot4.equals("f") && !click.getSource().equals(_return)) {
		
		if(_boss == null){
		_boss = new TootBoss3();
		
		_character1.setX(5);
		_character1.setY(400);
		} else {
			
			_character1.setX(_savedx);
			_character1.setY(_savedy);
		}
		
		GameSounds.playStageSong("/songs/toot3.mp3");
		_bosspicked = true;
		return;
	}
	if(!click.getSource().equals(_toot)) {
		_attacks.clear();
	}
	
	
	
	if(click.getSource().equals(_white)) {
		_skin = "sprites";
		_charpicked = true;
		_character1 = null;
	}
	if(click.getSource().equals(_red)) {
		_skin = "red";
		_charpicked = true;
		_character1 = null;
	}
	if(click.getSource().equals(_green)) {
		_skin = "green";
		_charpicked = true;
		_character1 = null;
	}
	if(click.getSource().equals(_yellow)) {
		_skin = "yellow";
		_charpicked = true;
		_character1 = null;
	}
	if(click.getSource().equals(_rock)) {
		_skin = "rock";
		_charpicked = true;
		_character1 = null;
	}
	if(click.getSource().equals(_black)) {
		_skin = "black";
		_charpicked = true;
		_character1 = null;
	}
	if(click.getSource().equals(_spike)) {
		_skin = "spike";
		_charpicked = true;
		_character1 = null;
	}
	if(click.getSource().equals(_lasers)) {
		_skin = "laser";
		_charpicked = true;
		_character1 = null;
	}
	if(click.getSource().equals(_rock2)) {
		_skin = "rock2";
		_charpicked = true;
		_character1 = null;
	}
	if(click.getSource().equals(_dragon)) {
		_skin = "dragon";
		_charpicked = true;
		_character1 = null;
	}
	if(click.getSource().equals(_skull)) {
		_skin = "skull";
		_charpicked = true;
		_character1 = null;
	}
	if(click.getSource().equals(_twins)) {
		_skin = "twins";
		_charpicked = true;
		_character1 = null;
	}
	if(click.getSource().equals(_spike2)) {
		_skin = "spike2";
		_charpicked = true;
		_character1 = null;
	}
	
	if(click.getSource().equals(_demon)) {
		_skin = "demon";
		_charpicked = true;
		_character1 = null;
	}
	if(click.getSource().equals(_clock)) {
		_skin = "clock";
		_charpicked = true;
		_character1 = null;
	}
	if(click.getSource().equals(_laser2)) {
		_skin = "laser2";
		_charpicked = true;
		_character1 = null;
	}
	if(click.getSource().equals(_ult)) {
		
		_skin = "ult";
		_charpicked = true;
		_character1 = null;
	}
	
	if(_character1 == null) {
		_character1 = new Ninja1Char("one", _skin);
	}
	
	
	// bosses
	
	if(click.getSource().equals(_toot)) {
		if(_beattoot4.equals("f")){
		_boss = new TootBoss();
		_bossclass = "TootBoss";
		_bosspicked = true;
		GameSounds.playStageSong("/songs/toot.mp3");
		} else if(_beattoot5.equals("f")){
			_boss = new TootBoss4();
			_bossclass = "TootBoss4";
			GameSounds.playStageSong("/songs/toot3.mp3");
			_bosspicked = true;
			_character1.setX(400);
			_character1.setY(400);
		} else {
			_boss = new NullBoss();
			GameSounds.stopPlayer();
			_bosspicked = true;
			_bossspawned = true;
		}
		
	}
	if(click.getSource().equals(_swurli)) {
		_boss = new GhostBoss();
		_bossclass = "GhostBoss";
		_bosspicked = true;
		GameSounds.playStageSong("/songs/swurli.mp3");
		_song = "swurli";
	}
	if(click.getSource().equals(_crush)) {
		_boss = new RockBoss();
		_bossclass = "RockBoss";
		_bosspicked = true;
		GameSounds.playStageSong("/songs/crush.mp3");
		_song = "crush";
	}
	if(click.getSource().equals(_spiball)) {
		_boss = new SpikeBoss();
		_bossclass = "SpikeBoss";
		_bosspicked = true;
		GameSounds.playStageSong("/songs/spiball.mp3");
		_song = "spiball";
	}
	if(click.getSource().equals(_laser)) {
		_boss = new BotBoss();
		_bossclass = "BotBoss";
		_bosspicked = true;
		GameSounds.playStageSong("/songs/laser.mp3");
		_song = "laser";
	}
	if(click.getSource().equals(_crunch)) {
		_boss = new RockBoss2();
		_bossclass = "RockBoss2";
		_bosspicked = true;
		GameSounds.playStageSong("/songs/crunch.mp3");
		_song = "crunch";
	}
	if(click.getSource().equals(_droth)) {
		_boss = new DragonBoss();
		_bossclass = "DragonBoss";
		_bosspicked = true;
		GameSounds.playStageSong("/songs/droth.mp3");
		_song = "droth";
	}
	if(click.getSource().equals(_cranius)) {
		_boss = new SkullBoss();
		_bossclass = "SkullBoss";
		_bosspicked = true;
		GameSounds.playStageSong("/songs/cranius.mp3");
		_song = "cranius";
	}
	if(click.getSource().equals(_candm)) {
		_boss = new TwinsBoss();
		_bossclass = "TwinsBoss";
		_bosspicked = true;
		GameSounds.playStageSong("/songs/candm.mp3");
		_song = "candm";
	}
	if(click.getSource().equals(_spiball2)) {
		_boss = new SpikeBoss2();
		_bossclass = "SpikeBoss2";
		_bosspicked = true;
		GameSounds.playStageSong("/songs/spiball2.mp3");
		_song = "spiball2";
	}
	if(click.getSource().equals(_nero)) {
		_boss = new DemonBoss();
		_bossclass = "DemonBoss";
		_bosspicked = true;
		GameSounds.playStageSong("/songs/nero.mp3");
		_song = "nero";
	}
	if(click.getSource().equals(_cryok)) {
		_boss = new ClockBoss();
		_bossclass = "ClockBoss";
		_bosspicked = true;
		GameSounds.playStageSong("/songs/cryok.mp3");
		_song = "cryok";
	}
	if(click.getSource().equals(_cannon)) {
		_boss = new BotBoss2();
		_bossclass = "BotBoss2";
		_bosspicked = true;
		GameSounds.playStageSong("/songs/cannon.mp3");
		_song = "cannon";
	}
	if(click.getSource().equals(_ultimo)) {
		if(!_beatultimo.equals("t") || _beattoot4.equals("t")){
		_boss = new UltimoBoss(_1stultimo);
		_bossclass = "UltimoBoss";
		if(_1stultimo.equals("f")){
			
			GameSounds.playStageSong("/songs/ultimointro.mp3");
			_song = "ultimointro";
			} else {
				
			GameSounds.playStageSong("/songs/ultimo.mp3");
			_song = "ultimo";
			}
		} else {
		_boss = new TootBoss2();
		_bossclass = "TootBoss2";
		GameSounds.playStageSong("/songs/toot2.mp3");
		_song = "toot2";
		}
		_bosspicked = true;
		
	}
	
	
	
	
	
	
	
	//after aug screen
	if(click.getSource().equals(_continue)) {
		_gotpower = false;
		_root1.getChildren().remove(_continue);
	}
	
	//retry
	if(click.getSource().equals(_retry)) {
		String c = _character1.getSkin();
		//String b = _boss.getClass().toString();
		//System.out.println(b);
		_root1.getChildren().remove(_return);
		_root1.getChildren().remove(_retry);
		_character1 = null;
		_paused = false;
		_character1 = new Ninja1Char("one", c);
		_attacks.clear();
		_backdrops.clear();
		_frontdrops.clear();
		_timescale = 1;
		_scrollr = 10;
		GameSounds.playStageSong("/songs/" + _song + ".mp3");
		try {
			if(_bossclass != "UltimoBoss"){
			_boss = (Boss)Class.forName("cd.bosses." + _bossclass).getConstructor().newInstance();
			} else {
				_boss = (UltimoBoss)Class.forName("cd.bosses." + _bossclass).getConstructor(String.class).newInstance(_1stultimo);
			}
			_boss.spawn();
		
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	//return
	if(click.getSource().equals(_return)) {
		
		_root1.getChildren().remove(_return);
		_root1.getChildren().remove(_retry);
		
		if(_character1.getLives() > 0){
			_savedx = _character1.getX();
			_savedy = _character1.getY();
		} else {
			_savedy = 400;
		}
		if(_paused) {
			//_animationTimer.start();
			_paused = false;
			
		}
		_buttonsremoved = false;
		_backdrops.clear();
		_frontdrops.clear();
		_charpicked = false;
		_bosspicked = false;
		_character1 = null;
		_timescale = 1;
		
		_scroll = new Image("scroll/space.png");
		
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
			if(_boss.getID().equals("clockboss")){
				if(_beatcryok.equals("f")){
					_gotpower = true;
					_power = "cryok";
					_newskin = "clock";
				}
				_beatcryok = "t";
			}
			if(_boss.getID().equals("botboss2")){
				if(_beatcannon.equals("f")){
					_gotpower = true;
					_power = "cannon";
					_newskin = "laser2";
				}
				_beatcannon = "t";
			}
			if(_boss.getID().equals("ultimoboss")){
				if(_beattoot4.equals("t") && _beatultimo2.equals("f")){
					_gotpower = true;
					_power = "ultimo";
					_newskin = "ult";
					_beatultimo2 = "t";
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
			write(writer, _beatcryok);
			write(writer, _beatcannon);
			write(writer, _1stultimo);
			write(writer, _beatultimo);
			write(writer, _beattoot2);
			write(writer, _beattoot3);
			write(writer, _beattoot4);
			write(writer, _beatultimo);
			write(writer, _beatultimo2);
			write(writer, _beattoot5);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
		if((!_boss.getID().equals("tootboss3"))){
			if(!_boss.getID().equals("tootboss4")){
			_attacks.clear();
			_boss = null;
			}
			_bossspawned = false;
			
			
		} 
		
		
		
		
		stopText();
		
		
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

public static void write(BufferedWriter writer, String s) 
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

public static void setScroll(Image i) {
	_scroll = i;
}

public static void writeData() {
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
		write(writer, _beatcryok);
		write(writer, _beatcannon);
		write(writer, _1stultimo);
		write(writer, _beatultimo);
		write(writer, _beattoot2);
		write(writer, _beattoot3);
		write(writer, _beattoot4);
		write(writer, _beatultimo2);
		write(writer, _beattoot5);
		writer.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void addReset() {
	_reset.setLayoutX(797);
	_reset.setLayoutY(493);
	_reset.setMinWidth(86);
	_reset.setMinHeight(19);
	
	
	_root1.getChildren().add(_reset);
}

}


	


