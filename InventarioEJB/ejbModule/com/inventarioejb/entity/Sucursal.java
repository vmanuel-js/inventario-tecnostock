package com.inventarioejb.entity;

import jakarta.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(
			name="Sucursal.listar",
			query="SELECT s FROM Sucursal s"
			),
	@NamedQuery(
			name="Sucursal.buscarPorNombre",
			query="SELECT s FROM Sucursal s WHERE s.nombre LIKE :nombre"
			),
	@NamedQuery(
			name="Sucursal.buscarPorId",
			query="SELECT s FROM Sucursal s WHERE s.idSucursal = :id"
			),
	@NamedQuery(
			name="Sucursal.actualizar",
			query="UPDATE Sucursal s SET s.nombre = :nombre, s.direccion = :direccion, s.telefono = :telefono, s.estado = :estado WHERE s.idSucursal = :id"
			),
	@NamedQuery(
			name="Sucursal.eliminar",
			query="UPDATE Sucursal s SET s.estado = :estado WHERE s.idSucursal = :id"
			),
})

public class Sucursal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSucursal;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "estado")
	private String estado;

	public int getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
