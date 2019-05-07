import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Ranzu
{

    public static void main(String[] args) {

        int size = 19;
        if (args.length > 0)
            size = Integer.parseInt(args[0]);

        JFrame frame = new JFrame();

        final int FRAME_WIDTH = 600;
        final int FRAME_HEIGHT = 650;
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("Ranzu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        RanzuPanel panel = new RanzuPanel(size);
        frame.add(panel);

        frame.setVisible(true);
    }
}

class RanzuPanel extends JPanel
{
    private final int MARGIN = 5;
    private final double PIECE_FRAC = 0.9;

    private int size = 19;
    private RanzuState state;

    public RanzuPanel()
    {
        this(19);
    }

    public RanzuPanel(int size)
    {
        super();
        this.size = size;
        state = new RanzuState(size);
        addMouseListener(new RanzuListener());
    }

    class RanzuListener extends MouseAdapter
    {
        public void mouseReleased(MouseEvent e)
        {
            double panelWidth = getWidth();
            double panelHeight = getHeight();
            double boardWidth = Math.min(panelWidth, panelHeight) - 2 * MARGIN;
            double squareWidth = boardWidth / size;
            double pieceDiameter = PIECE_FRAC * squareWidth;
            double xLeft = (panelWidth - boardWidth) / 2 + MARGIN;
            double yTop = (panelHeight - boardWidth) / 2 + MARGIN;
            int col = (int) Math.round((e.getX() - xLeft) / squareWidth - 0.5);
            int row = (int) Math.round((e.getY() - yTop) / squareWidth - 0.5);
            if (row >= 0 && row < size && col >= 0 && col < size
                    && state.getPiece(row, col) == RanzuState.NONE
                    && state.getWinner() == RanzuState.NONE) {
                state.playPiece(row, col);
                repaint();
                int winner = state.getWinner();
                if (winner != RanzuState.NONE)
                    JOptionPane.showMessageDialog(null,
                            (winner == RanzuState.BLACK) ? "Black wins!"
                                    : "White wins!");
            }
        }
    }


    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        double panelWidth = getWidth();
        double panelHeight = getHeight();

        g2.setColor(new Color(0.925f, 0.670f, 0.34f)); // light wood
        g2.fill(new Rectangle2D.Double(0, 0, panelWidth, panelHeight));


        double boardWidth = Math.min(panelWidth, panelHeight) - 2 * MARGIN;
        double squareWidth = boardWidth / size;
        double gridWidth = (size - 1) * squareWidth;
        double pieceDiameter = PIECE_FRAC * squareWidth;
        boardWidth -= pieceDiameter;
        double xLeft = (panelWidth - boardWidth) / 2 + MARGIN;
        double yTop = (panelHeight - boardWidth) / 2 + MARGIN;

        g2.setColor(Color.BLACK);
        for (int i = 0; i < size; i++) {
            double offset = i * squareWidth;
            g2.draw(new Line2D.Double(xLeft, yTop + offset,
                    xLeft + gridWidth, yTop + offset));
            g2.draw(new Line2D.Double(xLeft + offset, yTop,
                    xLeft + offset, yTop + gridWidth));
        }

        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++) {
                int piece = state.getPiece(row, col);
                if (piece != RanzuState.NONE) {
                    Color c = (piece == RanzuState.BLACK) ? Color.BLACK : Color.WHITE;
                    g2.setColor(c);
                    double xCenter = xLeft + col * squareWidth;
                    double yCenter = yTop + row * squareWidth;
                    Ellipse2D.Double circle
                            = new Ellipse2D.Double(xCenter - pieceDiameter / 2,
                            yCenter - pieceDiameter / 2,
                            pieceDiameter,
                            pieceDiameter);
                    g2.fill(circle);
                    g2.setColor(Color.black);
                    g2.draw(circle);
                }
            }
    }
}