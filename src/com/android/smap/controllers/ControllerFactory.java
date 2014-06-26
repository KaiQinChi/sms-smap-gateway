package com.android.smap.controllers;

import com.android.smap.api.models.Gojo;
import com.android.smap.samuel.Samuel;
import com.android.smap.sms.GatewayService;

/**
 * Temporary idea to map GoJo's to their action controllers.
 * 
 * @author Matt Witherow
 * 
 */
public class ControllerFactory {

	public static Controller fetch(Gojo gojo, GatewayService relayService) {

		switch (gojo.cmd) {
		case EMAIL:
			gojo.controller = new EmailController(
					relayService.getApplicationContext(),
					gojo.find(Samuel.EMAIL),
					gojo.find(Samuel.SUBJECT),
					gojo.find(Samuel.MSG));
			break;
		case POST:
			gojo.controller = new GojoController(gojo, relayService,
					relayService, relayService);
			break;
		}

		return gojo.controller;

	}
}
