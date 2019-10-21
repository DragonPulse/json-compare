package org.demo.jsoncompare.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {
	
	@RequestMapping(value = {"/","/serverside"},method= RequestMethod.GET)
	public String redirectToServerCompare() {
		return "servercompare";
	}

	@RequestMapping(value = {"/clientside"},method= RequestMethod.GET)
	public String redirectToClientCompare() {
		return "clientcompare";
	}

	@RequestMapping(value = {"/servercustom"},method= RequestMethod.GET)
	public String redirectToServerCustom() {
		return "customservercompare";
	}
}
