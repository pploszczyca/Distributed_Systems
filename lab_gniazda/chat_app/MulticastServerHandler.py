from threading import Thread
from MulticastSocket import MulticastSocket


class MulticastServerHandler:
    def __init__(self, server_address) -> None:
        self.multicast_socket = MulticastSocket(server_address)

    def start(self):
        Thread(target=self.__handleMulticastMessages, daemon=True).start()

    def __handleMulticastMessages(self):
        while True:
            print(self.multicast_socket.receiveMessage())
