package com.example.sodacloudsmsexampleclient;

import org.magnum.soda.proxy.ObjRef;

public class ExternalObjRefImpl implements ExternalObjRef {
	
	private ObjRef objRef;
	private String pubSubHost;
	
	public ExternalObjRefImpl(ObjRef objref, String pubSubHost){
		this.objRef = objref;
		this.pubSubHost = pubSubHost;
	}

	@Override
	public ObjRef getObjRef() {
		return objRef;
	}

	@Override
	public String getPubSubHost() {
		return pubSubHost;
	}
	

	public String toString(){
		return getPubSubHost()+"|"+getObjRef().getUri();
	}

}
