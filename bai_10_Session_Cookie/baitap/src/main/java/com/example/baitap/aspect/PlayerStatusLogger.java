package com.example.baitap.aspect;

import com.example.baitap.entity.Player;
import com.example.baitap.repository.IPlayerRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
@RequiredArgsConstructor
public class PlayerStatusLogger {

    private final IPlayerRepository playerRepository;
    private static final String LOG_FILE = "logs/player-status-log.txt";

    @AfterReturning(
            pointcut = "execution(* com.example.baitap.service.impl.PlayerService.toggleStatus(..))",
            returning = "result")
    public void logPlayerStatusChange(JoinPoint joinPoint, Object result) {
        if (!(result instanceof String change)) return;
        Integer id = (Integer) joinPoint.getArgs()[0];
        Player player = playerRepository.findById(id).orElse(null);
        if (player == null) return;

        long count = playerRepository.countByStatus("Đã đăng ký");
        String logMessage = String.format("[%s] %s (%s) | Tổng đăng ký: %d%n",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                player.getName(), change, count);

        writeLogToFile(logMessage);
    }

    private void writeLogToFile(String logMessage) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(logMessage);
        } catch (IOException e) {
            System.err.println("Không thể ghi log: " + e.getMessage());
        }
    }
}
