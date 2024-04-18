import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChatServerGUI extends JFrame {

    private JTextArea logTextArea;
    private Set<PrintWriter> clientWriters;
    private int port;
    private Map<String, String> userCredentials;

    public ChatServerGUI(int port) {
        this.port = port;
        setTitle("Chat Server GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logTextArea);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);

        userCredentials = new HashMap<>();
        userCredentials.put("user1", "password1"); // Add sample credentials

        clientWriters = new HashSet<>();

        startServer();
    }

    private void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            appendToLog("Chat server is running on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                appendToLog("New client connected: " + clientSocket);
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                String username = reader.readLine();
                String password = reader.readLine();
                if (authenticate(username, password)) {
                    appendToLog("Authentication successful for: " + username);
                    clientWriters.add(writer);
                    writer.println("Authentication successful. You are now connected to the chat server.");
                    new ClientHandler(clientSocket, username).start();
                } else {
                    appendToLog("Authentication failed for: " + username);
                    writer.println("Authentication failed. Please check your username and password.");
                    clientSocket.close();
                }
            }
        } catch (IOException e) {
            appendToLog("Error in the server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean authenticate(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }

    private void appendToLog(String message) {
        logTextArea.append(message + "\n");
    }

    private class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter writer;
        private String username;

        public ClientHandler(Socket socket, String username) {
            this.clientSocket = socket;
            this.username = username;
        }

        public void run() {
            try {
                writer = new PrintWriter(clientSocket.getOutputStream(), true);
                while (true) {
                    // For simplicity, this server echoes messages back to the client
                    writer.println("Message received by server from " + username + ": " + clientSocket);
                }
            } catch (IOException e) {
                appendToLog("Error handling client: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        int port = 8080; // Default port
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        new ChatServerGUI(port);
    }
}
