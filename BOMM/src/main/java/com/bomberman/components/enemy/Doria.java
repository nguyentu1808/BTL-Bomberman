package com.bomberman.components.enemy;

import com.almasb.fxgl.texture.AnimationChannel;
import com.bomberman.Type_Game;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Doria extends Oneal {
    private double timeChangeMove = 0.0;

    public Doria() {
        super();
        onCollisionBegin(Type_Game.DORIA_E, Type_Game.AROUND_WALL,
                (d, w) -> d.getComponent(Doria.class).turn());

        onCollisionBegin(Type_Game.DORIA_E, Type_Game.WALL,
                (d, w) -> d.getComponent(Doria.class).turn());

        onCollisionBegin(Type_Game.DORIA_E, Type_Game.BOMB,
                (d, w) -> d.getComponent(Doria.class).turn());

        setRangeDetectPlayer(120);
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
        timeChangeMove += tpf;
        if (!isDetectPlayer() && timeChangeMove > 3.0) {
            timeChangeMove = 0;
            turn();
        }
    }


    @Override
    protected void setAnimationMove() {
        animDie = new AnimationChannel(image("sprites.png"), 16, SIZE_FLAME, SIZE_FLAME,
                Duration.seconds(ANIM_TIME), 30, 30);
        animWalkRight = new AnimationChannel(image("sprites.png"), 16, SIZE_FLAME, SIZE_FLAME,
                Duration.seconds(ANIM_TIME), 27, 29);
        animWalkLeft = new AnimationChannel(image("sprites.png"), 16, SIZE_FLAME, SIZE_FLAME,
                Duration.seconds(ANIM_TIME), 24, 26);
        animStop = new AnimationChannel(image("sprites.png"), 16, SIZE_FLAME, SIZE_FLAME,
                Duration.seconds(1), 24, 30);
    }

    @Override
    public void enemyDie() {
        int DORIA_SCORE = 500;
        inc("score", DORIA_SCORE);
        showScore(DORIA_SCORE);

        die = true;
        dx = 0;
        dy = 0;
        astar.pause();
        texture.loopNoOverride(animDie);
    }
}
