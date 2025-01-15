package com.opencabinet.service;

import com.opencabinet.Consts.Actions;
import com.opencabinet.models.packagemodels.ReqPackage;
import com.opencabinet.models.util.Pair;

import java.util.Scanner;
public class FromClient implements Runnable{

	private Actions actions = new Actions();
	private Scanner clientOutput;
	private String username;
	private int port;
	public FromClient(Scanner clientOutput, String username, int port) {
		this.clientOutput=clientOutput;
		this.port=port;
		this.username=username;
	}
	@Override
	public void run(){
		while(ServerThread.continueRuntime) {
			String clientJsonString = clientOutput.nextLine();
			ReqPackage inbound = new ReqPackage(clientJsonString);
			ServerThread.outbound.add(new Pair<String, ReqPackage>(username, inbound));

			System.out.println("Req Received from "+username+" concerning REQ_MODE "+inbound.getHeader());
		}
	}
}
