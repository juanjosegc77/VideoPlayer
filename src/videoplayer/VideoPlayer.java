package videoplayer;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Navegador web embebido en un JFrame.
 * Útil para ver videos en modo pantalla completa pero embebido en un JFrame
 * que puede ser ajustado al tamaño deseado.
 * @author Juan José Guzmán Cruz.
 */
public class VideoPlayer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NativeInterface.open();
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("VideoPlayer");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.getContentPane().add(getBrowserPanel(), BorderLayout.CENTER);
                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        NativeInterface.runEventPump();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                NativeInterface.close();
            }
        }));
    }
    
    public static JPanel getBrowserPanel() {
    
        JPanel webBrowserPanel = new JPanel(new BorderLayout());
        JWebBrowser webBrowser = new JWebBrowser();
        String url = "https://www.youtube.com";
        //String url = "http://duckduckgo.com";

        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        webBrowser.setBarsVisible(false); // activa o descativa la barra de navegación
        webBrowser.navigate(url);

        return webBrowserPanel;
    }
}
