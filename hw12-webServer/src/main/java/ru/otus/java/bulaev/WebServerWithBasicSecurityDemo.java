package ru.otus.java.bulaev;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.security.LoginService;
import ru.otus.java.bulaev.dao.InMemoryClientService;
import ru.otus.java.bulaev.dao.ClientService;
import ru.otus.java.bulaev.server.UsersWebServer;
import ru.otus.java.bulaev.server.UsersWebServerWithBasicSecurity;
import ru.otus.java.bulaev.services.InMemoryLoginServiceImpl;
import ru.otus.java.bulaev.services.TemplateProcessor;
import ru.otus.java.bulaev.services.TemplateProcessorImpl;

/*
    Полезные для демо ссылки

    // Стартовая страница
    http://localhost:8080

    // Страница пользователей
    http://localhost:8080/clients

    // REST сервис
    http://localhost:8080/api/client/3
*/
public class WebServerWithBasicSecurityDemo {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) throws Exception {
        ClientService clientService = new InMemoryClientService();
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);

        LoginService loginService = new InMemoryLoginServiceImpl(clientService);

        UsersWebServer usersWebServer = new UsersWebServerWithBasicSecurity(WEB_SERVER_PORT,
                loginService, clientService, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }
}
