package com.agenda.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agenda.modelo.Agenda;
import com.agenda.modelo.dao.AgendaDAO;
import com.agenda.modelo.dao.FabricaDAO;

@WebServlet(urlPatterns = {"/agendacontrole", "/agenda", "/inserir"})
public class AgendaControle extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//conexão banco de dados
    AgendaDAO dao = FabricaDAO.criarAgendaDAO();  
    
    //instancia de objeto
    Agenda contatos = new Agenda();
    
    public AgendaControle() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("METODO GET");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String url = request.getServletPath();
		System.out.println(url);
		
		if(url.equals("/agenda")) {
			listarContatos(request, response);
		} else if(url.equals("/inserir")) {
			inserirContatos(request, response);
		}
	}
	
	protected void listarContatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Agenda> listaContatos = dao.buscarLista();
		
	       /**for(Agenda contatos: listaContatos) {
			System.out.println(contatos.getIdcon());
			System.out.println(contatos.getNome());
			System.out.println(contatos.getFone());
			System.out.println(contatos.getEmail());**/
			
		request.setAttribute("contatos", listaContatos);
		
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);	
		}
	
        protected void inserirContatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		   String idcon = request.getParameter("idcon");
		   String nome = request.getParameter("nome");
		   String fone = request.getParameter("fone");
		   String email = request.getParameter("email");
	      
		   System.out.println(idcon);
		   System.out.println(nome);
		   System.out.println(fone);
		   System.out.println(email);
		   
		   contatos.setNome(nome);
		   contatos.setFone(fone);
		   contatos.setEmail(email);
			
		   dao.inserir(contatos);
		   
		   response.sendRedirect("agenda");
		//request.setAttribute("contatos", listaContatos);
		
		//RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		//rd.forward(request, response);	
		}
	}
