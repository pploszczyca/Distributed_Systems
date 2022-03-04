import socket
import threading
from Constants import CLOSE_MESSAGE, SERVER_IP, SERVER_PORT
from Decoding import decodeMessage
from MessagesService import receiveMessage


def makeUserMessage(username: str, message: str):
    return "{}: {}".format(username, message)

def sendMessageToOtherUsers(username: str, message: str):
    user_connections_lock.acquire()

    for user, userConnection in user_connections.items():
        if user != username:
            userConnection.send(decodeMessage(makeUserMessage(username, message)))

    user_connections_lock.release()

def closeConnection(connection: socket, username: str):
    user_connections.pop(username)
    connection.close()

def handleConnection(connection: socket, username: str):
    while True:
        isReceived, message = receiveMessage(connection)
        if isReceived:
            if message == CLOSE_MESSAGE:
                break
            else:
                sendMessageToOtherUsers(username, message)

    closeConnection(connection, username)

def acceptAndSaveNewConnection():
    connection, address = socket_tcp.accept()
    is_received, new_username = receiveMessage(connection)
    user_connections[new_username] = connection

    return connection, new_username

def handleUdpConnection():
    pass


user_connections = {}
user_connections_lock = threading.Lock()

socket_tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
socket_tcp.bind((SERVER_IP, SERVER_PORT))
socket_tcp.listen(True)

socket_udp = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
socket_udp.bind((SERVER_IP, SERVER_PORT))

threading.Thread(target=handleUdpConnection)

while True:
    connection, new_username = acceptAndSaveNewConnection()
    threading.Thread(target=handleConnection, args=(connection,new_username)).start()

