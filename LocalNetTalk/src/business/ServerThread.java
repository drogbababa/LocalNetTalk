package business;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import util.Constant;
import window.ServerWindow;

public class ServerThread implements Runnable{
	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
//	private Socket client = null;
	private ServerWindow serverWindow = null;/////////1
	
	public ServerThread(Socket socket,ServerWindow serverWindow){///////////1
		this.socket = socket;
//		this.client = client;
		this.serverWindow = serverWindow;//////////1
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
//		ServerWindow.getInstance().display("����ͻ���" + client.getInetAddress().getHostAddress() + "��������");
		
//		ServerWindow.getInstance().display("---------------------");/////////1
		serverWindow.display("����ͻ��ˣ�" + socket.getRemoteSocketAddress() +  " "+socket.getInetAddress().getHostName() +" �������ӣ�");//getInetAddress()����socket�����ӵ�ַ
		serverWindow.display("---------------------");
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
//			ServerWindow.getInstance().setOut(out);/////////////?????�����Ļ����·�������ֻ��һ�������������ֻ�������һ���ͻ������
			serverWindow.setOut(out);///////////////1
			
			in = new ObjectInputStream(socket.getInputStream());
			String message = null;
			while(true){
				try {
					message = (String)in.readObject();
					if(!message.equals(Constant.CONNECT_QUIT)){
//						ServerWindow.getInstance().setOut(null);//////////////////1
						serverWindow.display(message);
					}else{
						serverWindow.setOut(null);
						break;
					}
//					ServerWindow.getInstance().display(message);////////////1
					
//					serverWindow.display(message);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
//			ServerWindow.getInstance().display("�ͻ���" + ClientImpl.getClient().getInetAddress().getHostAddress() + "�ж�����");
			serverWindow.display("�ͻ���:" + socket.getInetAddress().getHostName() + "�ж�����");
			
			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
