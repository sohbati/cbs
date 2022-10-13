package com.sina.cbs.accounting.journalentry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sina.cbs.accounting.journalentry.domain.JournalEntry;
import com.sina.cbs.accounting.journalentry.domain.JournalEntryReverse;

public interface JournalEntryReverseRepository extends JpaRepository<JournalEntryReverse, Long>  {

}
