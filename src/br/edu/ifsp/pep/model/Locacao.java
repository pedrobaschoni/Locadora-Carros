package br.edu.ifsp.pep.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "locacao")
@IdClass(LocacaoPK.class)
@NamedQueries({
    @NamedQuery(name = "Locacao.findAll", query = "SELECT l FROM Locacao l"),
    @NamedQuery(name = "Locacao.findByCliente", query = "SELECT l FROM Locacao l WHERE l.cliente = :cliente"),
    @NamedQuery(name = "Locacao.findByVeiculo", query = "SELECT l FROM Locacao l WHERE l.veiculo = :veiculo"),
    @NamedQuery(name = "Locacao.findByDataInicio", query = "SELECT l FROM Locacao l WHERE l.dataInicio = :dataInicio"),
    @NamedQuery(name = "Locacao.findByTotal", query = "SELECT l FROM Locacao l WHERE l.total = :total"),
    @NamedQuery(name = "Locacao.findByDataFim", query = "SELECT l FROM Locacao l WHERE l.dataFim = :dataFim")})
public class Locacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    @Id
    @JoinColumn(name = "veiculo")
    private Veiculo veiculo;

    @Basic(optional = false)
    @Column(name = "data_inicio", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;

    @Basic(optional = false)
    @Column(name = "total", nullable = false)
    private double total;

    @Basic(optional = false)
    @Column(name = "dias", nullable = false)
    private int dias;

    @Column(name = "data_fim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFim;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
    private Cliente cliente1;

    @ManyToOne(optional = false)
    @JoinColumn(name = "veiculo", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
    private Veiculo veiculo1;

    public Locacao() {
    }

    public Locacao(Cliente cliente, Veiculo veiculo, Date dataInicio, double total, int dias, Date dataFim, Cliente cliente1, Veiculo veiculo1) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataInicio = dataInicio;
        this.total = total;
        this.dias = dias;
        this.dataFim = dataFim;
        this.cliente1 = cliente1;
        this.veiculo1 = veiculo1;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Cliente getCliente1() {
        return cliente1;
    }

    public void setCliente1(Cliente cliente1) {
        this.cliente1 = cliente1;
    }

    public Veiculo getVeiculo1() {
        return veiculo1;
    }

    public void setVeiculo1(Veiculo veiculo1) {
        this.veiculo1 = veiculo1;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.cliente);
        hash = 29 * hash + Objects.hashCode(this.veiculo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Locacao other = (Locacao) obj;
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        return Objects.equals(this.veiculo, other.veiculo);
    }

    @Override
    public String toString() {
        return "Locacao{" + "cliente=" + cliente + ", veiculo=" + veiculo + ", dataInicio=" + dataInicio + ", total=" + total + ", dataFim=" + dataFim + ", cliente1=" + cliente1 + ", veiculo1=" + veiculo1 + '}';
    }

}
