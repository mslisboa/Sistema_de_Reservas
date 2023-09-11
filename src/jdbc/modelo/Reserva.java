package jdbc.modelo;

import java.math.BigDecimal;
import java.util.Date;

public class Reserva {
    private Integer id;
    private Date data_entrada;
    private Date data_saida;
    private BigDecimal valor;
    private String forma_pagamento;
    
    public Reserva(Date data_entrada, Date data_saida, BigDecimal valor, String forma_pagamento) {
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
        this.valor = valor;
        this.forma_pagamento = forma_pagamento;
    }

    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return Date return the data_entrada
     */
    public Date getData_entrada() {
        return data_entrada;
    }

    /**
     * @param data_entrada the data_entrada to set
     */
    public void setData_entrada(Date data_entrada) {
        this.data_entrada = data_entrada;
    }

    /**
     * @return Date return the data_saida
     */
    public Date getData_saida() {
        return data_saida;
    }

    /**
     * @param data_saida the data_saida to set
     */
    public void setData_saida(Date data_saida) {
        this.data_saida = data_saida;
    }

    /**
     * @return BigDecimal return the valor
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    /**
     * @return String return the forma_pagamento
     */
    public String getForma_pagamento() {
        return forma_pagamento;
    }

    /**
     * @param forma_pagamento the forma_pagamento to set
     */
    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

}