package com.aor.ghostrumble.menu;

import com.aor.ghostrumble.State;
import com.aor.ghostrumble.factory.ViewAbstractFactory;
import com.aor.ghostrumble.menu.model.MenuModel;
import com.aor.ghostrumble.menu.view.ViewMenu;

public abstract class MenuState extends State {

    protected MenuModel model;
    protected ViewMenu view;

    public MenuState(ViewAbstractFactory factory, MenuModel model) {
        super(factory);
        this.model = model;
        this.view = factory.createMenuView();
    }

    @Override
    public boolean keepGoing() {
        return true;
    }

    public void handleInput() { view.handleInput(); }

    public void draw() {
        view.drawAll(model);
    }

    public abstract void update();

}
