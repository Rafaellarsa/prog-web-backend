/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smdecommerce.produto.controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import smdecommerce.produto.modelo.Produto;
import smdecommerce.produto.modelo.ProdutoDAO;
import smdecommerce.relatorio.modelo.RelatorioDAO;
import smdecommerce.vendas.modelo.VendasDAO;

/**
 *
 * @author SilvaVan
 */
public class NovoProdutoServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        /* entrada */
 
        String nome = request.getParameter("nome");
        String desc = request.getParameter("descricao");
        double preco = Double.parseDouble(request.getParameter("preco"));
        String foto = request.getParameter("foto");
        int qntde = Integer.parseInt(request.getParameter("qntde"));
        int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
               
        /* processamento */
        //ProdutoDao produtoDao = new ProdutoDao();
        ProdutoDAO produtoDao = new ProdutoDAO();
        RelatorioDAO relatorioDAO = new RelatorioDAO();
       
        boolean inseriu = false;
        String mensagem = null;
        try {
            produtoDao.cadastrarProduto(nome, desc, preco, foto, qntde, id_categoria);
            inseriu = true;
            mensagem = "Produto cadastrado com sucesso";
            List<Produto> produtos = produtoDao.consultarProdutoPorNome(nome);
            relatorioDAO.inserirRelatorio(nome, "ENTRADA", qntde, produtos.get(0).getId());
        } catch (Exception ex) {
            inseriu = false;
            mensagem = ex.getMessage();
        }
        /* saída */
        
        //Transforma a msg em json
        String json = "";
        json += "{";
            json += "\"inseriu\":" + inseriu + ", ";
            json += "\"mensagem\":" + mensagem;
        json += "}";

        
        //Conf cors 
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
        
        //Envio JSON
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        // primeiro faz funcionar, depois faz direito
        out.print("[");
        out.print(json);
        out.print("]");
    }
}
