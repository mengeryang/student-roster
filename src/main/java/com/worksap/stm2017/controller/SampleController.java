package com.worksap.stm2017.controller;

import com.worksap.stm2017.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;


//@Controller
//public class SampleController {
//
//    private JdbcTemplate jdbcTemplate;
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    public SampleController(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @RequestMapping("/")
//    @ResponseBody
//    List<Person> home() {
//        List<Person> personList = jdbcTemplate.query("select * from person", BeanPropertyRowMapper.newInstance(Person.class));
//        personList.stream().map( x -> {logger.info(String.format("Person: %s", x.getLastName())); return null;});
//
//        return personList;
//    }
//}
