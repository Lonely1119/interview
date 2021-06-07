//package cn.raocloud.interview.dubbo;
//
//import org.apache.dubbo.common.extension.ExtensionLoader;
//import org.apache.dubbo.rpc.Protocol;
//
//public class ExtensionLoaderTests {
//
//    public static void main(String[] args) {
//        ExtensionLoader<Protocol> extensionLoader = ExtensionLoader.getExtensionLoader(Protocol.class);
//        Protocol protocol = extensionLoader.getExtension("dubbo");
//        System.out.println(protocol.getDefaultPort());
//    }
//}
