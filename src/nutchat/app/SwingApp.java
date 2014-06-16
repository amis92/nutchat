package nutchat.app;

import java.awt.EventQueue;
import java.net.UnknownHostException;

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
