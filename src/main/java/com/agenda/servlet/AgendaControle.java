package com.agenda.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet(urlPatterns = {"/agendacontrole", "/agenda", "/inserir", "/selecionar", "/atualizar", "/delete", "/reportar"})
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
		} else if(url.equals("/selecionar")) {
			exibirContatos(request, response);
		} else if(url.equals("/atualizar")) {
			atualizarContatos(request, response);
		} else if(url.equals("/delete")) {
			deletarContatos(request, response);
		} else if(url.equals("/reportar")) {
			gerarRelatorioContatos(request, response);
		}
	}
	
	protected void listarContatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Agenda> listaContatos = dao.buscarLista();
			
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
			
		   dao.inserir(contatos);
		   
		   response.sendRedirect("agenda");
		}
        
        protected void exibirContatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
 		   String idcon = request.getParameter("idcon");
 		 
 		   System.out.println(idcon);
 		   
 		   contatos.setIdcon(idcon);
 			
 		   dao.mostarContato(contatos);
 		   
 		   System.out.println(contatos.getIdcon());
 		   System.out.println(contatos.getNome());
 		   System.out.println(contatos.getFone());
 		   System.out.println(contatos.getEmail());
 		 
 		   request.setAttribute("idcon", contatos.getIdcon());
 		   request.setAttribute("nome", contatos.getNome());
 		   request.setAttribute("fone", contatos.getFone());
 		   request.setAttribute("email", contatos.getEmail());
 		   
 		   RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
 		   rd.forward(request, response);	
 		}
        
        protected void atualizarContatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
  		   String idcon = request.getParameter("idcon");
  		   String nome = request.getParameter("nome");
  		   String fone = request.getParameter("fone");
  		   String email = request.getParameter("email");
  		   
  		   contatos.setIdcon(idcon);
  		   contatos.setNome(nome);
  		   contatos.setFone(fone);
  		   contatos.setEmail(email);
  			
  		   dao.editar(contatos);
 
  		   response.sendRedirect("agenda");	
  		}
        
        protected void deletarContatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
   		   String idcon = request.getParameter("idcon");
   		   
   		   contatos.setIdcon(idcon);
   		
   		   dao.deletar(contatos);
  
   		   response.sendRedirect("agenda");	
   		}
        
        protected void gerarRelatorioContatos(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {

    		// capturando idcon
    		String idcon = request.getParameter("idcon");
    		System.out.println(idcon);

    		Document doc = new Document();
            try {
            	
            	 response.reset();
            	 
            	//tipo de conteudo
            	response.setContentType("application/pdf");
               
            	//nome do conteudo
    			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
    			
    			//criar o documento
    			PdfWriter.getInstance(doc, response.getOutputStream());
    			
    			//Abrir documento para gerar conteudo
    			doc.open();
    			doc.add(new Paragraph("Lista de contatos:"));
    			doc.add(new Paragraph(" "));
    			
    			//criar uma tabela
    			PdfPTable tabela = new PdfPTable(3);
    			
    			//cabeçalho
    			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
    			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
    			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
    			tabela.addCell(col1);
    			tabela.addCell(col2);
    			tabela.addCell(col3);
    			
    			//papular tabela
    			List<Agenda> lista = dao.buscarLista();
    			for(Agenda a: lista) {
    				tabela.addCell(a.getNome());
    				tabela.addCell(a.getFone());
    				tabela.addCell(a.getEmail());
    			}
    			doc.add(tabela);
    			doc.close();
            } catch (Exception e) {
    			System.out.println(e);
    			doc.close();
    		}
    	}
	}