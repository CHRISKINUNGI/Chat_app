import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientGUI extends JFrame {

    private JTextArea chatTextArea;
    private JTextField messageTextField;
    private JButton sendButton;
    private PrintWriter writer;
    private BufferedReader reader;

    @SuppressWarnings("resource")
    public ChatClientGUI(String serverAddress, int port, String username, String password) {
        setTitle("Chat Client GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        chatTextArea = new JTextArea();
        chatTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        messageTextField = new JTextField();
        bottomPanel.add(messageTextField, BorderLayout.CENTER);
        sendButton = new JButton("Send");
        bottomPanel.add(sendButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);

        try {
            Socket socket = new Socket(serverAddress, port);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer.println(username);
            writer.println(password);

            sendButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    sendMessage();
                }
            });

            new Thread(new Runnable() {
                public void run() {
                    try {
                        String message;
                        while ((message = reader.readLine()) != null) {
                            chatTextArea.append(message + "\n");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        String message = messageTextField.getText();
        if (!message.isEmpty()) {
            writer.println(message);
            messageTextField.setText("");
        }
    }

    public static void main(String[] args) {
        String serverAddress = "localhost"; // Change this to the server address
        int port = 8080; // Default port
        String username = "user1"; // Change this to your username
        String password = "password1"; // Change this to your password

        new ChatClientGUI(serverAddress, port, username, password);
    }
}
