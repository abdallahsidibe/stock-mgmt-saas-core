package com.tech.saas.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Configuration
@Slf4j
public class DatabaseInitializerConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource(
            @Value("${DB_HOST:localhost}") final String host,
            @Value("${DB_PORT:5432}") final int port,
            @Value("${DB_NAME:saas-app-db}") final String dbName,
            @Value("${DB_USERNAME:postgres}") final String username,
            @Value("${DB_PASSWORD:postgres}") final String password
    ) {
        createDatabaseIfNotExists(host, port, dbName, username, password);

        final HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://" + host + ":" + port + "/" + dbName);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName("org.postgresql.Driver");
        return ds;
    }

    private void createDatabaseIfNotExists(final String host,
                                           final int port,
                                           final String dbName,
                                           final String username,
                                           final String password) {
        final String adminUrl = "jdbc:postgresql://" + host + ":" + port + "/postgres";
        try (final Connection conn = DriverManager.getConnection(adminUrl, username, password)) {

            try (final PreparedStatement ps = conn.prepareStatement(
                    "SELECT 1 FROM pg_database WHERE datname = ?")) {
                ps.setString(1, dbName);
                try (final ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        log.info("Database '{}' not found — creating...", dbName);
                        try (final Statement stmt = conn.createStatement()) {
                            stmt.executeUpdate("CREATE DATABASE \"" + dbName + "\"");
                        }
                        log.info("Database '{}' created successfully.", dbName);
                    } else {
                        log.debug("Database '{}' already exists.", dbName);
                    }
                }
            }
        } catch (final Exception e) {
            log.error("Failed to create database '{}'", dbName, e);
            throw new RuntimeException("Failed to initialize database: " + dbName, e);
        }
    }
}
