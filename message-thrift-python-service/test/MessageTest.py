from thrift.transport import TSocket
from thrift.transport import TTransport

from thrift.protocol import TBinaryProtocol

from message.api import MessageService

# 测试代码
if __name__ == "__main__":
    socket = TSocket.TSocket("localhost", "7912")
    transport = TTransport.TFramedTransport(socket)
    protocol = TBinaryProtocol.TBinaryProtocol(transport)

    client = MessageService.Client(protocol)

    transport.open()

    client.sendEmailMessage("xuehuiwang@blackfish.cn", "测试代码")
