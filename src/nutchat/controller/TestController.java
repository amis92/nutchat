package nutchat.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nutchat.model.ChatUser;
import nutchat.model.IMessage;
import nutchat.model.IUser;
import nutchat.view.IChatView;

public class TestController implements IChatController
{
    private IChatView view;
    
    private List<IUser> contactList;
    private IUser self;
    private Map<IUser, List<IMessage>> chatHistory;
    
    public TestController() throws UnknownHostException
    {
        contactList = new ArrayList<>();
        contactList.add(new ChatUser("Maurycy", InetAddress.getByName("192.168.1.125")));
        contactList.add(new ChatUser("Router", InetAddress.getByName("192.168.1.101")));
        self = new ChatUser("Tester", InetAddress.getLocalHost());
        chatHistory = new HashMap<>();
    }

    @Override
    public void openChatWith(IUser user)
    {
        if (!chatHistory.containsKey(user))
        {
            chatHistory.put(user, new ArrayList<IMessage>());
        }
        view.showChat(chatHistory.get(user));
    }

    @Override
    public void sendMessage(IMessage message)
    {
        chatHistory.get(message.getRecipient()).add(message);
    }

    @Override
    public void setCurrentUserName(String newUserName)
    {
        this.self = new ChatUser(newUserName, self.getAddress());
    }

    @Override
    public void getCurrentUser()
    {
        view.showCurrentUser(self);
    }

    @Override
    public void getUsers()
    {
        view.showContacts(contactList);
    }

    @Override
    public void addUser(IUser user)
    {
        contactList.add(user);
    }

    @Override
    public void removeUser(IUser user)
    {
        contactList.remove(user);
    }

    @Override
    public void setView(IChatView view) throws NullPointerException
    {
        this.view = view;
    }

}
