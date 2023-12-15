
package br.edu.ifsp.pep.model;

import java.io.Serializable;

public class LocacaoPK implements Serializable {

    private int cliente;

    private int veiculo;

    public LocacaoPK() {
    }

    public LocacaoPK(int cliente, int veiculo) {
        this.cliente = cliente;
        this.veiculo = veiculo;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(int veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cliente;
        hash += (int) veiculo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof LocacaoPK)) {
            return false;
        }
        LocacaoPK other = (LocacaoPK) object;
        if (this.cliente != other.cliente) {
            return false;
        }
        if (this.veiculo != other.veiculo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifsp.pep.model.LocacaoPK[ cliente=" + cliente + ", veiculo=" + veiculo + " ]";
    }
    
}
