from Constants import MULTICAST_ADDRESS, SERVER_ADDRESS
from MulticastServerHandler import MulticastServerHandler
from TcpServerHandler import TcpServerHandler
from UdpServerHandler import UdpServerHandler


TcpServerHandler(SERVER_ADDRESS).start()
UdpServerHandler(SERVER_ADDRESS).start()
MulticastServerHandler(MULTICAST_ADDRESS).start()

