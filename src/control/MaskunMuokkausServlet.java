package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Lasku;
import model.dao.LaskuDAO;
import model.dao.MaksajaDAO;


@WebServlet("/muuta-laskua")
public class MaskunMuokkausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*Otetaan vastaan muokattavan laskun id*/
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		
		LaskuDAO laskuDAO = new LaskuDAO();
		MaksajaDAO maksajaDAO = new MaksajaDAO();
		ArrayList<Lasku> laskut = laskuDAO.findAll();
		Lasku lasku = null;
		
		/*Haetaan teitokannan laskuista id:tä vastaavan laskun tiedot talteen*/
		for (int i = 0; i < laskut.size(); i++) {
			if (laskut.get(i).getId() == id) {
				lasku = laskut.get(i);
				break;
			}
		}
		
		/*Lähetetään jsp:lle laskun ja maksajien tiedot laskun muokkausta varten*/
		request.setAttribute("lasku", lasku);
		request.setAttribute("maksajat", maksajaDAO.findAll());
		String jsp = "/view/maksunmuokkaus.jsp";
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idString = request.getParameter("id");
		System.out.println(idString);
		int id = Integer.parseInt(idString);
		
		String kuvaus = request.getParameter("kuvaus");
		
		String hintaString = request.getParameter("hinta");
		hintaString = hintaString.replace(",", ".");
		double hinta = Double.parseDouble(hintaString);
		
		String pvmString = request.getParameter("pvm");
		
		Date pvm = null;
		try {
			pvm = new SimpleDateFormat("yyyy-MM-dd").parse(pvmString);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String maksajaString = request.getParameter("maksaja");
		int maksaja = Integer.parseInt(maksajaString);
		
		Lasku lasku = new Lasku(id, kuvaus, hinta, pvm, maksaja);
		
		LaskuDAO laskuDAO = new LaskuDAO();
		System.out.println(idString);
		try {
			laskuDAO.updateLasku(lasku);
			response.sendRedirect("maksulista");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
