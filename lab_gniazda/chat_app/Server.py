import socket
import threading
from Constants import CLOSE_MESSAGE, MULTICAST_ADDRESS, MULTICAST_IP, MULTICAST_TTL, SERVER_ADDRESS, SERVER_IP, SERVER_PORT
from Decoding import decodeMessageWithFrame
from MessagesService import receiveMessage
from MulticastSocket import MulticastSocket
from UdpSocket import UdpSocket


def makeUserMessage(username: str, message: str):
    return "{}: {}".format(username, message)

def sendMessageToOtherUsers(username: str, message: str):
    user_connections_lock.acquire()

    for user, userConnection in user_connections.items():
        if user != username:
            userConnection.send(decodeMessageWithFrame(makeUserMessage(username, message)))

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

def sendMessageToOthersUdp(address, message: str):
    sender_username = address_udp_user[address]

    for user_address in address_udp_user.keys():
        if user_address != address:
            udp_socket.sendMessage(makeUserMessage(sender_username, message), user_address)

def handleUdpConnection():
    while True:
        data, address = udp_socket.receiveMessage()
        if address not in address_udp_user.keys():
            address_udp_user[address] = data
        else:
            sendMessageToOthersUdp(address, data)

def handleMulticastMessages():
    while True:
        print(multicast_socket.receiveMessage())

user_connections = {}
user_connections_lock = threading.Lock()

address_udp_user = {}

socket_tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
socket_tcp.bind(SERVER_ADDRESS)
socket_tcp.listen(True)

udp_socket = UdpSocket(SERVER_ADDRESS, True)
multicast_socket = MulticastSocket(MULTICAST_ADDRESS)

threading.Thread(target=handleUdpConnection).start()
threading.Thread(target=handleMulticastMessages).start()

while True:
    connection, new_username = acceptAndSaveNewConnection()
    threading.Thread(target=handleConnection, args=(connection,new_username)).start()

