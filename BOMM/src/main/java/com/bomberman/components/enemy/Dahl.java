package com.bomberman.components.enemy;

import com.almasb.fxgl.texture.AnimationChannel;
import com.bomberman.Type_Game;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.bomberman.Const_Bomberman.ENEMY_SPEED_BASE;

public class Dahl extends Normal {
    public Dahl() {
        super();
        onCollisionBegin(Type_Game.DAHL_E, Type_Game.WALL,
                (d, w) -> d.getComponent(Dahl.class).turn());

        onCollisionBegin(Type_Game.DAHL_E, Type_Game.BOMB,
                (d, b) -> d.getComponent(Dahl.class).turn());

        onCollisionBegin(Type_Game.DAHL_E, Type_Game.AROUND_WALL,
                (d, w) -> d.getComponent(Dahl.class).turn());

        onCollisionBegin(Type_Game.DAHL_E, Type_Game.BRICK,
                (d, br) -> d.getComponent(Dahl.class).turn());

        onCollisionBegin(Type_Game.DAHL_E, Type_Game.GRASS,
                (d, gr) -> d.getComponent(Dahl.class).turn());

        onCollisionBegin(Type_Game.DAHL_E, Type_Game.CORAL,
                (d, c) -> d.getComponent(Dahl.class).turn());

    }

    @Override
    protected void setAnimationMove() {
        animDie = new AnimationChannel(image("sprites.png"), 16, SIZE_FLAME, SIZE_FLAME,
                Duration.seconds(ANIM_TIME), 54, 54);
        animWalkRight = new AnimationChannel(image("sprites.png"), 16, SIZE_FLAME, SIZE_FLAME,
                Duration.seconds(ANIM_TIME), 51, 53);
        animWalkLeft = new AnimationChannel(image("sprites.png"), 16, SIZE_FLAME, SIZE_FLAME,
                Duration.seconds(ANIM_TIME), 48, 50);
        animStop = new AnimationChannel(image("sprites.png"), 16, SIZE_FLAME, SIZE_FLAME,
                Duration.seconds(1), 48, 53);
    }

    @Override
    protected double getRandom() {
        double coefficient = 0;
        while (coefficient < 0.7) {
            coefficient = Math.random();
        }
        double speed = ENEMY_SPEED_BASE * (coefficient + (Math.random() > 0.5 ? 0 : 0.8));
        return Math.random() > 0.5 ? speed : -speed;
    }

    @Override
    public void enemyDie() {
        super.enemyDie();

        int DAHL_SCORE = 150;
        showScore(DAHL_SCORE);
        inc("score", DAHL_SCORE);
    }
}
