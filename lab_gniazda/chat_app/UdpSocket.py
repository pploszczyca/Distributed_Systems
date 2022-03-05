import socket
from Constants import BUFFER_SIZE
from MessagesUtilis import encodeMessageToString
from UdpSocketAbstract import UdpSocketAbstract


class UdpSocket(UdpSocketAbstract):
    def __init__(self, socket_address, is_server_socket: bool) -> None:
        super().__init__(socket_address, is_server_socket)
        
    def initSocket(self, is_server_socket: bool):
        self._socket_udp = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

        if is_server_socket:
            self._socket_udp.bind(self.socket_address)

    def receiveMessage(self):
        data, address = self._socket_udp.recvfrom(BUFFER_SIZE)
        return encodeMessageToString(data), address

