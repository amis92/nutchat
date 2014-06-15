package nutchat.view;

import java.io.Console;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import nutchat.controller.IChatController;
import nutchat.model.ChatMessage;
import nutchat.model.ChatUser;
import nutchat.model.IMessage;
import nutchat.model.IUser;
import nutchat.model.MessageType;

public class ConsoleChatView implements IChatView
{
    private final Console console;
    private final ConsoleThread uiThread;
    private final IChatController controller;
    private List<IUser> contactList;
    private IUser self;

    /**
     * Calls {@link IChatController#setView(IChatView)} providing <b>this</b> as
     * argument.
     * 
     * @param controller
     * @throws IllegalStateException
     */
    public ConsoleChatView(IChatController controller) throws IllegalStateException
    {
        this.console = System.console();
        if (console == null)
        {
            throw new IllegalStateException("# No console for ConsoleChatView to use!");
        }
        this.contactList = new ArrayList<>(0);
        this.controller = controller;
        uiThread = new ConsoleThread();
        uiThread.setDaemon(false);
        uiThread.start();
    }

    @Override
    public void showChat(List<IMessage> chat)
    {
        for (IMessage m : chat)
        {
            switch (m.getType())
            {
            case TEXT:
                uiThread.println(String.format("%s: %s", m.getSender().getUserName(), m.getText()));
            }
        }
    }

    @Override
    public void showNewMessage(IMessage message)
    {
        if (message.getType() == MessageType.TEXT)
        {
            uiThread.print(String.format(
                            "# You have new message from %s: %s...",
                            message.getSender().getUserName(),
                            message.getText().substring(
                                            0,
                                            message.getText().length() > 20 ? 20 : message
                                                            .getText().length())));
        }
    }

    @Override
    public void showCurrentUser(IUser user)
    {
        self = user;
        uiThread.print("# Your username: ");
        uiThread.println(self.getUserName());
        uiThread.print("# Your address: ");
        uiThread.println(self.getAddress().getHostAddress());
    }

    @Override
    public void showContacts(List<IUser> users)
    {
        uiThread.println("# Your contacts: ");
        int index = 0;
        for (IUser user : users)
        {
            uiThread.println(String.format("# %d. %s %s", index++, user.getUserName(), user
                            .getAddress().getHostAddress()));
        }
    }

    /**
     * Controls console UI and provides loop for reading console input.
     * 
     * @author Amadeusz Sadowski 2014
     * 
     */
    private class ConsoleThread extends Thread
    {
        private final Scanner scanner = new Scanner(console.reader());
        private static final String helpMessage = "[q]uit\n" + "[i]nfo (ip, username)\n"
                        + "[e]dit my username\n" + "[l]ist contacts\n" + "[a]dd contact\n"
                        + "[r]emove contact\n" + "[c]hat\n";

        @Override
        public void run()
        {
            boolean isExit = false;
            while (isExit == false)
            {
                switch (getNextCommand())
                {
                case EXIT:
                    isExit = true;
                    break;
                case ADD_USER:
                    addUser();
                    break;
                case REMOVE_USER:
                    removeUser();
                    break;
                case LIST_CONTACTS:
                    controller.getUsers();
                    break;
                case SELF_INFO:
                    controller.getCurrentUser();
                    break;
                case SELF_EDIT:
                    selfEdit();
                    break;
                case OPEN_CHAT:
                    openChat();
                    break;
                default:
                    showHelp();
                }
            }
            println("# Thanks for using NutChat :D\n See you next time!");
            scanner.close();
        }

        private void openChat()
        {
            println("# Enter index number of user you want to chat with (the number on contact list):");
            try
            {
                int index = scanner.nextInt();
                controller.openChatWith(contactList.get(index));
                beginChatLoop(contactList.get(index));
            }
            catch (InputMismatchException e)
            {
                println("# Please enter a number.");
            }
            catch (IndexOutOfBoundsException e)
            {
                println("# Number is out of list range.");
            }
        }

        private void beginChatLoop(IUser iUser)
        {
            boolean isExit = false;
            println(String.format("# Chatting with %s", iUser.getUserName()));
            while (!isExit)
            {
                // compose message
                println("# Enter message (to send press Ctrl+D on Linux or Ctrl+Z on Windows):");
                StringBuilder messageBuilder = new StringBuilder();
                String msgLine = console.readLine();
                while (msgLine != null)
                {
                    messageBuilder.append(msgLine);
                    msgLine = console.readLine();
                }
                controller.sendMessage(new ChatMessage(MessageType.TEXT, messageBuilder.toString(),
                                self, iUser));
                print("# To exit enter 'e', to continue press Enter: ");
                isExit = console.readLine().startsWith("e");
            }
        }

        private void selfEdit()
        {
            print("# New username: ");
            String newUserName = scanner.nextLine();
            controller.setCurrentUserName(newUserName);
        }

        private void removeUser()
        {
            print("# Username of user to remove: ");
            String userName = scanner.nextLine();
            for (IUser user : contactList)
            {
                if (user.getUserName() == userName)
                {
                    controller.removeUser(user);
                    return;
                }
            }
            println("# No such user on your list (maybe fetch the list first?).");
        }

        private void addUser()
        {
            print("Username: ");
            String username = scanner.nextLine();
            print("IP address: ");
            String ip = scanner.nextLine();
            try
            {
                InetAddress address = InetAddress.getByName(ip);
                IUser user = new ChatUser(username, address);
                controller.addUser(user);
            }
            catch (UnknownHostException e)
            {
                println("# The ip address is incorrect, or has incorrect format.");
            }
        }

        private void showHelp()
        {
            println("\nTry again:\n");
            print(helpMessage);
        }

        private void print(String message)
        {
            console.writer().print(message);
        }

        private void println(String message)
        {
            console.writer().println(message);
        }

        private ChatCommand getNextCommand()
        {
            print(">");
            String command = scanner.nextLine();
            if (command.length() != 1)
            {
                return ChatCommand.NO_COMMAND;
            }
            switch (command.charAt(0))
            {
            case 'q':
                return ChatCommand.EXIT;
            case 'i':
                return ChatCommand.SELF_INFO;
            case 'e':
                return ChatCommand.SELF_EDIT;
            case 'l':
                return ChatCommand.LIST_CONTACTS;
            case 'a':
                return ChatCommand.ADD_USER;
            case 'r':
                return ChatCommand.REMOVE_USER;
            case 'c':
                return ChatCommand.OPEN_CHAT;
            }
            return ChatCommand.NO_COMMAND;
        }

    }

    private enum ChatCommand
    {
        NO_COMMAND, EXIT, SELF_INFO, SELF_EDIT, OPEN_CHAT, LIST_CONTACTS, SEND_MESSAGE, ADD_USER, REMOVE_USER
    }
}
