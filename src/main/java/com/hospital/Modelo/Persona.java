package com.hospital.Modelo;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Pattern;


import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "persona")
public class Persona extends Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identificacion", unique = true, length = 10)
    @Pattern(regexp = "[0-9]+", message="La identificación solo puede contenener números")
    private String identificacion;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "correo", unique = true)
    private String correo;

    @Column(name = "telefono")
    private String telefono;

    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "genero_id", referencedColumnName = "id")
    @Column(name = "genero")
    private String genero;

    @Column(name = "lugar_residencia")
    private String lugar_residencia;

    @Column(name = "fecha_nacimiento")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_nacimiento;

    @Column(name = "usuario", unique = true, length = 30)
    private String usuario;

    @Column(name = "password", length = 128)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<Rol> roles;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<Especialidad> especialidades;


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<HorarioAtencion> horarios;

    //mappedBy = "persona" , cascade = CascadeType.ALL)
    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private HistoriaClinica historiaClinica;

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    @Column(name = "estado")
    private Boolean estado;


    public Persona(Long id, String identificacion, String nombres, String apellidos, String correo, String telefono, String genero, String lugar_residencia, Date fecha_nacimiento, String usuario, String password, List<Rol> roles, List<Especialidad> especialidades, List<HorarioAtencion> horarios, Boolean estado) {
        this.id = id;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.genero = genero;
        this.lugar_residencia = lugar_residencia;
        this.fecha_nacimiento = fecha_nacimiento;
        this.usuario = usuario;
        this.password = password;
        this.roles = roles;
        this.especialidades = especialidades;
        this.horarios = horarios;
        this.estado = estado;
    }

    public Persona(String identificacion, String nombres, String apellidos, String correo, String telefono, String genero, String lugar_residencia, Date fecha_nacimiento, String usuario, String password, List<Rol> roles, List<Especialidad> especialidades, List<HorarioAtencion> horarios, Boolean estado) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.genero = genero;
        this.lugar_residencia = lugar_residencia;
        this.fecha_nacimiento = fecha_nacimiento;
        this.usuario = usuario;
        this.password = password;
        this.roles = roles;
        this.especialidades = especialidades;
        this.horarios = horarios;
        this.estado = estado;
    }

    public List<HorarioAtencion> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioAtencion> horarios) {
        this.horarios = horarios;
    }

    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public Persona() {
        this.estado = true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getLugar_residencia() {
        return lugar_residencia;
    }

    public void setLugar_residencia(String lugar_residencia) {
        this.lugar_residencia = lugar_residencia;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


}
