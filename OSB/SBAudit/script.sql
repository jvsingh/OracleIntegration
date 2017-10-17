--create database sblog 
/
use sblog
/
create login sblog with password = '*****'
/

create user sblog for login sblog
/
GRANT SELECT, INSERT, UPDATE ON dbo.flowtrace_audit TO sblog
--GRANT SELECT, INSERT ON  dbo.flowtrace_audit TO sblog
--GRANT SELECT, DELETE ON dbo.flowtrace_audit TO sblog
/
drop table dbo.flowtrace_audit
/
CREATE TABLE dbo.FLOWTRACE_AUDIT ( 
  "Message_type" VARCHAR(16) NULL , 
-- osb message codes
  "AuditID" VARCHAR(64) NOT NULL, 
-- auto generated from a sequence
  "MessageReference" VARCHAR (64) ,
-- Can be a context sensitive message reference (UUID, ECID or another meaningful field from the actual data)
  "AuditDateTime" TIMESTAMP NULL , 
-- 'now' - time the first audit entry was made
  "MessageDateTime" VARCHAR(64) NULL , 
-- Time the message was inserted
  "MessagePayload" XML NULL , 
-- Actual payload
  "ComponentName" VARCHAR(64) NULL , 
-- Fully qualified SB component name (Project/Pipelines/pipeXYZ) 
  "ServiceVersion" VARCHAR(16) NULL)
-- Version where available
  
/
-- login as sblog
/
use sblog
/
select * from dbo.flowtrace_audit order by AuditDateTime desc
/
insert into dbo.flowtrace_audit(AuditID,MessageDateTime)
values ('123', 'test')
/

  
  