import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

class Board {

    public static final String ICONS_ANT_PNG = "icons/Ant.png";
    public static final String ICONS_ANT_QUEEN_PNG = "icons/AntQueen.png";
    public static final String ICONS_LEAF_PNG = "icons/Leaf.png";
    public static final String ICONS_ANTHILL_PNG = "icons/Anthill.png";
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));

    private JLabel[][] boardSquares = new JLabel[Max.SIZE][Max.SIZE];
    static char[][] boardSquaresTags = new char[Max.SIZE][Max.SIZE];
    private JPanel board;
    public static char TYPE_EMPTY='E';
    JToolBar tools;
    JButton button;
    static Queen queen;
    static public void setQueen(Queen q) {
        queen = q;
    }



    static public void setAnts(Ant[] a) {
        ants = a;
    }

    static Ant ants[];
    Board() {
        initializeGui();
    }


    private final void initializeGui() {
        board = new JPanel(new GridLayout(0, Max.SIZE));
        setToolbar();
        createEmptySquares();
        fillBoard();
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));

    }

    private void createEmptySquares() {
        for (int i = 0; i < boardSquares.length; i++) {
            for (int j = 0; j < boardSquares[i].length; j++) {
                JLabel b = new JLabel();
                b.setOpaque(true);
                squareEmpty(b);

                boardSquares[j][i] = b;
                boardSquaresTags[j][i] = 'E';
            }
        }
    }
    private void squareEmpty(JLabel b) {
        ImageIcon icon = new ImageIcon(
                new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB));
        b.setIcon(icon);
    }

    private void squareWithImage(JLabel square,String imagePath,double degrees){
        BufferedImage image = null;
        try {
            image = ImageIO.read(ClassLoader.getSystemResourceAsStream(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        image = rotateImage(image, degrees);

        ImageIcon icon = new ImageIcon(image);
        square.setIcon(icon);
    }




    private BufferedImage rotateImage(BufferedImage image, double degrees) {
        double radians=Math.toRadians(degrees);
        AffineTransform transform = new AffineTransform();
        transform.rotate(radians, image.getWidth()/2, image.getHeight()/2);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        image = op.filter(image, null);
        return image;
    }


    private void fillBoard() {
        gui.remove(board);
        board.removeAll();

        for (int ii = 0; ii < Max.SIZE; ii++) {
            for (int jj = 0; jj < Max.SIZE; jj++) {
                board.add(boardSquares[jj][ii]);
            }
        }
        gui.add(board);
    }

    private void setToolbar() {
        tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        //TODO pozmieniaj te przyciski
        tools.add(new JButton("New")); // TODO - add functionality!
        tools.addSeparator();

        button =new JButton("Save");

        tools.add(button); // TODO - add functionality!
        tools.addSeparator();
        tools.add(new JButton("Restore")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(new JButton("Resign")); // TODO - add functionality!
        tools.addSeparator();
    }

     void showNewTurn() {


        for (int i = 0; i < boardSquares.length; i++) {
            for (int j = 0; j < boardSquares[i].length; j++) {
                JLabel b = new JLabel();
                b.setOpaque(true);

                if (boardSquaresTags[i][j] == Queen.TYPE) {
                    squareWithImage(b,ICONS_ANT_QUEEN_PNG,queen.degreesFacing);
                } else if (boardSquaresTags[i][j] == Ant.TYPE){

                    for(Ant ant : ants){
                        if(ant.x==i && ant.y==j){
                            double dgr=ant.degreesFacing;
                            squareWithImage(b,ICONS_ANT_PNG,dgr);
                            break;
                        }


                    }
                }
                else if (boardSquaresTags[i][j] == Anthill.TYPE){
                    squareWithImage(b,ICONS_ANTHILL_PNG,0);

                }
                else if (boardSquaresTags[i][j] == Leaf.TYPE){
                    squareWithImage(b,ICONS_LEAF_PNG,0);

                }
                else{
                    squareEmpty(b);
                }

                boardSquares[j][i] = b;


            }

        }
        fillBoard();

    }




    public final JComponent getBoard() {
        return board;
    }

    public final JComponent getGui() {
        return gui;
    }
    public final JComponent getTools() {
        return tools;
    }
    public final JComponent getButton() {
        return button;
    }

}