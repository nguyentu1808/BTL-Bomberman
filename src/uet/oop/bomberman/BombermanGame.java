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
        // TODO: tạo các Entity của màn chơi
        // TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

        // TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
        // TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                int pos = j + i * WIDTH;
                char c = map[i][j];
                switch (c) {
                    // Thêm grass
                    case ' ':
                        stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                        break;
                    // Thêm Wall
                    case '#':
                        stillObjects.add(new Grass(i, j, Sprite.wall.getFxImage()));
                        break;
                    // Thêm Portal
                    case 'x':
                        stillObjects.add(new Grass(i, j, Sprite.portal.getFxImage()));
                        break;
                    // Thêm brick
                    case '*':
                        stillObjects.add(new Grass(i, j, Sprite.brick.getFxImage()));
                        break;
                    // Thêm Bomber
                    case 'p':
                        stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                        stillObjects.add(new Grass(i, j, Sprite.player_right.getFxImage()));
                        break;

                    // Thêm balloon
                    case '1':
                        stillObjects.add(new Grass(i, j, Sprite.minvo_dead.getFxImage()));
                        break;
                    // Thêm oneal
                    case '2':
                        stillObjects.add(new Grass(i, j, Sprite.kondoria_dead.getFxImage()));
                        break;
                    // Thêm doll
                    case '3':
                        stillObjects.add(new Grass(i, j, Sprite.doll_dead.getFxImage()));
                        break;
                    // Thêm oneal
                    // Thêm BomItem
                    case 'b':
                        stillObjects.add(new Grass(i, j, Sprite.powerup_bombs.getFxImage()));
                        break;
                    // Thêm SpeedItem
                    case 's':
                        stillObjects.add(new Grass(i, j, Sprite.powerup_speed.getFxImage()));
                        break;
                    // Thêm FlameItem
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
