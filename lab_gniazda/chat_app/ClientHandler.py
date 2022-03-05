from abc import ABC, abstractmethod
from threading import Thread


class ClientHandler(ABC):
    def start(self):
        Thread(target=self.receiveMessages).start()

    @abstractmethod
    def receiveMessages(self):
        pass

    @abstractmethod
    def sendMessage(self, message):
        pass