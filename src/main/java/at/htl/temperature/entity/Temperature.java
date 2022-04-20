package at.htl.temperature.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.Month;

/**
 * Die Spalte yyyymm darf keine doppelten Werte enthalten
 */

@Entity
@Table(name="S_TEMPERATURE")
public class Temperature extends PanacheEntityBase {

    @Transient
    @JsonIgnore
    int originY = 300;

    @Transient
    @JsonIgnore
    int scaleY = 30;

    @Id
    @Column(name = "T_YYYYMM") //, unique = true)
    public int yyyymm;    // Jahr + Monat zB 202001 für Jänner 2020

    @Column(name = "T_YEAR")
    public int year;

    @Enumerated(EnumType.STRING)
    @Column(name = "T_MONTH")
    public Month month;  // enum -> Month.JANUARY, Month.FEBRUARY, ...

    @Column(name = "T_TEMPERATURE")
    public double temperature;

    @Column(name = "T_LOCATION")
    public String location;

    public Temperature() {
    }

    public Temperature(int yyyy, int mm, double temperature, String location) {
        this.yyyymm = yyyy * 100 + mm;
        this.year = yyyy;
        this.month = Month.of(mm);
        this.temperature = temperature / 10;
        this.location = location;
    }

    public int widthAbsolute() {
        return (int) (Math.abs( temperature) * scaleY);
    }

    public int width() {
        return (int) (Math.abs( temperature) * scaleY);
    }

    public int marginNegative(double temperature) {
        return (int) (originY - (Math.abs(temperature) * scaleY));
    }

    public int getOriginY() {
        return this.originY;
    }

    public int getScaleY() {
        return this.scaleY;
    }

    @Override
    public String toString() {
        return String.format("%d-%s -> %.1f °C", this.year, month.name(), temperature);
    }
}
