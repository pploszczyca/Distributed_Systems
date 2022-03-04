from typing import Final


SERVER_PORT: Final = 9004
SERVER_IP: Final = 'localhost'
SERVER_ADDRESS: Final = (SERVER_IP, SERVER_PORT)
MULTICAST_IP: Final = '224.1.1.1'
MULTICAST_TTL: Final = 1
MULTICAST_ADDRESS: Final = (MULTICAST_IP, SERVER_PORT)
HEADER_SIZE: Final = 10
DECODING_TYPE: Final = 'UTF-8'
CLOSE_MESSAGE: Final = 'close()'
BUFFER_SIZE: Final = 1024
UDP_MESSAGE_TYPE: Final = "U"
MULTICAST_MESSAGE_TYPE: Final = "M"
ASCII_ART: Final = """
████████╗███████╗░██████╗████████╗
╚══██╔══╝██╔════╝██╔════╝╚══██╔══╝
░░░██║░░░█████╗░░╚█████╗░░░░██║░░░
░░░██║░░░██╔══╝░░░╚═══██╗░░░██║░░░
░░░██║░░░███████╗██████╔╝░░░██║░░░
░░░╚═╝░░░╚══════╝╚═════╝░░░░╚═╝░░░
"""
ASCII_ART_MULTICAST: Final = """
█▀▄▀█ █░█ █░░ ▀█▀ █ █▀▀ ▄▀█ █▀ ▀█▀
█░▀░█ █▄█ █▄▄ ░█░ █ █▄▄ █▀█ ▄█ ░█░
"""