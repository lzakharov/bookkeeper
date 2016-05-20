package ru.kpfu.itis.fujitsu.lzakharov.bookkeeper.service.impl;

import ru.kpfu.itis.fujitsu.lzakharov.bookkeeper.dao.ClientDao;
import ru.kpfu.itis.fujitsu.lzakharov.bookkeeper.dao.hsqldb.ClientDaoHsqldb;
import ru.kpfu.itis.fujitsu.lzakharov.bookkeeper.model.Client;
import ru.kpfu.itis.fujitsu.lzakharov.bookkeeper.service.ClientAlreadyExistsException;
import ru.kpfu.itis.fujitsu.lzakharov.bookkeeper.service.ClientService;

/**
 *
 * @see ru.kpfu.itis.fujitsu.lzakharov.bookkeeper.service.ClientService
 */
public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao;

    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public long create(Client client) throws ClientAlreadyExistsException {
        String login = client.getLogin();
        if (clientDao.get(login) != null) {
            throw new ClientAlreadyExistsException("Unable to create client. Client with login '" + login + "' already exists");
        }
        return clientDao.add(client);
    }

    @Override
    public Client find(String login, String password) {
        Client client = clientDao.get(login);
        if (client != null && client.getPassword().equals(password)) {
            return client;
        } else {
            return null;
        }
    }
}
