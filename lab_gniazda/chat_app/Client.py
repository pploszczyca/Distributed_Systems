import socket
import threading

from Constants import ASCII_ART, ASCII_ART_MULTICAST, CLOSE_MESSAGE, MULTICAST_ADDRESS, MULTICAST_IP, MULTICAST_MESSAGE_TYPE, MULTICAST_TTL, SERVER_ADDRESS, UDP_MESSAGE_TYPE
from MessagesService import receiveMessage, sendMessage
from MulticastSocket import MulticastSocket
from UdpSocket import UdpSocket


server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.connect(SERVER_ADDRESS)

server_udp_socket = UdpSocket(SERVER_ADDRESS, False)
multicast_socket = MulticastSocket(MULTICAST_ADDRESS)

def receiveData():
    while True:
        isReceived, message = receiveMessage(server_socket)
        if isReceived:
            print(message)

def receiveUdpData():
    while True:
        data, address = server_udp_socket.receiveMessage()
        print(data)

def receiveMulticastData():
    while True:
        print(multicast_socket.receiveMessage())

username = input("Enter username: ")
sendMessage(server_socket, username)
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
        sendMessage(server_socket, input_data)
        if input_data == CLOSE_MESSAGE:
            break

server_socket.close()

receiveMessageThread.join()
receiveUdpMessageThread.join()
receiveMulticastThread.join()
