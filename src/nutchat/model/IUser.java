package nutchat.model;

import java.net.InetAddress;

/**
 * Defines methods for accessing and working with User representations in chat.
 * 
 * @author Amadeusz Sadowski 2014
 * 
 */
public interface IUser
{
    public String getUserName();

    public InetAddress getAddress();
}
