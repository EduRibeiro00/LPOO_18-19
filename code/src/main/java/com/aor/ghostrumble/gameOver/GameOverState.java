package com.aor.ghostrumble.gameOver;

import com.aor.ghostrumble.State;
import com.aor.ghostrumble.ViewAbstractFactory;
import com.aor.ghostrumble.gameOver.model.GameOverModel;
import com.aor.ghostrumble.gameOver.view.ViewGameOver;
import com.aor.ghostrumble.play.GameState;

public class GameOverState extends State {

    private GameOverModel model;
    private ViewGameOver view;

    public GameOverState(ViewAbstractFactory factory, int score) {
        super(factory);
        this.model = new GameOverModel(score);
        this.view = factory.createGameOverView();
    }

    public void handleInput() { view.handleInput(); }

    public void draw() {
        view.drawAll(model);
    }

    public void update() {

    }

}
