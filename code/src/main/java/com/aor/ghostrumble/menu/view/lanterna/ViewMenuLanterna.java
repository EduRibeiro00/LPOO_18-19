package com.aor.ghostrumble.menu.view.lanterna;

import com.aor.ghostrumble.menu.event.EventChangeOption;
import com.aor.ghostrumble.menu.event.EventConfirmOption;
import com.aor.ghostrumble.menu.model.MenuModel;
import com.aor.ghostrumble.menu.view.ViewMenu;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class ViewMenuLanterna extends ViewMenu {

    private Screen screen;
    private int width;
    private int height;

    public ViewMenuLanterna(Screen screen, int width, int height) {
        this.screen = screen;
        this.width = width;
        this.height = height;
    }

    private void createEvent(KeyStroke key) {

        switch(key.getKeyType()) {

            case ArrowUp: case ArrowDown:
                event = new EventChangeOption();
                break;

            case Enter:
                event = new EventConfirmOption();
                break;

            default:
                break;

        }

    }

    public void prepareStateChange() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#32204E"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
    }

    @Override
    public void handleInput() {
        try {

            KeyStroke key = screen.readInput();
            createEvent(key);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void drawAll(MenuModel model) {

        TextGraphics graphics = screen.newTextGraphics();

        graphics.setBackgroundColor(TextColor.Factory.fromString("#32204E"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#CEC20F"));

        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(width / 2 - 8, height / 3), model.getTitle());
        graphics.disableModifiers(SGR.BOLD);

        if(model.willPlay()) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#009999"));
            graphics.enableModifiers(SGR.BOLD);
        }

        graphics.putString(new TerminalPosition((width - model.getFirst().length()) / 2, (int) (1.5 * height) / 3), model.getFirst());
        graphics.disableModifiers(SGR.BOLD);

        if (model.willPlay()) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#CEC20F"));
        }
        else {
            graphics.setForegroundColor(TextColor.Factory.fromString("#009999"));
            graphics.enableModifiers(SGR.BOLD);
        }

        graphics.putString(new TerminalPosition((width - model.getSecond().length()) / 2, 2 * height / 3), model.getSecond());
        graphics.disableModifiers(SGR.BOLD);

        try {
            screen.refresh();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
