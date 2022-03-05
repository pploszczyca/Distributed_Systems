import socket
import struct
from Constants import BUFFER_SIZE, MULTICAST_TTL
from Decoding import encodeMessageToString
from UdpSocketAbstract import UdpSocketAbstract


class MulticastSocket(UdpSocketAbstract):
    def __init__(self, socket_address) -> None:
        super().__init__(socket_address, True)

    def initSocket(self, is_server_socket: bool):
        self._socket_udp = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP)
        self._socket_udp.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, MULTICAST_TTL)
        self._socket_udp.bind(self.socket_address)

        mreq = struct.pack("4sl", socket.inet_aton(self.socket_address[0]), socket.INADDR_ANY)
        self._socket_udp.setsockopt(socket.IPPROTO_IP, socket.IP_ADD_MEMBERSHIP, mreq)

    def receiveMessage(self):
        data = self._socket_udp.recv(BUFFER_SIZE)
        return encodeMessageToString(data)