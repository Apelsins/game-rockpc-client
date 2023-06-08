package com.game.client.domain;

import com.game.client.client.ClientInstance;
import com.game.client.config.ConnectionProperties;
import com.game.client.dto.CommandType;
import com.game.client.dto.RegistrationCommandDto;
import com.game.client.dto.RegistrationDto;
import com.game.client.dto.StageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.util.Scanner;

@Slf4j
@Component
public class SendingService implements ApplicationRunner {

    private final ConnectionProperties connectionProperties;
    private ClientInstance clientInstance;
    private final Scanner scanner;

    public SendingService(final ConnectionProperties connectionProperties) {
        this.connectionProperties = connectionProperties;
        scanner = new Scanner(System.in);
    }

    private StageType current_stage = StageType.BEGIN;

    @Override
    public void run(final ApplicationArguments args) {

        while (true) {
            switch (current_stage) {
                case BEGIN:
                    stageBegin();
                    break;
                case MENU:
                    break;
                case GAME_1:
                    break;
                case GAME_2:
                    break;
                case GAME_3:
                    break;
            }

        }
    }

    private void stageBegin() {
        log.info("""
                    Stage SignUp/SignIn. Enter the command:
                    signup=<login>=<password>
                    signin=<login>=<password>
                    """);

            final String command = scanner.nextLine();
            if (command.contains("signup")) {
                final boolean isSuccess = sendSignupCommand(command);
                if (!isSuccess) {
                    throw new RuntimeException("Cannot sign up");
                }
                log.info("You signed up!");
            } else if (command.contains("signin")) {
                final boolean isSuccess = sendSigninCommand(command);
                if (!isSuccess) {
                    throw new RuntimeException("Cannot sign in");
                }
                current_stage = StageType.MENU;
                log.info("You signed in. Play!");
            } else {
                log.error("Command didn't found. Repeat command...");
            }

    }

    private synchronized void createClientInstance() {
        if (clientInstance == null) {
            clientInstance = new ClientInstance(connectionProperties);
        }
    }

    private boolean sendSignupCommand(final String command) {
        createClientInstance();
        final String[] logAndPass = command.split("=");
        final RegistrationDto registrationDto = new RegistrationDto(logAndPass[1], logAndPass[2]);
        final RegistrationCommandDto cmd = new RegistrationCommandDto(CommandType.SIGN_UP, registrationDto);
        return clientInstance.sendTCP(cmd);
    }

    private boolean sendSigninCommand(final String command) {
        createClientInstance();
        final String[] logAndPass = command.split("=");
        final RegistrationDto registrationDto = new RegistrationDto(logAndPass[1], logAndPass[2]);
        final RegistrationCommandDto cmd = new RegistrationCommandDto(CommandType.SIGN_IN, registrationDto);
        return clientInstance.sendTCP(cmd);
    }
}

