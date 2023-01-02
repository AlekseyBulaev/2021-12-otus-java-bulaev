package ru.otus.java.bulaev.servlet;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.otus.java.bulaev.dao.ClientService;
import ru.otus.java.bulaev.model.Address;
import ru.otus.java.bulaev.model.Client;
import ru.otus.java.bulaev.model.Phone;
import ru.otus.java.bulaev.services.TemplateProcessor;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class ClientServlet extends HttpServlet {

    private static final String CLIENT_PAGE_TEMPLATE = "client.html";
    private static final int ID_PATH_PARAM_POSITION = 1;

    private final ClientService clientService;
    private final TemplateProcessor templateProcessor;
    private final Gson gson;
    public ClientServlet(TemplateProcessor templateProcessor, ClientService clientService, Gson gson) {
        this.templateProcessor = templateProcessor;
        this.clientService = clientService;
        this.gson = gson;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, Object> paramsMap = new HashMap<>();
        response.getWriter().println(templateProcessor.getPage(CLIENT_PAGE_TEMPLATE, paramsMap));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Client client = randomClient(null);
        clientService.createClient(client);

        resp.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = resp.getOutputStream();
        out.print(gson.toJson(client));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Client client = randomClient(extractIdFromRequest(req));
        clientService.updateClient(client);

        resp.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = resp.getOutputStream();
        out.print(gson.toJson(client));
    }

    private Client randomClient(Long in) {
        var uuid = UUID.randomUUID().hashCode();
        Long id  = in == null ? uuid : in;
        Faker faker = new Faker();
        var name = faker.name().fullName();
        var phone = faker.phoneNumber().phoneNumber();
        var address = faker.address().fullAddress();

        return new Client(id, name, new Address(id, address), List.of(new Phone(id, phone)));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = clientService.deleteClient(extractIdFromRequest(req));

        resp.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = resp.getOutputStream();
        out.print(gson.toJson(client));
    }

    private long extractIdFromRequest(HttpServletRequest request) throws IOException {
        var id = request.getReader().lines().collect(Collectors.joining());
        return Long.parseLong(id.replace("\"", ""));
    }

}
