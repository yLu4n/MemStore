package br.com.ucsal.controller;

import br.com.ucsal.anotacoes.Rota;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class RoteamentoServlet extends HttpServlet {

    private Map<String, Command> rotas = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
        carregarRotas();
    }

    private void carregarRotas() {
        //Utilizar reflexão se precisar
        rotas.put("/produto/adicionar", new ProdutoAdicionarServlet());

        // Adicione outras rotas conforme necessário
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String caminho = req.getPathInfo();

        Command comando = rotas.get(caminho);
        if (comando != null) {
            comando.execute(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Rota não encontrada.");
        }
    }
}
