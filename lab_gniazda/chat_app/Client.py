import socket
import threading

from Constants import CLOSE_MESSAGE, SERVER_IP, SERVER_PORT
from MessagesService import receiveMessage, sendMessage


server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.connect((SERVER_IP, SERVER_PORT))

def receiveData():
    while True:
        isReceived, message = receiveMessage(server_socket)
        if isReceived:
            print(message)

sendMessage(server_socket, input("Enter username: "))

receiveMessageThread = threading.Thread(target=receiveData)
receiveMessageThread.start()

while True:
    input_data = input("")
    sendMessage(server_socket, input_data)
    if input_data == CLOSE_MESSAGE:
        break

server_socket.close()
