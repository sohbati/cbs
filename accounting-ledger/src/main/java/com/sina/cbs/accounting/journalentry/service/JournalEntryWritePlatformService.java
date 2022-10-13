package com.sina.cbs.accounting.journalentry.service;

import org.springframework.stereotype.Service;

import com.sina.cbs.accounting.journalentry.domain.JournalEntry;
import com.sina.cbs.accounting.journalentry.record.JournalEntryRecord;
import com.sina.cbs.accounting.journalentry.repository.JournalEntryRepository;
import com.sina.cbs.accounting.journalentry.repository.JournalEntryReverseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JournalEntryWritePlatformService {

	private final JournalEntryRepository journalEntryRepository;
	private final JournalEntryReverseRepository journalEntryReverseRepository;
		
	public Long saveJournalEntry(JournalEntryRecord journalEntryRecord) {
		
		JournalEntry je = JournalEntry.newInstance(journalEntryRecord);
		journalEntryRepository.save(je);
	
		return je.getId();
		
	}
	
	public Long reverse(JournalEntryRecord journalEntryRecord) {
		Long journalEntryId = journalEntryRecord.id();
		Long reverseJournalEntryId = saveJournalEntry(journalEntryRecord);
		
	}
	
}
