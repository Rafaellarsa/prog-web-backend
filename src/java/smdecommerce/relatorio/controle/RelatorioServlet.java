/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smdecommerce.relatorio.controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import smdecommerce.relatorio.modelo.RelatorioDAO;
import smdecommerce.relatorio.modelo.Relatorio;

/**
 *
 * @author ivalm
 */
public class RelatorioServlet extends HttpServlet {
  
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        List<Relatorio> relatorios;
        
        try {
            relatorios = relatorioDAO.listarRelatorios();
        } catch (Exception ex) {
            relatorios = new ArrayList<Relatorio>();
        }
        
        //CORS
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
        
        //Envio JSON
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print("[");
            for (int i = 0; i < relatorios.size(); i++) {
                out.print(relatorios.get(i).toJSON());
                if (i < relatorios.size() - 1) {
                    out.print(", ");
                }
            }
            out.print("]");
        }
    }
}