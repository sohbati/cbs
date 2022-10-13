package com.sina.cbs.accounting.journalentry.record;

import java.time.LocalDateTime;

public record JournalEntryReverseRecord(
		Long journalEntryId,
		Long revserseJournalEntryId,
		LocalDateTime entryDate,
		String referenceNumber,
		String reverseReferenceNumber
		) {}
