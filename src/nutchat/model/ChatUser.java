package nutchat.model;

import java.net.InetAddress;

public class ChatUser implements IUser
{
    private String username;
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
    public void setUserName(String newUserName)
    {
        this.username = newUserName;
    }

    @Override
    public InetAddress getAddress()
    {
        return address;
    }

    @Override
    public String toString()
    {
        return String.format("%s (%s)", username, address.getHostAddress());
    }
}
