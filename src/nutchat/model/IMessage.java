package nutchat.model;

/**
 * Defines methods for message usage, properties accessors and so on.
 * 
 * @author Amadeusz Sadowski 2014
 * 
 */
public interface IMessage
{
    public MessageType getType();

    public String getText();
    
    public IUser getSender();
    
    public IUser getRecipient();

}
