//Self Note : Change path accordingly

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Bright extends JFrame{
       //globel variables
        BufferedImage bufferedImage;
        String path="E:\\imageToImageSearch\\b.jpg";
        
        //To change brightness
        float offset = 0.0f;
        //To change contrast default: 1.0f
        float scaleFactor = 1.0f;
        
        RescaleOp rescale;
        ImageIcon icon;
        JLabel picLabel=new JLabel();

     public Bright() {

         JFrame jf=new JFrame();
         JPanel jp=new JPanel();        

         jf.add(jp);
         jp.add(picLabel);

            jf.setVisible(true);
            jf.setSize(550, 550);
            jf.setLocation(200,100);
            
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            File file = new File(path);

        try {           
            bufferedImage = ImageIO.read(file); // create a buffered image
        } catch (IOException ex) {
            Logger.getLogger(Bright.class.getName()).log(Level.SEVERE, null, ex);
        }

         brighten();   
         icon = new ImageIcon(bufferedImage);          
         picLabel.setIcon(icon);       

    }

     private void brighten(){

         rescale();
         icon = new ImageIcon(bufferedImage);          
         picLabel.setIcon(icon);
         System.out.println("offset : "+offset  );

    }

     public void rescale() {

        rescale = new RescaleOp(scaleFactor,offset, null);
        bufferedImage=rescale.filter(bufferedImage,null);

       }

}
    
