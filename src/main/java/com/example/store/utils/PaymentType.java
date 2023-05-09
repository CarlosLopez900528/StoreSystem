package com.example.store.utils;

public enum PaymentType {
    CASH(1, "Cash"),
    CREDIT_CARD(2, "Credit Card"),
    CHECK(3, "Check"),
    OTHER(4, "Other");

    private final int idPaymentType;
    private final String payment;

    PaymentType(int id, String payment) {
        this.idPaymentType = id;
        this.payment = payment;
    }

    public int getIdPaymentType() {
        return idPaymentType;
    }

    public String getPayment() {
        return payment;
    }


}
