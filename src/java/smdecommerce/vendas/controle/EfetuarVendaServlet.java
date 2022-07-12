package smdecommerce.vendas.controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import smdecommerce.produto.modelo.ProdutoDAO;
import smdecommerce.relatorio.modelo.RelatorioDAO;
import smdecommerce.vendas.modelo.VendasDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author SilvaVan
 */
public class EfetuarVendaServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        /* entrada */
        int id_cliente_venda = Integer.parseInt(request.getParameter("id_cliente_venda"));
        String ids_produtos_string = request.getParameter("ids_produto_venda");
        ArrayList<String> ids_produto_venda = new ArrayList<>(Arrays.asList(ids_produtos_string.split(",")));

        /* processamento */
        VendasDAO vendaDao = new VendasDAO();
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();

        boolean inseriu = false;
        String mensagem = null;
        
        try {
            vendaDao.cadastrarVenda(id_cliente_venda, ids_produto_venda);
            inseriu = true;
            mensagem = "Operação de venda registrada com sucesso";
            int id_produto = 0;
            for (int i = 0; i < ids_produto_venda.size(); i++) {
                id_produto = Integer.parseInt(ids_produto_venda.get(i));
                relatorioDAO.inserirRelatorio(produtoDAO.consultarProduto(id_produto).getNome(), "SAIDA", ids_produto_venda.size(), id_produto);
            }        
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
