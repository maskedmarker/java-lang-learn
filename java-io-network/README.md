connectTimeout即socket连接超时时间,只使用一次.
socketTimeout即socket读超时时间,会使用多次.用于read操作.socketTimeout可以设置多次.
具体看jdk源码.
java.net.DualStackPlainSocketImpl.socketConnect
java.net.SocketInputStream.read(byte[], int, int)

# NIO(New IO)
Java NIO (New IO) is an alternative IO API for Java, meaning alternative to the standard Java IO and Java Networking API's. 
Java NIO offers a different IO programming model than the traditional IO APIs. 
Note: Sometimes NIO is claimed to mean Non-blocking IO. However, this is not what NIO meant originally. 
Also, parts of the NIO APIs are actually blocking - e.g. the file APIs - so the label "Non-blocking" would be slightly misleading.

The central abstractions of the NIO APIs are:
Buffers, which are containers for data;
Charsets and their associated decoders and encoders, which translate between bytes and Unicode characters;
Channels of various types, which represent connections to entities capable of performing I/O operations;
Selectors and selection keys, which together with selectable channels define a multiplexed, non-blocking I/O facility.

The java.nio package defines the buffer classes, the java.nio.charset package and java.nio.channels package. 
Each of these subpackages has its own service-provider (SPI) subpackage, the contents of which can be used to extend the platform's default implementations or to construct alternative implementations.
