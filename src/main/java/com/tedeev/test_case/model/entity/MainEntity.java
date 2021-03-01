package com.tedeev.test_case.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "csv_import")
@Getter
@Setter
public class MainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ssoid")
    private String ssoid;

    @Column(name = "ts")
    private LocalDateTime ts;

    @Column(name = "grp")
    private String grp;

    @Column(name = "type")
    private String type;

    @Column(name = "subtype")
    private String subtype;

    @Column(name = "url")
    private String url;

    @Column(name = "orgid")
    private String orgid;

    @Column(name = "formid")
    private String formid;

    @Column(name = "code")
    private String code;

    @Column(name = "ltpa")
    private String  ltpa;

    @Column(name = "sudirresponse")
    private String sudirresponse;

    @Column(name = "ymdh")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH", iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime ymdh;
}
