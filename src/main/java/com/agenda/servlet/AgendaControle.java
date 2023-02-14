package com.agenda.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agenda.modelo.dao.AgendaDAO;
import com.agenda.modelo.dao.FabricaDAO;

@WebServlet("/agendacontrole")
public class AgendaControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
    AgendaDAO dao = FabricaDAO.criarAgendaDAO();  
    
    public AgendaControle() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
