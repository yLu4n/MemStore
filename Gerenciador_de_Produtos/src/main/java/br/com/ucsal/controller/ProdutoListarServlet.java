package br.com.ucsal.controller;

import java.io.IOException;
import java.util.List;

import br.com.ucsal.anotacoes.Inject;
import br.com.ucsal.anotacoes.Rota;
import br.com.ucsal.model.Produto;
import br.com.ucsal.persistencia.HSQLProdutoRepository;
import br.com.ucsal.service.ProdutoService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Rota(value = "/listarProdutos")
public class ProdutoListarServlet implements Command {
    private static final long serialVersionUID = 1L;
    
    @Inject
	private ProdutoService produtoService;

	public ProdutoListarServlet() {
        produtoService = new ProdutoService(new HSQLProdutoRepository());
	}
	
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém a lista de produtos
        List<Produto> produtos = produtoService.listarProdutos();
        
        // Define a lista de produtos como atributo da requisição
        request.setAttribute("produtos", produtos);
        
        // Encaminha para a página JSP que exibe a lista de produtos
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/produtolista.jsp");
        dispatcher.forward(request, response);
    }

}
