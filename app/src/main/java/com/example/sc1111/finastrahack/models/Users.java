package com.example.sc1111.finastrahack.models;

/**
 * Created by TW2222 on 2/20/2018.
 */

public class Users {

    private String first_name;
    private String last_name;
    private String email;
    private String loan_number;
    private String password;
    private String security_question;
    private String security_answer;
    private String sin;
    private String status;

    public Users(String first_name, String last_name, String email, String loan_number, String password, String security_question, String security_answer, String sin, String status) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.loan_number = loan_number;
        this.password = password;
        this.security_question = security_question;
        this.security_answer = security_answer;
        this.sin = sin;
        this.status = status;
    }

    public Users(){

    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoan_number() {
        return loan_number;
    }

    public void setLoan_number(String loan_number) {
        this.loan_number = loan_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurity_question() {
        return security_question;
    }

    public void setSecurity_question(String security_question) {
        this.security_question = security_question;
    }

    public String getSecurity_answer() {
        return security_answer;
    }

    public void setSecurity_answer(String security_answer) {
        this.security_answer = security_answer;
    }

    public String getSin() {
        return sin;
    }

    public void setSin(String sin) {
        this.sin = sin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Users{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", loan_number='" + loan_number + '\'' +
                ", password='" + password + '\'' +
                ", security_question='" + security_question + '\'' +
                ", security_answer='" + security_answer + '\'' +
                ", sin='" + sin + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
