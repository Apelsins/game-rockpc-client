package com.game.client.client;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ClientListener extends Listener {

    @Override
    public void received(final Connection c, final Object p) {
        if (p instanceof PacketMessage) {
            //Если мы получили PacketMessage .
            PacketMessage packet = (PacketMessage) p;
            log.info("Ответ от сервера: '{}'", packet.getMessage());
        }
    }

    @Override
    public void connected(final Connection с) {
        log.info("Connected");
    }

    @Override
    public void disconnected(final Connection с) {
        log.info("Disconnected");
    }

}