from ClientHandler import ClientHandler
from Constants import ASCII_ART
from UdpSocket import UdpSocket


class UdpClientHandler(ClientHandler):
    def __init__(self, server_address, username) -> None:
        super().__init__()
        self.udp_server_socket = UdpSocket(server_address, False)
        self.server_address = server_address
        self.username = username

    def start(self):
        super().start()
        self.sendMessage(self.username)

    def receiveMessages(self):
        while True:
            data, address = self.udp_server_socket.receiveMessage()
            print(data)

    def sendMessage(self, message):
        self.udp_server_socket.sendMessage(message, self.server_address)