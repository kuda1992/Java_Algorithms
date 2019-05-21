package com.pluralsight.algo.Priority;



import com.sun.istack.internal.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transcation implements Comparable<Transcation> {

    private String who;
    private Date when;
    private double amount;

    public Transcation(String transcation) {
        String split[] = transcation.split("\\s+");
        who = split[0];
        try {
            when = new SimpleDateFormat("m/dd/yyyy").parse(split[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        amount = Double.parseDouble(split[2]);
    }

    @Override
    public String toString() {
        return "\r\n{" +
                "who: " + who + ", "
                + "when " + when + ", "
                + "amount " + amount + "" +
                "}" + "\r\n";
    }

    @Override
    public int compareTo(@NotNull Transcation o) {
        if (this.getAmount() < o.getAmount()) return -1;
        if (this.getAmount() > o.getAmount()) return 1;
        return 0;
    }

    public static abstract class WhoOrder implements Comparable<Transcation> {
        public int compare(Transcation v, Transcation w) {
            return v.who.compareTo(w.who);
        }
    }

    public static abstract class WhenOrder implements Comparable<Transcation> {
        public int compare(Transcation v, Transcation w) {
            return v.when.compareTo(w.when);
        }
    }

    public static abstract class HowMuchOrder implements Comparable<Transcation> {

        public int compare(Transcation v, Transcation w) {
            if (v.amount < w.amount) return -1;
            if (v.amount > w.amount) return 1;
            return 0;
        }
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
