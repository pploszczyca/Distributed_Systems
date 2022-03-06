from ClientHandler import ClientHandler
from TcpSocket import TcpSocket


class TcpClientHandler(ClientHandler):
    def __init__(self, server_address, username) -> None:
        super().__init__()
        self.__tcp_server_socket = TcpSocket(server_address, False)
        self.__username = username

    def start(self):
        super().start()
        self.sendMessage(self.__username)

    def receiveMessages(self):
        while True:
            isReceived, message = self.__tcp_server_socket.receiveMessage()
            if isReceived:
                print(message)

    def sendMessage(self, message):
        self.__tcp_server_socket.sendMessage(message)

    def close(self):
        self.__tcp_server_socket.close()