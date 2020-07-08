drop table if exists MESSAGE_TABLE;
drop sequence if exists msg_seq;

create sequence IF NOT EXISTS msg_seq;
CREATE TABLE IF NOT EXISTS MESSAGE_TABLE (  
message_Id NUMBER default msg_seq.nextval primary key,  
input_message VARCHAR(50) NOT NULL,  
delivery_Time TIMESTAMP NOT NULL,
DELIVERED VARCHAR(1)  
);