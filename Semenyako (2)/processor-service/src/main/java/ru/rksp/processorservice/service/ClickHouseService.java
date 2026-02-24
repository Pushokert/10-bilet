package ru.rksp.processorservice.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;

@Service
public class ClickHouseService {

    @Value("${clickhouse.url}")
    private String clickHouseUrl;

    @Value("${clickhouse.username}")
    private String clickHouseUsername;

    @Value("${clickhouse.password}")
    private String clickHousePassword;

    @PostConstruct
    public void initTable() {
        // Обратные кавычки обязательны для кириллических имён в ClickHouse
        String createTableSql = "CREATE TABLE IF NOT EXISTS `агрегаты_событий_платежей` (" +
                "`дата_и_время_записи` DateTime, " +
                "`количество_записей` UInt64" +
                ") ENGINE = MergeTree() ORDER BY `дата_и_время_записи`";

        try (Connection connection = DriverManager.getConnection(clickHouseUrl, clickHouseUsername, clickHousePassword);
             Statement statement = connection.createStatement()) {
            statement.execute(createTableSql);
            System.out.println("ClickHouse table created or already exists");
        } catch (SQLException e) {
            System.err.println("Error creating ClickHouse table: " + e.getMessage());
        }
    }

    public void saveEventCount(long count) {
        String sql = "INSERT INTO `агрегаты_событий_платежей` (`дата_и_время_записи`, `количество_записей`) VALUES (?, ?)";
        
        try (Connection connection = DriverManager.getConnection(clickHouseUrl, clickHouseUsername, clickHousePassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setObject(1, LocalDateTime.now());
            statement.setLong(2, count);
            statement.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error saving to ClickHouse", e);
        }
    }
}
