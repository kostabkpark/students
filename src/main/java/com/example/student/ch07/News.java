package com.example.student.ch07;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class News {
    private int aid;
    private String title;
    private String img;
    private String date;
    private String content;


}
