import socket;

serverIP = "127.0.0.1"
serverPort = 9009
msg = "żółta gęś"
msg_bytes = (300).to_bytes(4, byteorder='big')

print('PYTHON UDP CLIENT')
client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
client.sendto(bytes(msg, 'UTF-8'), (serverIP, serverPort))
client.sendto(msg_bytes, (serverIP, serverPort))
buff, address = client.recvfrom(1024)
print(int.from_bytes(buff, byteorder='big'))



