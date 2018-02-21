package com.example.sc1111.finastrahack;

import java.io.Serializable;

/**
 * Created by sc1111 on 2/20/2018.
 */

@SuppressWarnings("serial")
public class UserData implements Serializable{
    public String firstname;
    public String lastname;
    public float amtowing;
    public float amtpaid;
    public String loannum;
}