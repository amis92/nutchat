package nutchat.app;

import java.net.UnknownHostException;

import nutchat.controller.TestController;
import nutchat.view.ConsoleChatView;

public class ConsoleTest
{

    public static void main(String[] args) throws IllegalStateException, UnknownHostException
    {
        new ConsoleChatView(new TestController());
    }

}
