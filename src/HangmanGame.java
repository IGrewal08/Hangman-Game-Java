import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanGame implements ActionListener{
    
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel contentPane;

    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;

    private JLabel stringPattern;

    private HangmanManager manager;

    private JPanel gamePanel;

    private JPanel hangmanDrawing;


    HangmanGame() {

        frame = new JFrame("Evil Hangman Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.BLACK);

        
        contentPane = new JPanel(new BorderLayout());
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);

        //Create the different menus (seperate methods)

        
        contentPane.add(createTitlePanel(), "Title");
        contentPane.add(createDiffPanel(), "Difficulty");
        contentPane.add(createGamePanel(), "Game");

        frame.add(contentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private JPanel createTitlePanel() {

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(null);
        titlePanel.setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("<html> <font color='red'>Evil</font>"
        + "<font color='white'>Hangman</font></html>");

        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 75));

        ImageIcon image = new ImageIcon(new ImageIcon("lib/imageHangman.png").getImage().getScaledInstance(268, 346, Image.SCALE_DEFAULT));
        JLabel imageHolder = new JLabel(image);
        imageHolder.setBounds(25, 75, 500, 500);

        JButton startButton = new JButton("START");
        JButton quitButton = new JButton("QUIT");

        titleLabel.setBounds(50, 25, 450, 100);
        startButton.setBounds(500, 250, 150, 75);
        quitButton.setBounds(500, 350, 150, 75);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(contentPane);
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        titlePanel.add(imageHolder);
        titlePanel.add(titleLabel);
        titlePanel.add(startButton);
        titlePanel.add(quitButton);

        return titlePanel;
    }

    private JPanel createDiffPanel() {
        JPanel diffPanel = new JPanel(new GridBagLayout());
        diffPanel.setLayout(null);
        diffPanel.setBackground(Color.BLACK);

        String text = "Unlike in a normal game of hangman" + "<br>" + "<br>" +
                      "The computer doesn't pick a word instead; " + "<br>" + "<br>" +
                      "It picks the largest set of words that fit the users guess." + "<br>" + "<br>" +
                      "The computer will will delay picking a word until it's forced to." + "<br>" + "<br>" +
                      "Guess the correct word before the stick figure is hung";


        JLabel instLabel = new JLabel("<html><div style='text-align: center;'>" + text + "</div></html>");

        instLabel.setBackground(new Color(55, 10, 5));
        instLabel.setOpaque(true);
        instLabel.setBorder(BorderFactory.createLineBorder(new Color(35, 0 ,0), 10));
        instLabel.setForeground(Color.WHITE);

        //add image icon of ! mark //maybe make corners round?
        instLabel.setBounds(187, 100, 425, 200);

        JPanel diffOptions = new JPanel(new GridLayout(1, 1, 25,0));
        diffOptions.setOpaque(false);
        diffOptions.setBounds(200, 350, 400, 75);

        easyButton = new JButton("<html> <font color='green'>EASY</font>");
        easyButton.addActionListener(this);
        mediumButton = new JButton("<html> <font color='orange'>NORMAL</font>");
        mediumButton.addActionListener(this);
        hardButton = new JButton("<html> <font color='red'>HARD</font>");
        hardButton.addActionListener(this);

        JLabel expLabel = new JLabel("Difficulty: changes chosen words length");
        expLabel.setBounds(315, 500, 600, 50);
        expLabel.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        expLabel.setForeground(Color.DARK_GRAY);

        diffOptions.add(easyButton);
        diffOptions.add(mediumButton);
        diffOptions.add(hardButton);
        
        diffPanel.add(expLabel);
        diffPanel.add(diffOptions);
        diffPanel.add(instLabel);

        return diffPanel;
    }

    private JPanel createGamePanel() {

        gamePanel = new JPanel();
        gamePanel.setLayout(null);
        gamePanel.setBackground(Color.BLACK);

        JLabel title = new JLabel("<html> <font color='red'>Evil</font>"
            + "<font color='white'>Hangman</font></html>");

        title.setFont(new Font("Times New Roman", Font.BOLD, 60));
        title.setBounds(10, 0, 360, 70);

        stringPattern = new JLabel();
        stringPattern.setFont(new Font("Times New Roman", Font.PLAIN,  50));
        stringPattern.setBounds(37, 375, 500, 50);
        stringPattern.setForeground(Color.RED);

        JTextField textField = new JTextField();
        textField.setFont(new Font("Times New Roman", Font.PLAIN,  20));
        textField.setBounds(35, 475, 200, 35);

        JLabel guessedLetters = new JLabel("Guessed Letters: []");
        guessedLetters.setFont(new Font("Times New Roman", Font.PLAIN,  10));
        guessedLetters.setBounds(35, 450, 600, 35);
        guessedLetters.setForeground(Color.GRAY);

        JButton cheatButton = new JButton("CHEAT");
        cheatButton.setBounds(650, 500, 100, 50);

        cheatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog answerList = new JDialog();
                answerList.setTitle("Possible Answers");
                answerList.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                answerList.setSize(200, 500);
                answerList.setResizable(false);
                answerList.getContentPane().setBackground(Color.BLACK);

                DefaultListModel<String> listModel = new DefaultListModel<>();

                for (String s: manager.getDictionary()) listModel.addElement(s);

                JList<String> list = new JList<>(listModel);
                JScrollPane scrollPane = new JScrollPane(list);
                answerList.add(scrollPane);

                answerList.setVisible(true);
            }
        });

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                char guess = textField.getText().charAt(0);

                manager.update(guess);

                guessedLetters.setText("Guessed Letters: " + manager.getGuessedLetters());
                stringPattern.setText(manager.getPattern());

                if(manager.getGuessesLeft() == 0 || !(stringPattern.getText().contains("-"))) {

                    String condition = " ";
                    int r = 0;
                    int g = 0;
                    int b = 0;

                    if (manager.getGuessesLeft() == 0) {
                        condition = "You Lost!";
                        r = 255; g = 65; b = 0;
                    } else if (!(stringPattern.getText().contains("-"))) {
                        condition = "You Won!";
                        r = 0; g = 255; b = 95;
                    }

                    JPanel endGamePanel = new JPanel();
                    endGamePanel.setOpaque(false); //sajdksajd
                    endGamePanel.setLayout(new BoxLayout(endGamePanel, BoxLayout.Y_AXIS));
                    endGamePanel.setBounds(200, 200,300, 150);
                    JLabel gameOver = new JLabel(condition);
                    gameOver.setForeground(new Color(r, g, b));
                    gameOver.setFont(new Font("Times New Roman", Font.BOLD, 60));
                    JButton replayButton = new JButton("Play Again");
                    replayButton.setPreferredSize(new Dimension(150, 75));
                    JButton quitGameButton = new JButton("Quit");
                    quitGameButton.setPreferredSize(new Dimension(150, 75));
            
                    endGamePanel.add(gameOver);
                    endGamePanel.add(replayButton);
                    endGamePanel.add(quitGameButton);
                    gamePanel.add(endGamePanel);

                    textField.setEditable(false);
                    replayButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            guessedLetters.setText("Guessed Letters: []");
                            cardLayout.show(contentPane, "Difficulty");
                            gamePanel.remove(endGamePanel);
                            manager.replayGame();
                            textField.setEditable(true);
                        }
                    });

                    quitGameButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.exit(0);
                        }
                    });
                }

                textField.setText("");
            }
        });

        gamePanel.add(cheatButton);
        gamePanel.add(stringPattern);
        gamePanel.add(guessedLetters);
        gamePanel.add(textField);
        gamePanel.add(title);

        return gamePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int num = 0;

        if (e.getSource() == easyButton) {

            num = (int) ((Math.random() * (5 - 2)) + 2);

        } else if (e.getSource() == mediumButton) {

            num = (int) ((Math.random() * (10 - 5)) + 5);

        } else if (e.getSource() == hardButton) {

            num = (int) ((Math.random() * (15 - 10)) + 10);

        }

        manager = new HangmanManager(Main.getDictionary(num), num);

        hangmanDrawing = manager.getDrawing();

        hangmanDrawing.setLocation(450, 30);
        gamePanel.add(hangmanDrawing);

        stringPattern.setText(manager.getPattern());
        cardLayout.next(contentPane);
    }
}