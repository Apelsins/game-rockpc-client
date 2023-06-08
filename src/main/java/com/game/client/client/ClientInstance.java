package com.game.client.client;

import com.esotericsoftware.kryonet.Client;
import com.game.client.config.ConnectionProperties;
import com.game.client.dto.CommandType;
import com.game.client.dto.GameCommandDto;
import com.game.client.dto.RegistrationCommandDto;
import com.game.client.dto.RegistrationDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientInstance {

    private Client client;

    public static final int CONNECTION_TIMEOUT = 5000;

    public ClientInstance(final ConnectionProperties connectionProperties) {
        try {
            log.info("Connect to server.");
            client = new Client();

            client.getKryo().register(CommandType.class);
            client.getKryo().register(RegistrationDto.class);
            client.getKryo().register(RegistrationCommandDto.class);
            client.getKryo().register(GameCommandDto.class);

            client.start();

            client.connect(CONNECTION_TIMEOUT, connectionProperties.getIp(), connectionProperties.getPort());

            client.addListener(new ClientListener());
        } catch (final Exception e) {
            log.error("Unable connect to server.", e);
        }
    }

    public boolean sendTCP(final Object msg) {
        final int countBytes = client.sendTCP(msg);
        return countBytes > 0;
    }

}