package com.bomberman;

import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.bomberman.components.Block;
import com.bomberman.components.Bomb;
import com.bomberman.components.Flame;
import com.bomberman.components.Player;
import com.bomberman.components.enemy.*;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.geto;
import static com.bomberman.Const_Bomberman.*;

/**
 * Entity factory. Used to create all entities in the game.
 */
public class Element_Bomberman implements EntityFactory {
    private final int radius = SIZE_BLOCK / 2;

    //BACKGROUND

    @Spawns("background")
    public Entity newBackground(SpawnData data) {
        return FXGL.entityBuilder(data)
                .view(new Rectangle(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, Color.LIGHTGRAY))
                .zIndex(-100)
                .with(new IrremovableComponent())
                .build();
    }

    //PLAYER

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        var physics = new PhysicsComponent();

        var fixtureDef = new FixtureDef();
        fixtureDef.setFriction(0);
        fixtureDef.setDensity(0.1f);
        physics.setFixtureDef(fixtureDef);

        var bodyDef = new BodyDef();
        bodyDef.setFixedRotation(true);
        bodyDef.setType(BodyType.DYNAMIC);
        physics.setBodyDef(bodyDef);

        return FXGL.entityBuilder(data)
                .type(Type_Game.PLAYER)
                .bbox(new HitBox(BoundingShape.circle(radius)))
                .atAnchored(new Point2D(radius, radius), new Point2D(radius, radius))
                .with(physics)
                .with(new Player())
                .with(new CollidableComponent(true))
                .with(new CellMoveComponent(SIZE_BLOCK, SIZE_BLOCK, ENEMY_SPEED_BASE))
                .with(new AStarMoveComponent(new LazyValue<>(() -> geto("grid"))))
                .zIndex(5)
                .build();
    }

    //BOMB AND FLAME

    @Spawns("bomb")
    public Entity newBomb(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.BOMB)
                .with(new Bomb())
                .bbox(new HitBox(new Point2D(2, 2), BoundingShape.circle(radius - 2)))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("wall_bomb")
    public Entity newWallBomb(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.WALL_BOMB)
                .bbox(new HitBox(new Point2D(0, 0), BoundingShape.box(SIZE_BLOCK, SIZE_BLOCK)))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("centerFlame")
    public Entity newCenterFlame(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.FLAME)
                .with(new Flame(128, 130))
                .viewWithBBox(new Rectangle(SIZE_BLOCK / 2.0 - 3, SIZE_BLOCK / 2.0 - 3, Color.TRANSPARENT))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new CollidableComponent(true))
                .zIndex(1)
                .build();
    }

    @Spawns("horizontalFlame")
    public Entity newHorizontalFlame(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.FLAME)
                .with(new Flame(147, 149))
                .viewWithBBox(new Rectangle(SIZE_BLOCK / 2.0 - 3, SIZE_BLOCK / 2.0 - 3, Color.TRANSPARENT))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new CollidableComponent(true))
                .zIndex(1)
                .build();
    }

    @Spawns("verticalFlame")
    public Entity newVerticalFlame(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.FLAME)
                .with(new Flame(160, 162))
                .viewWithBBox(new Rectangle(SIZE_BLOCK / 2.0 - 3, SIZE_BLOCK / 2.0 - 3, Color.TRANSPARENT))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new CollidableComponent(true))
                .zIndex(1)
                .build();
    }

    @Spawns("aboveEFlame")
    public Entity newAboveEFlame(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.FLAME)
                .with(new Flame(144, 146))
                .viewWithBBox(new Rectangle(SIZE_BLOCK / 2.0 - 3, SIZE_BLOCK / 2.0 - 3, Color.TRANSPARENT))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new CollidableComponent(true))
                .zIndex(1)
                .build();
    }

    @Spawns("bottomEFlame")
    public Entity newBottomEFlame(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.FLAME)
                .with(new Flame(176, 178))
                .viewWithBBox(new Rectangle(SIZE_BLOCK / 2.0 - 3, SIZE_BLOCK / 2.0 - 3, Color.TRANSPARENT))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new CollidableComponent(true))
                .zIndex(1)
                .build();
    }

    @Spawns("leftEFlame")
    public Entity newLeftEFlame(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.FLAME)
                .with(new Flame(131, 133))
                .viewWithBBox(new Rectangle(SIZE_BLOCK / 2.0 - 3, SIZE_BLOCK / 2.0 - 3, Color.TRANSPARENT))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new CollidableComponent(true))
                .zIndex(1)
                .build();
    }

    @Spawns("rightEFlame")
    public Entity newRightEFlame(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.FLAME)
                .with(new Flame(163, 165))
                .viewWithBBox(new Rectangle(SIZE_BLOCK / 2.0 - 3, SIZE_BLOCK / 2.0 - 3, Color.TRANSPARENT))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new CollidableComponent(true))
                .zIndex(1)
                .build();
    }

    @Spawns("check_flame")
    public Entity newCheckFlame(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.CHECK_FLAME)
                .viewWithBBox(new Rectangle(SIZE_BLOCK, SIZE_BLOCK, Color.TRANSPARENT))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new CollidableComponent(true))
                .zIndex(1)
                .build();
    }

    //ENEMY ENTITIES

    @Spawns("balloom_e")
    public Entity newBalloom(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.BALLOOM_E)
                .bbox(new HitBox(BoundingShape.circle(radius - 2)))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new Balloom())
                .with(new CollidableComponent(true))
                .zIndex(2)
                .build();
    }

    @Spawns("dahl_e")
    public Entity newDahl(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.DAHL_E)
                .bbox(new HitBox(BoundingShape.circle(radius - 2)))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new Dahl())
                .with(new CollidableComponent(true))
                .zIndex(2)
                .build();
    }

    @Spawns("ovape_e")
    public Entity newOvape(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.OVAPE_E)
                .bbox(new HitBox(BoundingShape.circle(radius - 2)))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new Ovape())
                .with(new CollidableComponent(true))
                .zIndex(2)
                .build();
    }

    @Spawns("oneal_e")
    public Entity newOneal(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.ONEAL_E)
                .bbox(new HitBox(BoundingShape.circle(radius - 2)))
                .with(new CollidableComponent(true))
                .atAnchored(new Point2D(radius, radius), new Point2D(radius, radius))
                .with(new CellMoveComponent(SIZE_BLOCK, SIZE_BLOCK, ENEMY_SPEED_BASE))
                .with(new AStarMoveComponent(new LazyValue<>(() -> geto("grid"))))
                .with(new Oneal())
                .zIndex(2)
                .build();
    }

    @Spawns("pass_e")
    public Entity newPass(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.PASS_E)
                .bbox(new HitBox(BoundingShape.circle(radius - 2)))
                .with(new CollidableComponent(true))
                .atAnchored(new Point2D(radius, radius), new Point2D(radius, radius))
                .with(new CellMoveComponent(SIZE_BLOCK, SIZE_BLOCK, ENEMY_SPEED_BASE))
                .with(new AStarMoveComponent(new LazyValue<>(() -> geto("grid"))))
                .with(new Pass())
                .zIndex(2)
                .build();
    }

    @Spawns("doria_e")
    public Entity newDoria(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.DORIA_E)
                .bbox(new HitBox(BoundingShape.circle(radius - 2)))
                .with(new CollidableComponent(true))
                .atAnchored(new Point2D(radius, radius), new Point2D(radius, radius))
                .with(new CellMoveComponent(SIZE_BLOCK, SIZE_BLOCK, ENEMY_SPEED_BASE + 20))
                .with(new AStarMoveComponent(new LazyValue<>(() -> geto("_grid"))))
                .with(new Doria())
                .zIndex(2)
                .build();
    }

    @Spawns("enemyDie")
    public Entity newEnemyDie(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.ENEMY_DIE)
                .bbox(new HitBox(BoundingShape.circle(radius - 2)))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new EnemyDie())
                .build();
    }

    //BLOCKS

    @Spawns("wall")
    public Entity newWall(SpawnData data) {
        var width = (int) data.get("width");
        var height = (int) data.get("height");

        return FXGL.entityBuilder(data)
                .type(Type_Game.WALL)
                .bbox(new HitBox(BoundingShape.box(width, height)))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("around_wall")
    public Entity newArWall(SpawnData data) {
        var width = (int) data.get("width");
        var height = (int) data.get("height");

        return FXGL.entityBuilder(data)
                .type(Type_Game.AROUND_WALL)
                .bbox(new HitBox(BoundingShape.box(width, height)))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("brick")
    public Entity newBrick(SpawnData data) {
        var width = (int) data.get("width");
        var height = (int) data.get("height");

        return FXGL.entityBuilder(data)
                .type(Type_Game.BRICK)
                .bbox(new HitBox(BoundingShape.box(width, height)))
                .with(new Block(104, 104, TIME_PER_LEVEL))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("brick_break")
    public Entity newBrickBreak(SpawnData data) {
        var boundingShape = BoundingShape.box(
                SIZE_BLOCK / 2.0f - 3,
                SIZE_BLOCK / 2.0f - 3);

        var hitBox = new HitBox(boundingShape);

        return FXGL.entityBuilder(data)
                .type(Type_Game.BRICK_BREAK)
                .with(new Block(105, 107, 1))
                .bbox(hitBox)
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .zIndex(1)
                .build();
    }

    @Spawns("grass")
    public Entity newGrass(SpawnData data) {
        var width = (int) data.get("width");
        var height = (int) data.get("height");

        return FXGL.entityBuilder(data)
                .type(Type_Game.GRASS)
                .bbox(new HitBox(BoundingShape.box(width, height)))
                .with(new Block(88, 88, TIME_PER_LEVEL))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("grass_break")
    public Entity newGrassBreak(SpawnData data) {
        var boundingShape = BoundingShape.box(
                SIZE_BLOCK / 2.0f - 3,
                SIZE_BLOCK / 2.0f - 3);

        var hitBox = new HitBox(boundingShape);

        return FXGL.entityBuilder(data)
                .type(Type_Game.GRASS_BREAK)
                .bbox(hitBox)
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new Block(89, 91, 1))
                .zIndex(1)
                .build();
    }

    @Spawns("coral")
    public Entity newCoral(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.CORAL)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),
                        data.<Integer>get("height"))))
                .with(new Block(92, 92, TIME_PER_LEVEL))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("coral_break")
    public Entity newCoralBreak(SpawnData data) {
        var boundingShape = BoundingShape.box(
                SIZE_BLOCK / 2.0f - 3,
                SIZE_BLOCK / 2.0f - 3);

        var hitBox = new HitBox(boundingShape);

        return FXGL.entityBuilder(data)
                .type(Type_Game.CORAL_BREAK)
                .bbox(hitBox)
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .with(new Block(93, 95, 1))
                .zIndex(1)
                .build();
    }

    //ITEMS

    @Spawns("speedItem")
    public Entity newItem3(SpawnData data) {
        return entityBuilder(data)
                .type(Type_Game.SPEED_ITEM)
                .view("photo/powerup_speed.png")
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("bombsItem")
    public Entity newBombItem(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.BOMB_ITEM)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .view("photo/powerup_bombs.png")
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("flameItem")
    public Entity newFlameItem(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.FLAME_ITEM)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .view("photo/powerup_flames.png")
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("flamePassItem")
    public Entity newFlamePassItem(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Type_Game.FLAME_PASS_ITEM)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .view("photo/powerup_flamepass.png")
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("portal")
    public Entity newPortal(SpawnData data) {
        var width = (int) data.get("width");
        var height = (int) data.get("height");

        var boundingShape = BoundingShape.box(width, height);
        var hitBox = new HitBox(boundingShape);

        return FXGL.entityBuilder(data)
                .type(Type_Game.PORTAL)
                .bbox(hitBox)
                .view("photo/portal.png")
                .with(new CollidableComponent(true))
                .build();
    }
}

