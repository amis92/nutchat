package nutchat.model;

import java.awt.EventQueue;

import nutchat.controller.IChatController;

/**
 * Takes care of dispatching {@link IMessage} to appropriate
 * {@link IChatController} with earlier set {@link IUser} sender.
 * 
 * @author Amadeusz Sadowski 2014
 * 
 */
public class MessageDispatcher
{
    private final IChatController controller;
    private IUser sender;

    /**
     * Sets up this dispatcher to send messages to controller. All messages are
     * sent with set sender.
     * 
     * @param sender
     *            - the sender of all messages.
     * @param controller
     *            - controller to which messages will be sent.
     */
    public MessageDispatcher(IUser sender, IChatController controller)
    {
        this.controller = controller;
        this.sender = sender;
    }

    /**
     * Sends text message addressed to recipient. If this thread is EDT,
     * EventQueue.invokeLater is used.
     * 
     * @param recipient
     *            - recipient of this message.
     * @param message
     *            - content of the message.
     */
    public void sendMessage(IUser recipient, String message)
    {
        IMessage iMessage = new ChatMessage(MessageType.TEXT, message, sender, recipient);
        if (EventQueue.isDispatchThread())
        {
            EventQueue.invokeLater(new MessageDispatchThread(iMessage));
        }
        else
        {
            controller.sendMessage(iMessage);
        }
    }

    /**
     * Sends message to controller. Convenience class to use with
     * EventQueue.invokeLater.
     * 
     * @author Amadeusz Sadowski 2014
     * 
     */
    private class MessageDispatchThread implements Runnable
    {
        private final IMessage message;

        public MessageDispatchThread(IMessage message)
        {
            this.message = message;
        }

        @Override
        public void run()
        {
            controller.sendMessage(message);
        }

    }
    
    public void setSender(IUser sender)
    {
        this.sender = sender;
    }
}
