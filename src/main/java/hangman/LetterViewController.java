package hangman;

import javafx.scene.control.Label;

public class LetterViewController {

    public Label Lbl;
    char theCharacter;
    public void setLetter(char theCharacter)
    {
        this.theCharacter = theCharacter;
        Lbl.setText(theCharacter + "");
        Lbl.setVisible(false);
    }
    public void showLetter()
    {
        Lbl.setVisible(true);
    }

    public String getLetter() {
        return  theCharacter + "";
    }
    public boolean isLetterShown()
    {
        return Lbl.isVisible();
    }
}
