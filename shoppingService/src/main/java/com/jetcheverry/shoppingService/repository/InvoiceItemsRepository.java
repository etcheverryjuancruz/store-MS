package com.jetcheverry.shoppingService.repository;

import com.jetcheverry.shoppingService.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem,Long> {
}