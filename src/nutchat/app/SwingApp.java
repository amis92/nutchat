package nutchat.app;

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
        new ChatSwingView(new TestController());
    }
}
