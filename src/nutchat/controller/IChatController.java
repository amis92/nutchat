package nutchat.controller;

import nutchat.model.IMessage;
import nutchat.model.IUser;
import nutchat.view.IChatView;

/**
 * Defines methods to communicate user requests from UI.
 * 
 * {@link #setView(IChatView)} must be called before calling any other function.
 * 
 * @author Amadeusz Sadowski 2014
 * 
 */
public interface IChatController
{
    /**
     * Controller checks connection with user. If there is no connection yet,
     * controller will try to connect. This call will result in controller
     * calling {@link nutchat.view.IChatView#showChat(java.util.List)} on its
     * view.
     * 
     * @param user
     *            - the user to whom connection will be checked.
     * @throws NullPointerException
     *             when any argument is null.
     */
    public void openChatWith(IUser user) throws NullPointerException;

    /**
     * An attempt to send this message will be made. Result of this attempt will
     * be communicated to view by calling
     * {@link nutchat.view.IChatView#showNewMessage(IMessage)}.
     * 
     * @param message
     *            - the message to be sent.
     * @throws NullPointerException
     *             when any argument is null.
     */
    public void sendMessage(IMessage message) throws NullPointerException;

    /**
     * Changes the username of self to the new provided one.
     * 
     * @param newUserName
     *            - the new username of self.
     * @throws NullPointerException
     *             when any argument is null.
     */
    public void setCurrentUserName(String newUserName) throws NullPointerException;

    /**
     * Informs controller the view wants details of the user currently running
     * the chat, the self. It will result in a call of
     * {@link nutchat.view.IChatView#showCurrentUser(IUser)} with appropriate
     * argument.
     */
    public void getCurrentUser();

    /**
     * Informs controller the view wants the list of users saved in self's
     * contact list.
     * Results in call of
     * {@link nutchat.view.IChatView#showContacts(java.util.List)}.
     */
    public void getUsers();

    /**
     * Adds provided user to self's contact list. An attempt to save this list
     * to local properties file will be made.
     * 
     * @param user
     *            - the user to add to local contact list of self.
     * @throws NullPointerException
     *             when any argument is null.
     */
    public void addUser(IUser user) throws NullPointerException;

    /**
     * Removes provided user from self's contact list. An attempt to save this
     * list
     * to local properties file will be made.
     * 
     * @param user
     *            - the user to remove from local contact list of self.
     * @throws NullPointerException
     *             when any argument is null.
     */
    public void removeUser(IUser user) throws NullPointerException;

    /**
     * From now on the provided view is the view on which all functions called
     * on IChatView are called. This must be called before calling any other
     * functions.
     * It may be called again at any time.
     * 
     * @param view
     *            - the view to set.
     * @throws NullPointerException
     *             when any argument is null.
     */
    public void setView(IChatView view) throws NullPointerException;
}
