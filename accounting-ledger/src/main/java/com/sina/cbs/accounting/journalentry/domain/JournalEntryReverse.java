package com.sina.cbs.accounting.journalentry.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sina.cbs.accounting.infrastructure.core.domain.AbstractPersistableCustom;

import lombok.Data;

@Entity
@Table(name = "acc_gl_journal_entry_reverse")
@Data
public class JournalEntryReverse extends AbstractPersistableCustom {
    private static final long serialVersionUID = -6027156954015502693L;

	@Column(name = "journal_entry_id", length = 3, nullable = false)
    private Long journalEntryId;

    @ManyToOne
    @JoinColumn(name = "reverse_journal_entry_id", nullable = false)
    private Long revserseJournalEntryId;

    @Column(name = "entry_date")
    private LocalDateTime entryDate;
    
    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "reverse_reference_number")
    private String reverseReferenceNumber;

}
