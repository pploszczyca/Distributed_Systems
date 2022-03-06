import signal
import sys
from Constants import ASCII_ART, ASCII_ART_MULTICAST, CLOSE_MESSAGE, MULTICAST_ADDRESS, MULTICAST_MESSAGE_TYPE, SERVER_ADDRESS, UDP_MESSAGE_TYPE
from MulticastClientHandler import MulticastClientHandler
from TcpClientHandler import TcpClientHandler
from UdpClientHandler import UdpClientHandler


def signal_handler(sig, frame):
    close_tcp_connection()
    sys.exit(0)

def close_tcp_connection():
    tcp_client_handler.sendMessage(CLOSE_MESSAGE)
    tcp_client_handler.close()

signal.signal(signal.SIGINT, signal_handler)

username = input("Enter username: ")

tcp_client_handler = TcpClientHandler(SERVER_ADDRESS, username)
udp_client_handler = UdpClientHandler(SERVER_ADDRESS, username)
multicast_client_handler = MulticastClientHandler(MULTICAST_ADDRESS, username)

tcp_client_handler.start()
udp_client_handler.start()
multicast_client_handler.start()


while True:
    input_data = input("")

    if input_data == UDP_MESSAGE_TYPE:
        udp_client_handler.sendMessage(ASCII_ART)
    elif input_data == MULTICAST_MESSAGE_TYPE:
        multicast_client_handler.sendMessage(ASCII_ART_MULTICAST)
    elif input_data == CLOSE_MESSAGE:
        close_tcp_connection()
        break
    else:
        tcp_client_handler.sendMessage(input_data)

