package br.com.ucsal.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;


public interface Command {
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
