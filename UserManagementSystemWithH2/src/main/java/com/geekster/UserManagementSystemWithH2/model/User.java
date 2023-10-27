package com.geekster.UserManagementSystemWithH2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_table")

public class User {

    @Id

    private Integer userId;

    private String userName;

    private String date_of_birth;

    private String email;

    private String phone_number;

    private Date date;

    private Timestamp time;
}
