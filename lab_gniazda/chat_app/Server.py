import signal
import sys
from Constants import MULTICAST_ADDRESS, SERVER_ADDRESS
from MulticastServerHandler import MulticastServerHandler
from TcpServerHandler import TcpServerHandler
from UdpServerHandler import UdpServerHandler

def signal_handler(sig, frame):
    tcp_server_handler.close()
    sys.exit(0)

tcp_server_handler = TcpServerHandler(SERVER_ADDRESS)

tcp_server_handler.start()
UdpServerHandler(SERVER_ADDRESS).start()
MulticastServerHandler(MULTICAST_ADDRESS).start()

signal.signal(signal.SIGINT, signal_handler)
signal.pause()
