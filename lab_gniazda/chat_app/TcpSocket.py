import socket

from Constants import HEADER_SIZE
from MessagesUtilis import decodeMessageWithFrame, encodeMessageToInt, encodeMessageToString


class TcpSocket:
    def __init__(self, server_address, is_server_socket: bool) -> None:
        self.initSocket(server_address, is_server_socket)

    def initSocket(self, server_address, is_serwer_socket: bool):
        self.__socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        
        if is_serwer_socket:
            self.__socket.bind(server_address)
            self.__socket.listen(True)
        else:
            self.__socket.connect(server_address)

    def acceptNewConnection(self):
        connection, address = self.__socket.accept()
        is_received, new_username = self.receiveMessageFromOtherConnection(connection)

        return connection, new_username

    def receiveMessage(self):
        return self.receiveMessageFromOtherConnection(self.__socket)

    def receiveMessageFromOtherConnection(self, connection: socket):
        message_lenght = connection.recv(HEADER_SIZE)
        if message_lenght:
            data = connection.recv(encodeMessageToInt(message_lenght))
            return True, encodeMessageToString(data)
        
        return False, ""

    def sendMessage(self, message: str):
        self.sendMessageToOtherSocket(self.__socket, message)


    def sendMessageToOtherSocket(self, connection: socket, message: str):
        connection.send(decodeMessageWithFrame(message))

    def close(self):
        self.__socket.close()


