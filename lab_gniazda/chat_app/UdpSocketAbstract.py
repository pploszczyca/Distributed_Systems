from abc import ABC, abstractmethod
from Decoding import decodeMessage


class UdpSocketAbstract(ABC):
    def __init__(self, socket_address, is_server_socket: bool) -> None:
        self.socket_address = socket_address
        self._socket_udp = None
        self.initSocket(is_server_socket)

    @abstractmethod
    def initSocket(self, is_server_socket: bool):
        pass

    def sendMessage(self, message, address):
        self._socket_udp.sendto(decodeMessage(message), address)

    @abstractmethod
    def receiveMessage(self):
        pass
