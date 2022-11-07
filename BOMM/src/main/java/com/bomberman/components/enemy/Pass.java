package com.bomberman.components.enemy;

import com.almasb.fxgl.texture.AnimationChannel;
import com.bomberman.Type_Game;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Pass extends com.bomberman.components.enemy.Oneal {
    public Pass() {
        super();
        onCollisionBegin(Type_Game.PASS_E, Type_Game.AROUND_WALL,
                (p, w) -> p.getComponent(Pass.class).turn());

        onCollisionBegin(Type_Game.PASS_E, Type_Game.WALL,
                (p, w) -> p.getComponent(Pass.class).turn());

        onCollisionBegin(Type_Game.PASS_E, Type_Game.BRICK,
                (p, br) -> p.getComponent(Pass.class).turn());

        onCollisionBegin(Type_Game.PASS_E, Type_Game.GRASS,
                (p, gr) -> p.getComponent(Pass.class).turn());

        onCollisionBegin(Type_Game.PASS_E, Type_Game.CORAL,
                (p, co) -> p.getComponent(Pass.class).turn());

        onCollisionBegin(Type_Game.PASS_E, Type_Game.BOMB,
                (p, w) -> p.getComponent(Pass.class).turn());

        setRangeDetectPlayer(120);
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
    }


    @Override
    protected void setAnimationMove() {
        animDie = new AnimationChannel(image("sprites.png"), 16, SIZE_FLAME, SIZE_FLAME,
                Duration.seconds(ANIM_TIME), 70, 70);
        animWalkRight = new AnimationChannel(image("sprites.png"), 16, SIZE_FLAME, SIZE_FLAME,
                Duration.seconds(ANIM_TIME), 67, 69);
        animWalkLeft = new AnimationChannel(image("sprites.png"), 16, SIZE_FLAME, SIZE_FLAME,
                Duration.seconds(ANIM_TIME), 64, 66);
        animStop = new AnimationChannel(image("sprites.png"), 16, SIZE_FLAME, SIZE_FLAME,
                Duration.seconds(1), 64, 69);
    }

    @Override
    public void enemyDie() {
        int PASS_SCORE = 300;
        showScore(PASS_SCORE);
        inc("score", PASS_SCORE);

        die = true;
        dx = 0;
        dy = 0;
        astar.pause();
        texture.loopNoOverride(animDie);
    }
}
