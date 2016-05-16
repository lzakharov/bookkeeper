package ru.kpfu.itis.fujitsu.lzakharov.bookkeeper.service;

import ru.kpfu.itis.fujitsu.lzakharov.bookkeeper.model.Client;

public interface ClientService {
    long create(Client client) throws ClientAlreadyExistsException;

    Client find(String login, String password);
}
