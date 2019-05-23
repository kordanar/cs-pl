import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Packet.Box;
import Packet.Coor;
import Packet.Game;
import Packet.Ranges;

public class Ranzu extends JFrame {
    private Game game;
    private JPanel panel;
    private final int colls = 16;
    private final int rows = 16;
    private final int Image_Size = 140;
    public static void main(String[] args) {
        new Ranzu().setVisible(true);

    }

    private Ranzu(){
        game = new Game(colls,rows);
        game.start();
        setImages();
        initFrame();
        initPanel();

    }
    private void initPanel(){
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                 for(Coor coor:Ranges.getAllCoords()){
                    g.drawImage((Image) game.getBox(coor).image, coor.x*Image_Size , coor.y*Image_Size, this);
                }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX()/Image_Size;
                int y = e.getY()/Image_Size;
                Coor coor = new Coor(x , y);
                if (e.getButton()== MouseEvent.BUTTON1)
                {
                    return Game.Turn;
                }
                panel.repaint();

            }
        });
        panel.setPreferredSize(new Dimension(Ranges.getSize().x*Image_Size,Ranges.getSize().y*Image_Size));
        add (panel);
    }

    private void initFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ranzu (five in a row)");
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        setIconImage(getImage("ball"));
        pack();
    }
    private void setImages(){
        for (Box box: Box.values())
            box.image = getImage(box.name().toLowerCase());
    }
    private Image getImage(String name){
        String filename = "src/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(filename);
        return icon.getImage();
    }
}
