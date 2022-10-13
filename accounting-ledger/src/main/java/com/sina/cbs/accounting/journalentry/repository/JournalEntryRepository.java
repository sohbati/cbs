package com.sina.cbs.accounting.journalentry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sina.cbs.accounting.journalentry.domain.JournalEntry;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long>  {

}
