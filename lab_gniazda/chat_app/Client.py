import socket
import threading

from Constants import ASCII_ART, CLOSE_MESSAGE, SERVER_ADDRESS, UDP_MESSAGE
from MessagesService import receiveMessage, receiveMessageUdp, sendMessage, sendMessageUdp


server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.connect(SERVER_ADDRESS)

server_udp_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

def receiveData():
    while True:
        isReceived, message = receiveMessage(server_socket)
        if isReceived:
            print(message)

def receiveUdpData():
    while True:
        data, address = receiveMessageUdp(server_udp_socket)
        print(data)

username = input("Enter username: ")
sendMessage(server_socket, username)
sendMessageUdp(server_udp_socket, username, SERVER_ADDRESS)

receiveMessageThread = threading.Thread(target=receiveData)
receiveUdpMessageThread = threading.Thread(target=receiveUdpData)

receiveMessageThread.start()
receiveUdpMessageThread.start()

while True:
    input_data = input("")

    if input_data == UDP_MESSAGE:
        sendMessageUdp(server_udp_socket, ASCII_ART, SERVER_ADDRESS)
    else:
        sendMessage(server_socket, input_data)
        if input_data == CLOSE_MESSAGE:
            break

server_socket.close()
