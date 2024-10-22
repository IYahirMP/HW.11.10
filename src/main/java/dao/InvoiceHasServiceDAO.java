package dao;

import models.InvoiceHasService;

import java.util.Optional;

public interface InvoiceHasServiceDAO extends Crud<InvoiceHasService> {
    int delete(int id1, int id2);
    Optional<InvoiceHasService> select(int invoiceId, int serviceId);
}
