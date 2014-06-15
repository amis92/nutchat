package nutchat.view;

import java.util.List;

import nutchat.model.IMessage;
import nutchat.model.IUser;

/**
 * Defines methods invoked on View representing p2p chat UI.
 * 
 * @author Amadeusz Sadowski 2014
 * 
 */
public interface IChatView
{
    /**
     * Informs user on new chat connection. It isn't guaranteed this chat will
     * now be active, in fact it probably won't. Instead, UI will inform user on
     * pending chat connection.
     * 
     * @param chat
     *            - the chat contents.
     */
    public void showChat(List<IMessage> chat);

    /**
     * UI will inform user on new pending message or not - if, for example,
     * message will be just delivery confirmation.
     * 
     * @param message
     */
    public void showNewMessage(IMessage message);

    /**
     * Updates UI's information on self.
     * 
     * @param user
     *            - self.
     */
    public void showCurrentUser(IUser user);

    /**
     * Updates UI's contact list.
     * 
     * @param users
     *            - current contact list.
     */
    public void showContacts(List<IUser> users);
}
