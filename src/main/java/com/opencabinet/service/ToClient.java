package com.opencabinet.service;


import com.opencabinet.Consts.Actions;
import com.opencabinet.Server;
import com.opencabinet.commands.ICommand;
import com.opencabinet.models.packagemodels.ReqPackage;
import com.opencabinet.models.util.Pair;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ToClient implements Runnable{

	Actions actions = new Actions();
	private int port;
	public ToClient(int port) {
		this.port=port;
	}

	@Override
	public void run(){
		while(ServerThread.continueRuntime) {
			if(!ServerThread.outbound.empty()) {
				
				System.out.println("Retrieving Next Outbound Command");
				Pair<String, ReqPackage> outboundPair = ServerThread.outbound.pop();
				PrintWriter writer = Server.activePort.getUserWriter(outboundPair.a());
				ReqPackage reqPackage = outboundPair.b();

				ICommand action = actions.commands.get(reqPackage.getHeader());
				String outboundJsonString = action.execute(action.unpackReq(reqPackage.getBody()));

				writer.println(outboundJsonString);
			}
		}
	}
}
