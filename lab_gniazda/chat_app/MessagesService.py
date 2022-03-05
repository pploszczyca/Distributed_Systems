from socket import socket
from Constants import BUFFER_SIZE, HEADER_SIZE
from Decoding import decodeMessageWithFrame, encodeMessageToInt, encodeMessageToString


def sendMessage(connection: socket, message: str):
    connection.send(decodeMessageWithFrame(message))

def receiveMessage(connection: socket):
    message_lenght = connection.recv(HEADER_SIZE)
    if message_lenght:
        data = connection.recv(encodeMessageToInt(message_lenght))
        return True, encodeMessageToString(data)
    
    return False, ""
