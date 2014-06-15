package nutchat.model;

public class ChatMessage implements IMessage
{
    private final MessageType type;
    private final String contents;
    private final IUser sender;
    private final IUser recipient;

    public ChatMessage(MessageType type, String contents, IUser sender, IUser recipient)
    {
        this.type = type;
        this.contents = contents;
        this.sender = sender;
        this.recipient = recipient;
    }

    @Override
    public MessageType getType()
    {
        return type;
    }

    @Override
    public String getText()
    {
        return contents;
    }

    @Override
    public IUser getSender()
    {
        return sender;
    }

    @Override
    public IUser getRecipient()
    {
        return recipient;
    }

}
