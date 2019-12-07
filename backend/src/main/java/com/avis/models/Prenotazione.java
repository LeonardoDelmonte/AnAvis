package com.avis.models;
import java.util.GregorianCalendar;

public class Prenotazione {
    private long id;
    private Donatore donatore;
    private String donatoreString;
    private String sedeAvis;
    private GregorianCalendar data;

    public Prenotazione(long id,String sedeAvis,GregorianCalendar data){
        this.sedeAvis=sedeAvis;
        this.data=data;
        this.id=id;      
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Donatore getDonatore() {
        return donatore;
    }

    public void setDonatore(Donatore donatore) {
        this.donatore = donatore;
    }

    public String getSedeAvis() {
        return sedeAvis;
    }

    public void setSedeAvis(String sedeAvis) {
        this.sedeAvis = sedeAvis;
    }

    public GregorianCalendar getData() {
        return data;
    }

    public void setData(GregorianCalendar data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((donatore == null) ? 0 : donatore.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((sedeAvis == null) ? 0 : sedeAvis.hashCode());
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
        Prenotazione other = (Prenotazione) obj;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (donatore == null) {
            if (other.donatore != null)
                return false;
        } else if (!donatore.equals(other.donatore))
            return false;
        if (id != other.id)
            return false;
        if (sedeAvis == null) {
            if (other.sedeAvis != null)
                return false;
        } else if (!sedeAvis.equals(other.sedeAvis))
            return false;
        return true;
    }

    public String getDonatoreString() {
        return donatoreString;
    }

    public void setDonatoreString(String donatoreString) {
        this.donatoreString = donatoreString;
    }

    
}