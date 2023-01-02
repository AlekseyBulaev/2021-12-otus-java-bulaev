package ru.otus.java.bulaev.services;

import org.eclipse.jetty.security.AbstractLoginService;
import org.eclipse.jetty.security.RolePrincipal;
import org.eclipse.jetty.security.UserPrincipal;
import org.eclipse.jetty.util.security.Password;
import ru.otus.java.bulaev.dao.ClientService;

import java.util.List;

public class InMemoryLoginServiceImpl extends AbstractLoginService {

    private final ClientService clientServiceDao;

    public InMemoryLoginServiceImpl(ClientService clientService) {
        this.clientServiceDao = clientService;
    }


    @Override
    protected List<RolePrincipal> loadRoleInfo(UserPrincipal userPrincipal) {
        return List.of(new RolePrincipal("admin"));
    }

    @Override
    protected UserPrincipal loadUserInfo(String login) {
        System.out.println(String.format("InMemoryLoginService#loadAdminInfo(%s)", login));
        return new UserPrincipal("admin", new Password("123"));
    }
}
