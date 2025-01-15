package com.opencabinet.service;

import com.opencabinet.Consts.CustomEnums;
import com.opencabinet.Server;
import com.opencabinet.db.MainController;
import com.opencabinet.models.util.Pair;
import com.opencabinet.models.User;
import com.opencabinet.models.packagemodels.InitPackage;
import com.opencabinet.models.packagemodels.ReqPackage;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Scanner;
import java.util.Stack;

public class ServerThread extends Thread{

	private Socket socket;
	public static boolean continueRuntime = true;
	private int port; 

	private MainController db = new MainController();
	public static Stack<Pair<String, ReqPackage>> outbound = new Stack<>();
	public ServerThread(Socket s, int port) {
		this.socket=s;
		this.port =port;
	
	}
	
	public void run() {
		try {
			System.out.println("Client req received at "+socket);
			
			
			Scanner serverInput = new Scanner(socket.getInputStream());
			PrintWriter serverOutput = new PrintWriter(socket.getOutputStream(), true);
	
			ReqPackage reqPackage = doSetupConnection(serverInput, serverOutput);

			if(reqPackage.getHeader() == CustomEnums.REQ_MODE.LOG_IN_SUCCESS.getValue()) {
				InitPackage initParams = doLogIn((InitPackage) reqPackage, serverOutput);

				Thread fC = new Thread(new FromClient(serverInput, initParams.getUsername(), port));
				fC.start();
				Thread tC = new Thread(new ToClient(port));
				tC.start();

			}else if(reqPackage.getHeader()==CustomEnums.REQ_MODE.LOG_IN_SUCCESS.getValue()) {
				doFailedLogIn(reqPackage, serverOutput);
			}
			while(continueRuntime) {
				if(!continueRuntime) {
					if(reqPackage.getHeader() == CustomEnums.REQ_MODE.LOG_IN_SUCCESS.getValue()) {
						InitPackage initPackage = (InitPackage) reqPackage;
						Server.activePort.removeConnectedUser(initPackage.getUsername());
					}
					socket.close();
					System.out.println("Connection Closed");
				}
			}
			
			
			
		}catch(Exception e) {}
	}

	private ReqPackage doSetupConnection(Scanner serverInput, PrintWriter serverOutput) throws UnsupportedEncodingException {


		String serverInputLine = serverInput.nextLine();
		System.out.println("Received init package, unpacking...");
		System.out.println(serverInputLine);
		
		ReqPackage reqPackage = new ReqPackage(serverInputLine);

		if(reqPackage.getHeader()==CustomEnums.REQ_MODE.LOG_IN.getValue()) {
			InitPackage initPackage = new InitPackage(reqPackage.getHeader(), reqPackage.getBody());
			db.uc.validateLogIn(initPackage.getUsername(), initPackage.getPassword());
			return initPackage;
		}else {
			continueRuntime = false;
			reqPackage.setHeader(CustomEnums.REQ_MODE.LOG_IN_FAIL.getValue());
			return reqPackage;
		}

	}

	private InitPackage doLogIn(InitPackage initPackage, PrintWriter serverOutput) throws UnsupportedEncodingException {
		System.out.println("Setting up Log in for "+initPackage.getUsername()+"...");
		User user = db.uc.getUser(initPackage.getUsername());
		System.out.println("Adding user to active users");
		Server.activePort.addConnectedUser(user);
		System.out.println("Sending log in package...");
		serverOutput.println(ReqPackage.encodeToJson(initPackage));
		System.out.println("Setup Complete...");
		return initPackage;
	}

	private void doFailedLogIn(ReqPackage initPackage, PrintWriter serverOutput) throws UnsupportedEncodingException {
		System.out.println("Setting up failed log in package...");
		initPackage.setHeader(CustomEnums.REQ_MODE.LOG_IN_FAIL.getValue());
		System.out.println("Sending failed log in package...");
		serverOutput.println(ReqPackage.encodeToJson(initPackage));
	}

	
}
