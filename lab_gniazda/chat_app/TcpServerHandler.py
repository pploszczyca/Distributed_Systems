import socket
import threading
from Constants import CLOSE_MESSAGE
from MessagesUtilis import decodeMessageWithFrame, makeUserMessage

from TcpSocket import TcpSocket


class TcpServerHandler:
    def __init__(self, server_address) -> None:
        self.__user_connections = {}
        self.__user_connections_lock = threading.Lock()
        self.__tcp_socket = TcpSocket(server_address, True)

    def start(self):
        threading.Thread(target=self.__startReceivingNewConnections, daemon=True).start()

    def __startReceivingNewConnections(self):
        while True:
            connection, new_username = self.__tcp_socket.acceptNewConnection()

            self.__user_connections_lock.acquire()
            self.__user_connections[new_username] = connection
            self.__user_connections_lock.release()

            threading.Thread(target=self.__handleConnection, args=(connection,new_username)).start()

    def __handleConnection(self, connection: socket, username: str):
        while True:
            isReceived, message = self.__tcp_socket.receiveMessageFromOtherConnection(connection)
            if isReceived:
                if message == CLOSE_MESSAGE:
                    break
                else:
                    self.__sendMessageToOtherUsers(username, message)

        self.__closeConnection(connection, username)

    def __sendMessageToOtherUsers(self, username: str, message: str):
        self.__user_connections_lock.acquire()

        for user, userConnection in self.__user_connections.items():
            if user != username:
                userConnection.send(decodeMessageWithFrame(makeUserMessage(username, message)))

        self.__user_connections_lock.release()

    def __closeConnection(self, connection: socket, username: str):
        self.__user_connections_lock.acquire()
        self.__user_connections.pop(username)
        self.__user_connections_lock.release()
        connection.close()

    def close(self):
        self.__tcp_socket.close()
