package com.tedeev.test_case.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.tedeev.test_case.model.entity.MainEntity;
import com.tedeev.test_case.model.repository.MainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MainService {
    private static final int VALID_DATA_LENGTH = 12;
    private static final int SSOID = 0;
    private static final int TS = 1;
    private static final int GRP = 2;
    private static final int TYPE = 3;
    private static final int SUBTYPE = 4;
    private static final int URL = 5;
    private static final int ORGID = 6;
    private static final int FORMID = 7;
    private static final int CODE = 8;
    private static final int LTPA = 9;
    private static final int SUDIRRESPONSE = 10;
    private static final int YMDH = 11;
    @Value("${csvPath}")
    private String csvPath;

    private final MainRepository mainRepository;

    @Autowired
    public MainService(MainRepository userRepository) {
        this.mainRepository = userRepository;
    }

    @Transactional
    @PostConstruct
    public String ImportToDb() throws IOException {

        CSVParser parser = new CSVParserBuilder()
            .withSeparator(';')
            .withIgnoreQuotations(false)
            .build();

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(new File("D:\\Рабочий стол\\Тестовое Задание\\test_case.csv")))
            .withSkipLines(1)
            .withCSVParser(parser)
            .build()) {

            String[] data;
            while ((data = csvReader.readNext()) != null) {
                mainRepository.save(createNewEvent(data));
            }
        }
        return "Imported!";
    }

    private MainEntity createNewEvent(String[] data) {

        MainEntity newEvent = new MainEntity();

        if (data.length == VALID_DATA_LENGTH) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH");

            newEvent.setSsoid(data[SSOID]);
            LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(data[TS])), ZoneId.of("+00:00:00"));
            newEvent.setTs(dateTime);
            newEvent.setGrp(data[GRP]);
            newEvent.setType(data[TYPE]);
            newEvent.setSubtype(data[SUBTYPE]);
            newEvent.setUrl(data[URL]);
            newEvent.setOrgid(data[ORGID]);
            newEvent.setFormid(data[FORMID]);
            newEvent.setCode(data[CODE]);
            newEvent.setLtpa(data[LTPA]);
            newEvent.setSudirresponse(data[SUDIRRESPONSE]);
            newEvent.setYmdh(LocalDateTime.parse(data[YMDH], formatter));
        }

        return newEvent;
    }

    public Map<String, List<String>> getHourStatistic() { //A

        Map<String, List<String>> userForms = new HashMap<>();

        for (MainEntity mainEntity : mainRepository.getLastHourUsersAndForms()) {

            List<String> forms = userForms.get(mainEntity.getSsoid());
            if (forms == null) {
                forms = new ArrayList<>();
            }
            forms.add(mainEntity.getFormid());
            userForms.put(mainEntity.getSsoid(), forms);
        }

        return userForms;
    }



    public Map<String,String>  getSecondStatistic(){ //B
        Map<String, String> SecondStatistic = new HashMap<>();
        for (String line : mainRepository.getSecondStatistics()) {
            String[] formAndCount = line.split(",");

            String formId = formAndCount[0];
            String subtype = formAndCount[5];

            SecondStatistic.put(formId,subtype);
        }

        return SecondStatistic;
    }





    public Map<String, String> getTopForms() { //C
        Map<String, String> map = new HashMap<>();
        for (String line : mainRepository.getTopFiveForms()) {
            String[] formAndCount = line.split(",");

            String formId = formAndCount[0];
            String usesCount = formAndCount[1];

            map.put(formId, usesCount);
        }
        return map;
    }
}



