package com.example.manhl_000.bkrypto.Puzzles;

public class puzzle_row {
    String text;
    int img, b;

    public puzzle_row(String text, int img, int b) {
        this.text = text;
        this.img = img;
        this.b = b;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String getText() {
        return text;
    }

    public int getImg() {
        return img;
    }

    public int isB() {
        return b;
    }

    @Override
    public String toString() {
        return "puzzle_row{" +
                "text='" + text + '\'' +
                ", img=" + img +
                ", b=" + b +
                '}';
    }
}
