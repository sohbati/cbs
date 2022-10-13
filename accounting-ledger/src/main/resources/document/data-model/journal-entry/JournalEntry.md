## Journal Entry    
 دفتر روزنامه
-
<br/>

 1. ID (Long)
 - شناسه جدول. این فیلد به صورت کلید اصلی تعریف میشود و توسط دیتابیس مقداردهی میشود
 2. cost_center_code(String)
    -This is foreign key for COST_CENTER.cost_center_code
    - کد مرکز هزینه
    - مرکز هزینه معمولا شعبه ای است که سند متعلق به آن شعبه میباشد
    - the COST_CENTER is defined in Global-Information-service
 4. gl_account_code(String)
    - کد حساب دفتر کل
    - this is foreign key for Table GENERAL_LEDGER.al_account_code
    - the GENERAL_LEDGER is defined in Global-Information-service
 5. type_enum
 6. amount
 7. currency_code
 8. manual_entry
 9. entry_date
 10. description
 11. transaction_type_enum
 12. transaction_id
 12.reference_number
 13. external_reference_number

