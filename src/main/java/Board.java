import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

class Board {
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JLabel[][] boardSquares = new JLabel[Max.SIZE][Max.SIZE];
    private JPanel board;
    public  ObjectSquare[][] objects = new ObjectSquare[Max.SIZE][Max.SIZE];
    private JToolBar tools;
    private JButton buttonAddAnt;
    private ObjectSquare emptyObject=new ObjectSquare();

    Board() {
        for (int i = 0; i < boardSquares.length; i++)
            for (int j = 0; j < boardSquares[i].length; j++)
                objects[i][j]=new ObjectSquare();
        initializeGui();
    }

    private final void initializeGui() {
        board = new JPanel(new GridLayout(0, Max.SIZE));
        setToolbar();
        createSquares();
        fillBoard();
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));

    }

    private void createSquares() {
        for (int i = 0; i < boardSquares.length; i++) {
            for (int j = 0; j < boardSquares[i].length; j++) {
                JLabel b = new JLabel();
                b.setOpaque(true);
                squareWithImage(b,objects[i][j]);
                boardSquares[j][i] = b;
            }
        }
    }
    private void squareEmpty(JLabel b) {
        ImageIcon icon = new ImageIcon(
                new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB));
        b.setIcon(icon);
    }

    private void squareWithImage(JLabel square,ObjectSquare object){
        BufferedImage image = null;
        if(!object.getICON().equals(emptyObject.getICON())) {
            try {
                image = ImageIO.read(ClassLoader.getSystemResourceAsStream(object.getICON()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            image = rotateImage(image, object.getDegreesFacing());
            ImageIcon icon = new ImageIcon(image);
            image.flush();
            square.setIcon(icon);
        }else{

            squareEmpty(square);
        }

    }

    private BufferedImage rotateImage(BufferedImage image, double degrees) {
        if(degrees!=0){
            double radians=Math.toRadians(degrees);
            AffineTransform transform = new AffineTransform();
            transform.rotate(radians, image.getWidth()/2, image.getHeight()/2);
            AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
            image = op.filter(image, null);
        }
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
        buttonAddAnt =new JButton("Spawn Ant");
        tools.add(buttonAddAnt);

    }

     void showNewTurn() {
        for (int i = 0; i < boardSquares.length; i++) {
            for (int j = 0; j < boardSquares[i].length; j++) {
                JLabel b = new JLabel();
                b.setOpaque(true);
                squareWithImage(b,objects[i][j]);
                boardSquares[j][i] = b;
            }
        }
        fillBoard();
    }





    public final JComponent getGui() {
        return gui;
    }
    public final JComponent getTools() {
        return tools;
    }
    public final JButton getButtonAddAnt() {
        return buttonAddAnt;
    }

    public  void passObjects(ObjectSquare[] Objects) {
        for(ObjectSquare obj:Objects) {
            if (obj != null)
                objects[obj.getX()][obj.getY()] = obj;
            else
                break;
        }
        showNewTurn();
    }
}