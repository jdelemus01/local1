package com.velazquez.apirestpi.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "ACTIVIDAD")
public class Actividad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double length;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private boolean underAge;

    @Column(nullable = false)
    private boolean onAir;

    @ManyToOne
    @JoinColumn(name="idTipo")
    Tipo tipo;

    @ManyToOne
    @JoinColumn(name="idOfertante")
    Ofertante ofertante;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean isUnderAge() {
        return underAge;
    }

    public void setUnderAge(boolean underAge) {
        this.underAge = underAge;
    }

    public boolean isOnAir() {
        return onAir;
    }

    public void setOnAir(boolean onAir) {
        this.onAir = onAir;
    }

    public Ofertante getOfertante() {
        return ofertante;
    }

    public void setOfertante(Ofertante ofertante) {
        this.ofertante = ofertante;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((length == null) ? 0 : length.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + (underAge ? 1231 : 1237);
        result = prime * result + (onAir ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Actividad other = (Actividad) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (length == null) {
            if (other.length != null)
                return false;
        } else if (!length.equals(other.length))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (underAge != other.underAge)
            return false;
        if (onAir != other.onAir)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Actividad [id=" + id + ", title=" + title + ", length=" + length + ", price=" + price + ", underAge="
                + underAge + ", onAir=" + onAir + "]";
    }

}
