package com.connectionPooling.app;

public class Connection_Pooling_Main {

	public static void main(String[] args) {
		Connection_Pooling cp =
			new Connection_Pooling("jdbc:oracle:thin:@localhost:1521:ORCL","system","harsh");
		 cp.createConnections();

	}

}
