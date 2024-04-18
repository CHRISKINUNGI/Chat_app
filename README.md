# Java Chat Application with Authentication

This is a simple chat application implemented in Java, consisting of a server and a client component. The server allows multiple clients to connect simultaneously and exchange messages in a chatroom-like environment. Authentication is implemented to ensure secure access to the chat server.

## Features

- **Server**: The server component allows clients to connect over a network and exchange messages. It supports multiple clients simultaneously.
- **Client**: The client component connects to the server and provides a user interface for sending and receiving messages.
- **Authentication**: Users are required to provide a username and password to access the chat server. Only authenticated users can join the chat.

## Usage

### Server

1. Compile and run the `ChatServerGUI.java` file. By default, the server listens on port `8080`.
2. Once the server is running, it will display a log of incoming connections and messages exchanged.

### Client

1. Compile and run the `ChatClientGUI.java` file.
2. Provide the server address, port, username, and password when prompted.
3. Once authenticated, you can send and receive messages in the chat.

## Requirements

- Java Development Kit (JDK) version 8 or higher

## Configuration

- You can modify the server address, port, username, and password in the `ChatClientGUI.java` file to fit your requirements.

## Note

- This application provides a basic chat functionality with authentication. For production use, consider implementing more robust security measures and error handling.

