from threading import Thread
from MessagesUtilis import makeUserMessage
from UdpSocket import UdpSocket


class UdpServerHandler:
    def __init__(self, server_address) -> None:
        self.__address_udp_user = {}
        self.__udp_socket = UdpSocket(server_address, True)

    def start(self):
        Thread(target=self.__handleConnections, daemon=True).start()

    def __handleConnections(self):
        while True:
            data, address = self.__udp_socket.receiveMessage()
            if address not in self.__address_udp_user.keys():
                self.__address_udp_user[address] = data
            else:
                self.__sendMessageToOthers(address, data)

    def __sendMessageToOthers(self, address, message: str):
        sender_username = self.__address_udp_user[address]

        for user_address in self.__address_udp_user.keys():
            if user_address != address:
                self.__udp_socket.sendMessage(makeUserMessage(sender_username, message), user_address)
