from ClientHandler import ClientHandler
from MessagesUtilis import makeUserMessage
from MulticastSocket import MulticastSocket


class MulticastClientHandler(ClientHandler):
    def __init__(self, server_address, username) -> None:
        super().__init__()
        self.multicast_socket = MulticastSocket(server_address)
        self.server_address = server_address
        self.username = username

    def receiveMessages(self):
        while True:
            print(self.multicast_socket.receiveMessage())


    def sendMessage(self, message):
        self.multicast_socket.sendMessage(makeUserMessage(self.username, message), self.server_address)