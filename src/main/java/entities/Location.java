package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Location {

    @Id
    @GeneratedValue
    @Column(name = "id_location")
    private long idLocation;

    @Column(name = "name_location")
    private String nameLocation;

    @Column(name = "city")
    private String city;

    public Location() {
    }

    public Location(String nameLocation, String city) {
        this.nameLocation = nameLocation;
        this.city = city;
    }

    public long getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(long idLocation) {
        this.idLocation = idLocation;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Location{" +
                "idLocation=" + idLocation +
                ", nameLocation='" + nameLocation + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
