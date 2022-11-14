package com.sina.cbs.accounting.journalentry.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sina.cbs.accounting.infrastructure.core.domain.AbstractPersistableCustom;
import com.sina.cbs.accounting.journalentry.record.JournalEntryRecord;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "acc_gl_journal_entry")
@Getter
@Setter
@AllArgsConstructor
public class JournalEntry extends AbstractPersistableCustom {

	private static final long serialVersionUID = 675229070241186694L;

	@Column(name = "cost_center_code", length = 10, nullable = false)
    private String costCenterCode;
 
    @ManyToOne
    @JoinColumn(name = "gl_account_code", nullable = false)
    private String glAccountCode;

    @Column(name = "type_enum", nullable = false)
    private String type;

    @Column(name = "amount", scale = 6, precision = 19, nullable = false)
    private BigDecimal amount;

    @Column(name = "currency_code", length = 3, nullable = false)
    private String currencyCode;

    @Column(name = "manual_entry", nullable = false)
    private boolean manualEntry = false;

    @Column(name = "entry_date")
    private LocalDateTime entryDate;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "transaction_type_enum", length = 50)
    private Integer transactionType;

    @Column(name = "transaction_id", nullable = false, length = 50)
    private String transactionId;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "external_reference_number")
    private String externalReferenceNumber;
    
    
    public static JournalEntry newInstance(JournalEntryRecord rec) {
		return new JournalEntry(
				rec.costCenterCode(),
				rec.glAccountCode(), 
				rec.type(), 
				rec.amount(), 
				rec.currencyCode(),
				rec.manualEntry(), 
				LocalDateTime.now(), 
				rec.description(), 
				rec.transactionType(), 
				rec.transactionId(),
				rec.referenceNumber(), 
				rec.externalReferenceNumber());
		
	}
}
