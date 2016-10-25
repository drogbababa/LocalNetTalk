package business;

import java.io.IOException;
import java.net.*;

import util.Constant;
import window.ServerWindow;

public class ServerImpl {
	private static ServerSocket server = null;
	private static ServerImpl serverImpl = null;
//	private ServerWindow serverWindow = null;/////////1
//	private Thread t = null;
	
	private ServerImpl(){
		try {
//			if(server==null){
//				server = new ServerSocket(6666);
//			}else{
//				
//			}
			server = new ServerSocket(Constant.LISTEN_PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		ServerWindow serverWindow = ServerWindow.getInstance();///////////1
		
//		serverWindow = new ServerWindow();/////////1
//		
//		serverWindow.setVisible(true);
//		serverWindow.display("�ȴ�������......");
		while(true){
			try {
				Socket socket = server.accept();
				
				ServerWindow serverWindow = new ServerWindow();/////////1
				serverWindow.setVisible(true);//ÿ���пͻ������Ӿ�����һ����������
				Constant.currentServer = server;
				
//				Constant.currentClient = ClientImpl.getClient();
				Thread t = new Thread(new ServerThread(socket,ClientImpl.getClient(),serverWindow));
				serverWindow.setT(t);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static ServerImpl getInstance(){
		if(serverImpl == null){
			serverImpl = new ServerImpl();
		}
		return serverImpl;
	}
	public static void close(){
		if(serverImpl!=null){
			serverImpl.close();
//			serverImpl= null;
			try {
				Constant.currentServer.close();
//				server = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.exit(1);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerImpl.getInstance();
//		new ServerImpl();
	}
	public static ServerSocket getServer(){
		return server;
	}
}
