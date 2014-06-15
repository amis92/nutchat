package nutchat.model;

import java.net.InetAddress;

public class ChatUser implements IUser
{
    private final String username;
    private final InetAddress address;

    public ChatUser(String username, InetAddress address)
    {
        this.username = username;
        this.address = address;
    }

    @Override
    public String getUserName()
    {
        return username;
    }

    @Override
    public InetAddress getAddress()
    {
        return address;
    }

}
