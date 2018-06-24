package com.example.manhl_000.bkrypto.Puzzles;

public class Puzzle {
    int pid, solve, tut_id, ans_id, number;
    String text, ott, hint, type;

    public Puzzle(int pid, int solve, int tut_id, int ans_id, int number, String text, String ott, String hint, String type) {
        this.pid = pid;
        this.solve = solve;
        this.tut_id = tut_id;
        this.ans_id = ans_id;
        this.number = number;
        this.text = text;
        this.ott = ott;
        this.hint = hint;
        this.type = type;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setSolve(int solve) {
        this.solve = solve;
    }

    public void setTut_id(int tut_id) {
        this.tut_id = tut_id;
    }

    public void setAns_id(int ans_id) {
        this.ans_id = ans_id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOtt(String ott) {
        this.ott = ott;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPid() {
        return pid;
    }

    public int getSolve() {
        return solve;
    }

    public int getTut_id() {
        return tut_id;
    }

    public int getAns_id() {
        return ans_id;
    }

    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public String getOtt() {
        return ott;
    }

    public String getHint() {
        return hint;
    }

    public String getType() {
        return type;
    }

}
