import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

class Board{

	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private final int SIZE=30;
	private JLabel[][] boardSquares = new JLabel[SIZE][SIZE];
	private JPanel board;

	Board() {
		initializeGui();
	}

	public final void initializeGui() {
		board = new JPanel(new GridLayout(0, SIZE));
        setToolbar();
        createEmptySquares();
        fillBoard();
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        gui.add(board);
    }

    private void createEmptySquares() {
        for (int ii = 0; ii < boardSquares.length; ii++) {
            for (int jj = 0; jj < boardSquares[ii].length; jj++) {
                JLabel b = new JLabel();
                b.setOpaque(true);
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                b.setBorder(new LineBorder(Color.BLACK));
                boardSquares[jj][ii] = b;
            }
        }
    }
    private void squareWithAnt(JLabel square){
	    ImageIcon icon =new ImageIcon(ClassLoader.getSystemResource( "icons/Ant.png" ));
	    square.setIcon(icon);
    }

    private void squareWithAntQueen(JLabel square){
        ImageIcon icon =new ImageIcon(ClassLoader.getSystemResource( "icons/AntQueen.png" ));
        square.setIcon(icon);
    }

    private void squareWithLeaf(JLabel square){
        ImageIcon icon =new ImageIcon(ClassLoader.getSystemResource( "icons/Leaf.png" ));
        square.setIcon(icon);
    }

    private void fillBoard() {
        for (int ii = 0; ii < SIZE; ii++) {
            for (int jj = 0; jj < SIZE; jj++) {
                 board.add(boardSquares[jj][ii]);
            }
        }
    }

    private void setToolbar() {
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        //TODO pozmieniaj te przyciski
        tools.add(new JButton("New")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(new JButton("Save")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(new JButton("Restore")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(new JButton("Resign")); // TODO - add functionality!
        tools.addSeparator();
    }

    public final JComponent getBoard() {
		return board;
	}

	public final JComponent getGui() {
		return gui;
	}

}