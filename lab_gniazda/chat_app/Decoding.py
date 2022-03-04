from Constants import DECODING_TYPE, HEADER_SIZE

def makeFrame(message: str):
    return "{header: <{fill}}{message}".format(header=len(message), fill=HEADER_SIZE, message=message)

def decodeMessage(message: str):
    return bytes(makeFrame(message), DECODING_TYPE)

def encodeMessageToString(message):
    return str(message, DECODING_TYPE)

def encodeMessageToInt(message):
    return int(encodeMessageToString(message))
