package com.hospital.Util;


import java.util.ArrayList;
import java.util.List;

public class DiasSemana {

    private Integer id;
    private String nombreDia;

    public DiasSemana() {
    }

    public DiasSemana(Integer id, String nombreDia) {
        this.id = id;
        this.nombreDia = nombreDia;

    }

    

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreDia() {
		return nombreDia;
	}

	public void setNombreDia(String nombreDia) {
		this.nombreDia = nombreDia;
	}

	public static List<DiasSemana> listaDias() {
        List<DiasSemana> listaDias = new ArrayList<DiasSemana>();

        listaDias.add(new DiasSemana(1, "Lunes"));
        listaDias.add(new DiasSemana(2, "Martes"));
        listaDias.add(new DiasSemana(3, "Miercoles"));
        listaDias.add(new DiasSemana(4, "Jueves"));
        listaDias.add(new DiasSemana(5, "Viernes"));
        listaDias.add(new DiasSemana(6, "Sabado"));
        listaDias.add(new DiasSemana(7, "Domingo"));

        return listaDias;

    }


}
