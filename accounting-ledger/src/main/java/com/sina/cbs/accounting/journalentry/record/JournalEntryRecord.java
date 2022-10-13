package com.sina.cbs.accounting.journalentry.record;

 
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.sina.cbs.accounting.journalentry.domain.JournalEntry;

public record JournalEntryRecord (
	 Long id,	
     String costCenterCode,
     String glAccountCode,
     String type,
     BigDecimal amount,
     String currencyCode,
     boolean manualEntry,
     LocalDateTime entryDate,
     String description,
     Integer transactionType,
     String transactionId,
     String referenceNumber,
     String externalReferenceNumber
){}
