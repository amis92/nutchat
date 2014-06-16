package nutchat.app;

import java.awt.EventQueue;
import java.net.UnknownHostException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import nutchat.controller.TestController;
import nutchat.view.ChatSwingView;

public class SwingApp
{

    /**
     * Launch the application.
     * 
     * @throws UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                        | UnsupportedLookAndFeelException e1)
        {
            // We must cry 'cause we can't set system L&F. But we'll cry silently.
        }
        EventQueue.invokeLater(new Runnable()
            {
                public void run()
                {
                    try
                    {
                        new ChatSwingView(new TestController());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
    }
}
