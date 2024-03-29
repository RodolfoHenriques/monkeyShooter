package org.academiadecodigo.allpainnogain.gameproject.Screens;

import org.academiadecodigo.allpainnogain.gameproject.BattleField;
import org.academiadecodigo.allpainnogain.gameproject.Collidable;
import org.academiadecodigo.allpainnogain.gameproject.Players.Player;
import org.academiadecodigo.allpainnogain.gameproject.Players.Player1;
import org.academiadecodigo.allpainnogain.gameproject.Players.Player2;
import org.academiadecodigo.allpainnogain.gameproject.Sound;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class WinnerScreen implements KeyboardHandler {

    private Keyboard keyboard;

    private Picture pictureWinnerScreenPlayer1 = new Picture(0, 0, "/Resources/winner1.png");
    private Picture pictureWinnerScreenPlayer2 = new Picture(0, 0, "/Resources/winner2.png");

    //change values of rectangle
    private Rectangle selectTryAgain = new Rectangle(BattleField.WIDTH-340, 15,450,100);
    private Rectangle selectExit = new Rectangle(BattleField.WIDTH-130, BattleField.HEIGHT,220,100);

    private boolean rectangleTryAgain = true;
    private boolean rectangleExit;

    private boolean space;
    private boolean hasWinnerScreen;

    private Sound soundWinnerScreen = new Sound("/Resources/clapping.wav");


    public WinnerScreen() {

        keyboard = new Keyboard(this);
        initKeyboard();
    }

    public void createWinnerScreen() {
        if (Collidable.listTanks.get(0).getHealth() <= 0) {
            pictureWinnerScreenPlayer2.draw();
        } else {
            pictureWinnerScreenPlayer1.draw();
        }

        selectTryAgain.draw();
        hasWinnerScreen = true;
        soundWinnerScreen.play(true);
    }


    public void deleteWinnerScreen() {

        pictureWinnerScreenPlayer1.delete();
        pictureWinnerScreenPlayer2.delete();
        selectExit.delete();
        selectTryAgain.delete();
        soundWinnerScreen.close();
    }


    public void initKeyboard() {

        int[] keys = new int[]{KeyboardEvent.KEY_UP,
                KeyboardEvent.KEY_DOWN,
                KeyboardEvent.KEY_SPACE};

        for (int key: keys) {
            KeyboardEvent event = new KeyboardEvent();
            event.setKey(key);
            event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(event);
        }
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (hasWinnerScreen) {

            switch (keyboardEvent.getKey()) {

                case KeyboardEvent.KEY_UP:
                    selectExit.delete();
                    selectTryAgain.draw();

                    rectangleTryAgain = true;
                    rectangleExit = false;

                    break;

                case KeyboardEvent.KEY_DOWN:
                    selectTryAgain.delete();
                    selectExit.draw();

                    rectangleExit = true;
                    rectangleTryAgain = false;

                    break;

                case KeyboardEvent.KEY_SPACE:
                    deleteWinnerScreen();

                    space = true;
                    hasWinnerScreen = false;
            }
        }
    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


    public boolean getRectangleTryAgain() {
        return rectangleTryAgain;
    }

    public boolean getRectangleExit() {
        return rectangleExit;
    }

    public boolean getSpace() {
        return space;
    }

    public boolean getHasWinnerScreen() {
        return hasWinnerScreen;
    }

    public void setRectangleTryAgain(boolean rectangleStart) {
        this.rectangleTryAgain = rectangleStart;
    }
}
