package main.java.conversor.model;

public class Conversor {
    private String baseCode;
    private String targetCode;
    private Double amount;
    private Double amountConverted;
    private String createdAt;

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmountConverted() {
        return amountConverted;
    }

    public void setAmountConverted(Double amountConverted) {
        this.amountConverted = amountConverted;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return String.format("Resultado de la Conversión: La cantidad de %.2f en %s es equivalente a %.2f en %s según los últimos tipos de cambio.", this.amount, this.baseCode, this.amountConverted, this.targetCode);
    }
}