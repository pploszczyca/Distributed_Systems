from socket import socket
from Constants import BUFFER_SIZE, HEADER_SIZE
from Decoding import decodeMessage, decodeMessageWithFrame, encodeMessageToInt, encodeMessageToString


def sendMessage(connection: socket, message: str):
    connection.send(decodeMessageWithFrame(message))

def receiveMessage(connection: socket):
    message_lenght = connection.recv(HEADER_SIZE)
    if message_lenght:
        data = connection.recv(encodeMessageToInt(message_lenght))
        return True, encodeMessageToString(data)
    
    return False, ""

def sendMessageUdp(socket_udp: socket, message: str, address):
    socket_udp.sendto(decodeMessage(message), address)

def receiveMessageUdp(socket_udp: socket):
    data, address = socket_udp.recvfrom(BUFFER_SIZE)
    return encodeMessageToString(data), address

def sendMulticastMessage(multicast_socket: socket, message: str, address):
    sendMessageUdp(multicast_socket, message, address)

def receiveMulticastMessage(multicast_socket: socket):
    return encodeMessageToString(multicast_socket.recv(BUFFER_SIZE))