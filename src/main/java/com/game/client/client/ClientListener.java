package com.game.client.client;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.game.client.dto.ResultDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ClientListener extends Listener {

    @Override
    public void received(final Connection c, final Object p) {
        if (p instanceof ResultDto) {
            //Если мы получили PacketMessage .
            ResultDto resultDto = (ResultDto) p;
            log.info("Answer from server:  '{}'", resultDto.getResult());
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