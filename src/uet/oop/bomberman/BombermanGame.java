package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;

public class BombermanGame extends Application {
    
    public static int WIDTH = 31;
    public static int HEIGHT = 13;

    public static int LEVELS = 1;

    private static char[][] map;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        LoadMap(1);

        //Entity bomberman = new Bomber(2, 2, Sprite.player_right.getFxImage());
        //entities.add(bomberman);
    }

    public int CDint(String s) {
        int a=0;
        for (int i = 0; i<s.length(); i++) {
            a=a*10+ (s.charAt(i)-48);
        }
        return a;
    }
    public void createEntities() {
        // TODO: t???o c??c Entity c???a m??n ch??i
        // TODO: sau khi t???o xong, g???i _board.addEntity() ????? th??m Entity v??o game

        // TODO: ph???n code m???u ??? d?????i ????? h?????ng d???n c??ch th??m c??c lo???i Entity v??o game
        // TODO: h??y x??a n?? khi ho??n th??nh ch???c n??ng load m??n ch??i t??? t???p c???u h??nh
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                int pos = j + i * WIDTH;
                char c = map[i][j];
                switch (c) {
                    // Th??m grass
                    case ' ':
                        stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                        break;
                    // Th??m Wall
                    case '#':
                        stillObjects.add(new Grass(i, j, Sprite.wall.getFxImage()));
                        break;
                    // Th??m Portal
                    case 'x':
                        stillObjects.add(new Grass(i, j, Sprite.portal.getFxImage()));
                        break;
                    // Th??m brick
                    case '*':
                        stillObjects.add(new Grass(i, j, Sprite.brick.getFxImage()));
                        break;
                    // Th??m Bomber
                    case 'p':
                        stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                        stillObjects.add(new Grass(i, j, Sprite.player_right.getFxImage()));
                        break;

                    // Th??m balloon
                    case '1':
                        stillObjects.add(new Grass(i, j, Sprite.minvo_dead.getFxImage()));
                        break;
                    // Th??m oneal
                    case '2':
                        stillObjects.add(new Grass(i, j, Sprite.kondoria_dead.getFxImage()));
                        break;
                    // Th??m doll
                    case '3':
                        stillObjects.add(new Grass(i, j, Sprite.doll_dead.getFxImage()));
                        break;
                    // Th??m oneal
                    // Th??m BomItem
                    case 'b':
                        stillObjects.add(new Grass(i, j, Sprite.powerup_bombs.getFxImage()));
                        break;
                    // Th??m SpeedItem
                    case 's':
                        stillObjects.add(new Grass(i, j, Sprite.powerup_speed.getFxImage()));
                        break;
                    // Th??m FlameItem
                    case 'f':
                        stillObjects.add(new Grass(i, j, Sprite.powerup_flames.getFxImage()));
                        break;

                    default:
                        stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                        break;

                }
            }
        }
    }

    public void LoadMap(int level) {
        List<String> list= new ArrayList<>();
        try {
            FileReader fi = new FileReader("res\\levels\\level" + level + ".txt");
            BufferedReader bu = new BufferedReader(fi);
            String line =bu.readLine();
            while (!line.equals("")) {
                System.out.print(line + "\n");
                list.add(line);
                line = bu.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] arr = list.get(0).trim().split(" ");
        LEVELS = CDint(arr[0]);
        HEIGHT = CDint(arr[1]);
        WIDTH = CDint(arr[2]);
        map = new char[WIDTH][HEIGHT];
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                map[i][j] = list.get(j + 1).charAt(i);
                //System.out.print(list.get(j + 1).charAt(i));
            }
            System.out.print("\n");
        }

        createEntities();
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

}
