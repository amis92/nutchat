package nutchat.view;

import java.util.List;

import javax.swing.JFrame;

import nutchat.controller.IChatController;
import nutchat.model.IMessage;
import nutchat.model.IUser;
import nutchat.view.swinggui.ChatFrame;

public class ChatSwingView implements IChatView
{
    private final IChatController controller;
    private final ChatFrame frame;

    public ChatSwingView(IChatController controller)
    {
        this.controller = controller;
        this.controller.setView(this);

        // GUI setup
        frame = new ChatFrame(controller);
        frame.setBounds(100, 100, 640, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        controller.getCurrentUser();
        controller.getUsers();
    }

    @Override
    public void showChatWith(IUser user, List<IMessage> chat)
    {
        frame.openChatWith(user, chat);
    }

    @Override
    public void showNewMessage(IMessage message)
    {
        frame.showNewMessage(message);
    }

    @Override
    public void showCurrentUser(IUser user)
    {
        frame.refreshCurrentInfo(user);
    }

    @Override
    public void showContacts(List<IUser> users)
    {
        frame.refreshContactList(users);
    }

}
