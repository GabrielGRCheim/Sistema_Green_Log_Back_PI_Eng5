package com.senai.demo.models.padraoprojeto.singleton;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogEventoSingleton {

    private static LogEventoSingleton instance;

    private static final String FILE_PATH = "logs-eventos.txt"; // arquivo na raiz do projeto
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private LogEventoSingleton() {
        // construtor privado (garante Singleton)
    }

    public static synchronized LogEventoSingleton getInstance() {
        if (instance == null) {
            instance = new LogEventoSingleton();
        }
        return instance;
    }

    public void registrar(String mensagem) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String logCompleto = "[" + timestamp + "] " + mensagem;

        // Imprime no console
        System.out.println(logCompleto);

        // Grava em arquivo
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(logCompleto + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo de log: " + e.getMessage());
        }
    }
}

