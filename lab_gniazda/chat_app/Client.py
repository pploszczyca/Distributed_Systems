import socket
import threading

from Constants import ASCII_ART, ASCII_ART_MULTICAST, CLOSE_MESSAGE, MULTICAST_ADDRESS, MULTICAST_IP, MULTICAST_MESSAGE_TYPE, MULTICAST_TTL, SERVER_ADDRESS, UDP_MESSAGE_TYPE
from MulticastSocket import MulticastSocket
from TcpSocket import TcpSocket
from UdpSocket import UdpSocket


def receiveData():
    while True:
        isReceived, message = tcp_server_socket.receiveMessage()
        if isReceived:
            print(message)

def receiveUdpData():
    while True:
        data, address = server_udp_socket.receiveMessage()
        print(data)

def receiveMulticastData():
    while True:
        print(multicast_socket.receiveMessage())


tcp_server_socket = TcpSocket(SERVER_ADDRESS, False)
server_udp_socket = UdpSocket(SERVER_ADDRESS, False)
multicast_socket = MulticastSocket(MULTICAST_ADDRESS)

username = input("Enter username: ")
tcp_server_socket.sendMessage(username)
server_udp_socket.sendMessage(username, SERVER_ADDRESS)

receiveMessageThread = threading.Thread(target=receiveData)
receiveUdpMessageThread = threading.Thread(target=receiveUdpData)
receiveMulticastThread = threading.Thread(target=receiveMulticastData)

receiveMessageThread.start()
receiveUdpMessageThread.start()
receiveMulticastThread.start()

while True:
    input_data = input("")

    if input_data == UDP_MESSAGE_TYPE:
        server_udp_socket.sendMessage(ASCII_ART, SERVER_ADDRESS)
    elif input_data == MULTICAST_MESSAGE_TYPE:
        multicast_socket.sendMessage( "{}:{}".format(username, ASCII_ART_MULTICAST), MULTICAST_ADDRESS)
    else:
        tcp_server_socket.sendMessage(input_data)
        if input_data == CLOSE_MESSAGE:
            break

receiveMessageThread.join()
receiveUdpMessageThread.join()
receiveMulticastThread.join()
