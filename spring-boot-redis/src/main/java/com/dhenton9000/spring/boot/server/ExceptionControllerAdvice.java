/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.spring.boot.server;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(Exception.class)
	public String exception(Exception exception, Model model){
		model.addAttribute("error", exception.toString());
                model.addAttribute("message", exception.getMessage());
                model.addAttribute("timestamp", (new java.util.Date()).toString());
		return "error";
	}
	
}
