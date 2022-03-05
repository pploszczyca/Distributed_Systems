from ClientHandler import ClientHandler
from TcpSocket import TcpSocket


class TcpClientHandler(ClientHandler):
    def __init__(self, server_address, username) -> None:
        super().__init__()
        self.tcp_server_socket = TcpSocket(server_address, False)
        self.username = username

    def start(self):
        super().start()
        self.sendMessage(self.username)

    def receiveMessages(self):
        while True:
            isReceived, message = self.tcp_server_socket.receiveMessage()
            if isReceived:
                print(message)

    def sendMessage(self, message):
        self.tcp_server_socket.sendMessage(message)