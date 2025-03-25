import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class drawHangman extends JPanel{

    static int guesses = 6;

    drawHangman() {

        this.setBounds(350, 75, 800, 600);
        this.setOpaque(false);

    }

    public void setDraw(int guesses) {
        drawHangman.guesses = guesses;
        repaint();
    }
    public void setDraw() {
        drawHangman.guesses = -1;
        repaint();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(75, 400, 300, 400);
        g2d.drawLine(300, 75, 300, 400);
        g2d.drawLine(150, 75, 300, 75);
        g2d.drawLine(150, 75, 150, 100);
        g2d.drawLine(250, 75, 300, 125);

        //hangman
        Graphics2D hangman = (Graphics2D) g;

        if (guesses == -1) hangman.setColor(Color.BLACK);

        hangman.setStroke(new BasicStroke(5));

        if (guesses <= 5) hangman.drawOval(117, 103, 65, 65);//head
        if (guesses <= 4) hangman.drawLine(150, 173, 150, 300); //torso
        if (guesses <= 3) hangman.drawLine(100, 200, 150, 225); //leftArm
        if (guesses <= 2) hangman.drawLine(150, 225, 200, 200); //rightArm
        if (guesses <= 1) hangman.drawLine(150, 300, 200, 350); //leftLeg
        if (guesses == 0) hangman.drawLine(150, 300, 100, 350); //rightLeg

    }

    
}
