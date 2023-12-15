
package br.edu.ifsp.pep.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "veiculo")
@NamedQueries({
    @NamedQuery(name = "Veiculo.findAll", query = "SELECT v FROM Veiculo v"),
    @NamedQuery(name = "Veiculo.findByCodigo", query = "SELECT v FROM Veiculo v WHERE v.codigo = :codigo"),
    @NamedQuery(name = "Veiculo.findByPlaca", query = "SELECT v FROM Veiculo v WHERE v.placa = :placa"),
    @NamedQuery(name = "Veiculo.findByCidade", query = "SELECT v FROM Veiculo v WHERE v.cidade = :cidade"),
    @NamedQuery(name = "Veiculo.findByModelo", query = "SELECT v FROM Veiculo v WHERE v.modelo = :modelo"),
    @NamedQuery(name = "Veiculo.findByAno", query = "SELECT v FROM Veiculo v WHERE v.ano = :ano"),
    @NamedQuery(name = "Veiculo.findByValorDiaria", query = "SELECT v FROM Veiculo v WHERE v.valorDiaria = :valorDiaria"),
    @NamedQuery(name = "Veiculo.findByIsLocado", query = "SELECT v FROM Veiculo v WHERE v.isLocado = :isLocado")})
public class Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo", nullable = false)
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "placa", nullable = false, length = 15)
    private String placa;
    @Column(name = "tipo", nullable = false, length = 15)
    private String tipo;
    @Basic(optional = false)
    @Column(name = "cidade", nullable = false, length = 60)
    private String cidade;
    @Basic(optional = false)
    @Column(name = "modelo", nullable = false, length = 40)
    private String modelo;
    @Basic(optional = false)
    @Column(name = "ano", nullable = false)
    private int ano;
    @Basic(optional = false)
    @Column(name = "valor_diaria", nullable = false)
    private double valorDiaria;
    @Basic(optional = false)
    @Column(name = "isLocado", nullable = false)
    private boolean isLocado;

    public Veiculo() {
    }

    public Veiculo(String tipo, String placa, String cidade, String modelo, int ano, double valorDiaria, boolean isLocado){
        this.placa = placa;
        this.tipo = tipo;
        this.cidade = cidade;
        this.modelo = modelo;
        this.ano = ano;
        this.valorDiaria = valorDiaria;
        this.isLocado = isLocado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public boolean isLocado() {
        return isLocado;
    }

    public void setIsLocado(boolean isLocado) {
        this.isLocado = isLocado;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Veiculo)) {
            return false;
        }
        Veiculo other = (Veiculo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Veiculo{" + "codigo=" + codigo + ", placa=" + placa + ", tipo=" + tipo + ", cidade=" + cidade + ", modelo=" + modelo + ", ano=" + ano + ", valorDiaria=" + valorDiaria + ", isLocado=" + isLocado + '}';
    }
}
