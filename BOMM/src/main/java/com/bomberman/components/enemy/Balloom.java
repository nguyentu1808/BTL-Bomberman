package com.bomberman.components.enemy;


import com.almasb.fxgl.texture.AnimationChannel;
import com.bomberman.Type_Game;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;


public class Balloom extends Normal {

    public Balloom() {
        super();
        onCollisionBegin(Type_Game.BALLOOM_E, Type_Game.WALL,
                (b, w) -> b.getComponent(Balloom.class).turn());

        onCollisionBegin(Type_Game.BALLOOM_E, Type_Game.BOMB,
                (b, bo) -> b.getComponent(Balloom.class).turn());

        onCollisionBegin(Type_Game.BALLOOM_E, Type_Game.AROUND_WALL,
                (b, w) -> b.getComponent(Balloom.class).turn());

        onCollisionBegin(Type_Game.BALLOOM_E, Type_Game.BRICK,
                (b, br) -> b.getComponent(Balloom.class).turn());

        onCollisionBegin(Type_Game.BALLOOM_E, Type_Game.GRASS,
                (b, gr) -> b.getComponent(Balloom.class).turn());

        onCollisionBegin(Type_Game.BALLOOM_E, Type_Game.CORAL,
                (b, co) -> b.getComponent(Balloom.class).turn());

    }

    @Override
    protected void setAnimationMove() {
        animDie = new AnimationChannel(image("sprites.png"), 16, SIZE_FLAME, SIZE_FLAME,
                Duration.seconds(ANIM_TIME), 22, 22);
        animWalkRight = new AnimationChannel(image("sprites.png"), 16, SIZE_FLAME, SIZE_FLAME,
                Duration.seconds(ANIM_TIME), 19, 21);
        animWalkLeft = new AnimationChannel(image("sprites.png"), 16, SIZE_FLAME, SIZE_FLAME,
                Duration.seconds(ANIM_TIME), 16, 18);
        animStop = new AnimationChannel(image("sprites.png"), 16, SIZE_FLAME, SIZE_FLAME,
                Duration.seconds(1), 16, 22);
    }

    @Override
    public void enemyDie() {
        super.enemyDie();

        int BALLOOM_SCORE = 100;
        showScore(BALLOOM_SCORE);
        inc("score", BALLOOM_SCORE);
    }
}
