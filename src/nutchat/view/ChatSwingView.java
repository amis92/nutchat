package nutchat.view;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;

import nutchat.controller.IChatController;
import nutchat.model.IMessage;
import nutchat.model.IUser;
import nutchat.view.swinggui.ChatFrame;

public class ChatSwingView implements IChatView
{
    private IChatController controller;

    public ChatSwingView(IChatController controller)
    {
        this.controller = controller;
        controller.setView(this);
        
        // GUI setup
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    JFrame frame = new ChatFrame();
                    frame.setBounds(100, 100, 450, 300);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void showChat(List<IMessage> chat)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void showNewMessage(IMessage message)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void showCurrentUser(IUser user)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void showContacts(List<IUser> users)
    {
        // TODO Auto-generated method stub

    }

}
