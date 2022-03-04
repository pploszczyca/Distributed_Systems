import socket
import struct
import threading

from Constants import ASCII_ART, ASCII_ART_MULTICAST, CLOSE_MESSAGE, MULTICAST_ADDRESS, MULTICAST_IP, MULTICAST_MESSAGE_TYPE, MULTICAST_TTL, SERVER_ADDRESS, UDP_MESSAGE_TYPE
from MessagesService import receiveMessage, receiveMessageUdp, receiveMulticastMessage, sendMessage, sendMessageUdp, sendMulticastMessage


server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.connect(SERVER_ADDRESS)

server_udp_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

multicast_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP)
multicast_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, MULTICAST_TTL)
multicast_socket.bind(MULTICAST_ADDRESS)

mreq = struct.pack("4sl", socket.inet_aton(MULTICAST_IP), socket.INADDR_ANY)
multicast_socket.setsockopt(socket.IPPROTO_IP, socket.IP_ADD_MEMBERSHIP, mreq)

def receiveData():
    while True:
        isReceived, message = receiveMessage(server_socket)
        if isReceived:
            print(message)

def receiveUdpData():
    while True:
        data, address = receiveMessageUdp(server_udp_socket)
        print(data)

def receiveMulticastData():
    while True:
        print(receiveMulticastMessage(multicast_socket))

username = input("Enter username: ")
sendMessage(server_socket, username)
sendMessageUdp(server_udp_socket, username, SERVER_ADDRESS)

receiveMessageThread = threading.Thread(target=receiveData)
receiveUdpMessageThread = threading.Thread(target=receiveUdpData)
receiveMulticastThread = threading.Thread(target=receiveMulticastData)

receiveMessageThread.start()
receiveUdpMessageThread.start()
receiveMulticastThread.start()

while True:
    input_data = input("")

    if input_data == UDP_MESSAGE_TYPE:
        sendMessageUdp(server_udp_socket, ASCII_ART, SERVER_ADDRESS)
    elif input_data == MULTICAST_MESSAGE_TYPE:
        sendMulticastMessage(server_udp_socket, "{}:{}".format(username, ASCII_ART_MULTICAST), MULTICAST_ADDRESS)
    else:
        sendMessage(server_socket, input_data)
        if input_data == CLOSE_MESSAGE:
            break

server_socket.close()

receiveMessageThread.join()
receiveUdpMessageThread.join()
receiveMulticastThread.join()
