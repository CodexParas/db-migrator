package com.paras.db_migrator.generator;

import com.paras.db_migrator.entity.mysql.MySqlClientEntity;
import com.paras.db_migrator.entity.oracle.OracleClientEntity;
import com.paras.db_migrator.entity.postgres.PostgresClientEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class MockDataGenerator {

    private final Random random = new Random();

    public List<MySqlClientEntity> mySqlGenerateData(int count) {
        if(count <= 0) {
            return Collections.emptyList();
        }
        List<MySqlClientEntity> clients = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            clients.add(generateMySqlClient(i));
        }
        return clients;
    }

    public List<PostgresClientEntity> postgresGenerateData(int count) {
        if(count <= 0) {
            return Collections.emptyList();
        }
        List<PostgresClientEntity> clients = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            clients.add(generatePostgresClient(i));
        }
        return clients;
    }

    public List<OracleClientEntity> oracleGenerateData(int count) {
        if(count <= 0) {
            return Collections.emptyList();
        }
        List<OracleClientEntity> clients = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            clients.add(generateOracleClient(i));
        }
        return clients;
    }

    private OracleClientEntity generateOracleClient(int index) {
        OracleClientEntity client = OracleClientEntity.builder()
                                                      .name(getRandomName())
                                                      .phone(getRandomPhone())
                                                      .address(getRandomAddress())
                                                      .city(getRandomCity())
                                                      .state(getRandomState())
                                                      .zip(getRandomZip())
                                                      .country(getRandomCountry())
                                                      .isActive(getRandomBoolean())
                                                      .build();
        client.setEmail(getRandomEmail(client.getName(), index));
        return client;
    }

    private PostgresClientEntity generatePostgresClient(int index) {
        PostgresClientEntity client = PostgresClientEntity.builder()
                                                          .name(getRandomName())
                                                          .phone(getRandomPhone())
                                                          .address(getRandomAddress())
                                                          .city(getRandomCity())
                                                          .state(getRandomState())
                                                          .zip(getRandomZip())
                                                          .country(getRandomCountry())
                                                          .isActive(getRandomBoolean())
                                                          .build();
        client.setEmail(getRandomEmail(client.getName(), index));
        return client;
    }


    private MySqlClientEntity generateMySqlClient(int index) {
        MySqlClientEntity client = MySqlClientEntity.builder()
                                                    .name(getRandomName())
                                                    .phone(getRandomPhone())
                                                    .address(getRandomAddress())
                                                    .city(getRandomCity())
                                                    .state(getRandomState())
                                                    .zip(getRandomZip())
                                                    .country(getRandomCountry())
                                                    .isActive(getRandomBoolean())
                                                    .build();
        client.setEmail(getRandomEmail(client.getName(), index));
        return client;
    }

    private String getRandomName() {
        String[] firstNames = {"John", "Jane", "Michael", "Emily", "Chris", "Sara"};
        String[] lastNames = {"Doe", "Smith", "Brown", "Johnson", "Taylor", "Anderson"};
        return firstNames[random.nextInt(firstNames.length)] + " " + lastNames[random.nextInt(lastNames.length)];
    }

    private String getRandomEmail(String name, int index) {
        return name.toLowerCase().replace(" ", ".") + index + "@example.com";
    }

    private String getRandomPhone() {
        return String.valueOf(1000000000L + random.nextInt(900000000));
    }

    private String getRandomAddress() {
        String[] streets = {"Main Street", "Elm Street", "Maple Avenue", "Oak Lane", "Pine Street"};
        return random.nextInt(999) + " " + streets[random.nextInt(streets.length)];
    }

    private String getRandomCity() {
        String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix"};
        return cities[random.nextInt(cities.length)];
    }

    private String getRandomState() {
        String[] states = {"NY", "CA", "IL", "TX", "AZ"};
        return states[random.nextInt(states.length)];
    }

    private String getRandomZip() {
        return String.format("%05d", random.nextInt(100000));
    }

    private String getRandomCountry() {
        String[] countries = {"USA", "Canada", "UK", "Germany", "France"};
        return countries[random.nextInt(countries.length)];
    }

    private boolean getRandomBoolean() {
        return random.nextBoolean();
    }

}
