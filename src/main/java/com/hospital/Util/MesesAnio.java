package com.hospital.Util;


import java.util.ArrayList;
import java.util.List;

public class MesesAnio {

    private Integer id;
    private String nombreMes;

    public MesesAnio() {
    }

    public MesesAnio(Integer id, String nombreMes) {
        this.id = id;
        this.nombreMes = nombreMes;

    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreMes() {
		return nombreMes;
	}

	public void setNombreMes(String nombreMes) {
		this.nombreMes = nombreMes;
	}

	public static List<MesesAnio> listaMeses() {
        List<MesesAnio> listaMeses = new ArrayList<MesesAnio>();

        listaMeses.add(new MesesAnio(1, "Enero"));
        listaMeses.add(new MesesAnio(2, "Febrero"));
        listaMeses.add(new MesesAnio(3, "Marzo"));
        listaMeses.add(new MesesAnio(4, "Abril"));
        listaMeses.add(new MesesAnio(5, "Mayo"));
        listaMeses.add(new MesesAnio(6, "Junio"));
        listaMeses.add(new MesesAnio(7, "Julio"));
        listaMeses.add(new MesesAnio(8, "Agosto"));
        listaMeses.add(new MesesAnio(9, "Septiembre"));
        listaMeses.add(new MesesAnio(10, "Octubre"));
        listaMeses.add(new MesesAnio(11, "Noviembre"));
        listaMeses.add(new MesesAnio(12, "Diciembre"));

        return listaMeses;

    }


}
